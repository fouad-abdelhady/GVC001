package hk.ust.cse.comp107x.gvc001.activities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hk.ust.cse.comp107x.gvc001.R;
import hk.ust.cse.comp107x.gvc001.fragments.ConjugationFragment;
import hk.ust.cse.comp107x.gvc001.fragments.Result;
import hk.ust.cse.comp107x.gvc001.verb.Conjugation;
import hk.ust.cse.comp107x.gvc001.verb.Verb;

import static hk.ust.cse.comp107x.gvc001.fragments.ConjugationFragment.VERB_OBJ_CHECK;

public class HomeActivity extends AppCompatActivity {
    public static final String VOCABS_LIST = "verbs";
    public static final String VERB = "verb";
    public static final String CONJUGATION = "conjugation";
    private Verb verbObj;
    private Conjugation conjugation;
    private FirebaseDatabase gvc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setViews();
    }

    private void setViews() {
       gvc = FirebaseDatabase.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        ConjugationFragment obj = new ConjugationFragment();
        obj.setHomeObj(this);
        setFragment(obj);

    }

    public void setFragment(Fragment conjugation) {
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.home_fragment_container, conjugation).
                commit();
    }

    public void searchVerb(final String verb, final byte whichFragment, final Result result, final ConjugationFragment obj) {
        verbObj = null;
        gvc.getReference(VOCABS_LIST).orderByChild(VERB).equalTo(verb.toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkAndDisplayResult(dataSnapshot, whichFragment, result, obj);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void checkAndDisplayResult(DataSnapshot dataSnapshot, byte whichFragment,Result result, ConjugationFragment obj) {
        if(dataSnapshot.exists()){
            verbObj= new Verb();
            // Toast.makeText(HomeActivity.this, "true", Toast.LENGTH_LONG).show();
            for(DataSnapshot verbData: dataSnapshot.getChildren()){
                Log.d(VERB_OBJ_CHECK,verbData.getKey());
                verbObj = verbData.getValue(Verb.class);
                result.setVerbObj(verbObj);
                if(whichFragment == ConjugationFragment.CONJUGATION_FRAGMENT){
                    obj.showLoading(false);
                    result.setHomeObj(HomeActivity.this);
                    setFragment(result);
                }
                else if(whichFragment == Result.RESULT_FRAGMENT){
                    result.displayFirstPrasensVerb();
                }
                else {}

            }
        }
        else{
            if(obj != null)
                obj.showLoading(false);
            Toast.makeText(HomeActivity.this, "Unavailable Verb ", Toast.LENGTH_LONG).show();
        }

    }

    private Conjugation getTenseConj(String key, final String tense) {
        String path = VOCABS_LIST +"/"+ key +"/"+ CONJUGATION +"/"+ tense;
        conjugation = new Conjugation();
        gvc.getReference(path).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               if(dataSnapshot.exists()){
                   for(DataSnapshot tenseData : dataSnapshot.getChildren()){
                       conjugation = tenseData.getValue(Conjugation.class);
                       break;
                   }
               }
               else{
                   Toast.makeText(HomeActivity.this, "Undefined Error Happend", Toast.LENGTH_LONG).show();
               }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return conjugation;
    }
}
