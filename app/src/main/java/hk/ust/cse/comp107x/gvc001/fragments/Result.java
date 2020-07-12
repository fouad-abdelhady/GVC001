package hk.ust.cse.comp107x.gvc001.fragments;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import hk.ust.cse.comp107x.gvc001.R;
import hk.ust.cse.comp107x.gvc001.activities.HomeActivity;
import hk.ust.cse.comp107x.gvc001.verb.Conjugation;
import hk.ust.cse.comp107x.gvc001.verb.Verb;

/**
 * A simple {@link Fragment} subclass.
 */
public class Result extends Fragment {
    public static final  String SCREEN_ROTATION = "SCREEN_ROTATION";
    public static final  String SAVED_VERB_OBJ = "SAVED_VERB_OBJ";
    private final static boolean ON = true;
    private final static boolean OFF = false;
    private final static int PRASENS = 0;
    private final static int PRATERITUM = 1;
    private final static int FUTUR_I = 2;
    private final static int PERFECT = 3;
    private final static int PLUSQUAMPERFERCT = 4;
    private final static int FUTUR_II = 5;
    public static final byte RESULT_FRAGMENT = 1;

    private SearchView searchView;

    private Verb verb;
    private View view;

    private LinearLayout mainBodey;
    private LinearLayout loadingLayout;
    private LinearLayout auxVerbsRaw1;
    private LinearLayout auxVerbsRaw2;

    private TextView resultVerb,
            auxVerb,
            type;

    // auxiliary Verbs I part
    private TextView auxVerbIch,
            auxVerbDu,
            auxVerbEr,
            auxVerbWir,
            auxVerbIhr,
            auxVerbSie;

    // auxiliary Verbs II part
    private TextView auxVerbiiIch,
            auxVerbiiDu,
            auxVerbiiEr,
            auxVerbiiWir,
            auxVerbiiIhr,
            auxVerbiiSie;

    // verb forms section
    private TextView verbIch,
            verbDu,
            verbEr,
            verbWir,
            verbIhr,
            verbSie;

    // clickable tenses textViews
    private TextView prasens,
            prateritum,
            perfekt,
            plusquamperfekt,
            futureI,
            futureII;
    private HomeActivity homeObj;

    public Result() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_result, container, false);
       // checkPreviousObj(savedInstanceState);
        setViews();
        return view;
    }

    private void checkPreviousObj(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            this.setVerbObj((Verb) savedInstanceState.get(SCREEN_ROTATION));
        }
    }

    private void setViews() {
        searchView = view.findViewById(R.id.result_search_view);
        mainBodey = view.findViewById(R.id.result_body);
        loadingLayout = view.findViewById(R.id.result_loading_layout);
        auxVerbsRaw1 = view.findViewById(R.id.result_auxverb);
        auxVerbsRaw2 = view.findViewById(R.id.result_futureii_auxverbii);

        resultVerb = view.findViewById(R.id.result_verb_infinitive);
        auxVerb = view.findViewById(R.id.result_auxverb_infinitive);
        type = view.findViewById(R.id.result_verb_type);

        auxVerbIch = view.findViewById(R.id.result_auxverb_ich);
        auxVerbDu = view.findViewById(R.id.result_auxverb_du);
        auxVerbEr = view.findViewById(R.id.result_auxverb_er);
        auxVerbWir = view.findViewById(R.id.result_auxverb_wir);
        auxVerbIhr = view.findViewById(R.id.result_auxverb_ihr);
        auxVerbSie = view.findViewById(R.id.result_auxverb_sie);

        auxVerbiiIch = view.findViewById(R.id.result_auxverbii_ich);
        auxVerbiiDu = view.findViewById(R.id.result_auxverbii_du);
        auxVerbiiEr = view.findViewById(R.id.result_auxverbii_er);
        auxVerbiiWir = view.findViewById(R.id.result_auxverbii_wir);
        auxVerbiiIhr = view.findViewById(R.id.result_auxverbii_ihr);
        auxVerbiiSie = view.findViewById(R.id.result_auxverbii_sie);

        verbIch = view.findViewById(R.id.result_verb_ich);
        verbDu = view.findViewById(R.id.result_verb_du);
        verbEr = view.findViewById(R.id.result_verb_er);
        verbWir = view.findViewById(R.id.result_verb_wir);
        verbIhr = view.findViewById(R.id.result_verb_ihr);
        verbSie = view.findViewById(R.id.result_verb_sie);

        prasens = view.findViewById(R.id.result_tense_prasens);
        prateritum = view.findViewById(R.id.result_tense_prateritum);
        perfekt = view.findViewById(R.id.result_tense_perfekt);
        plusquamperfekt = view.findViewById(R.id.result_tense_plusquamperfekt);
        futureI = view.findViewById(R.id.result_tense_futurei);
        futureII = view.findViewById(R.id.result_tense_futureii);
    }

    public void setVerbObj(Verb verb) {
        this.verb = verb;
    }

    private void displayResult() {
        resultVerb.setText(this.verb.getVerb());
        auxVerb.setText(this.verb.getAuxiliaryVerb());
        if (this.verb.getIrregular() == 0)
            type.setText("Regular");
        else
            type.setText("Irregular");

    }

    private void showLoadingBar(boolean on){
        if (on){
            loadingLayout.setVisibility(View.VISIBLE);
            mainBodey.setVisibility(View.GONE);
            setSearchViewOn(false);
            return;
        }
        loadingLayout.setVisibility(View.GONE);
        mainBodey.setVisibility(View.VISIBLE);
        setSearchViewOn(true);
    }
    @Override
    public void onStart() {
        super.onStart();

        setListeners();
        displayFirstPrasensVerb();
    }

    public void displayFirstPrasensVerb(){
        showLoadingBar(false);
        displayResult();
        setTenseButtonColor(prasens, true);
        displayPrasensePrateritum(prasens, verb.getConjugation().get(PRASENS));
    }

    private void setListeners() {
        prasens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrasensePrateritum(prasens, verb.getConjugation().get(PRASENS));
            }
        });
        prateritum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPrasensePrateritum(prateritum, verb.getConjugation().get(PRATERITUM));
            }
        });
        perfekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPerfectPlusFuI(perfekt, verb.getConjugation().get(PERFECT));
            }
        });
        plusquamperfekt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPerfectPlusFuI(plusquamperfekt, verb.getConjugation().get(PLUSQUAMPERFERCT));
            }
        });
        futureI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPerfectPlusFuI(futureI, verb.getConjugation().get(FUTUR_I));
            }
        });
        futureII.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayFutureII(verb.getConjugation().get(FUTUR_II));
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!verb.getVerb().equals(query.toLowerCase())){
                    showLoadingBar(true);
                    homeObj.searchVerb(query, RESULT_FRAGMENT, Result.this, null);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void displayPrasensePrateritum(TextView tense, Conjugation tenseConj){
        auxVerbsRaw1.setVisibility(View.GONE);
        auxVerbsRaw2.setVisibility(View.GONE);

        setTenseButtonColor(tense, ON);

        verbIch.setText(tenseConj.getIch());
        verbDu.setText(tenseConj.getDu());
        verbEr.setText(tenseConj.getEr());
        verbWir.setText(tenseConj.getWir());
        verbIhr.setText(tenseConj.getIhr());
        verbSie.setText(tenseConj.getSie());
    }

    private void displayPerfectPlusFuI(TextView tense, Conjugation conj){

        auxVerbsRaw1.setVisibility(View.VISIBLE);
        auxVerbsRaw2.setVisibility(View.GONE);

        setTenseButtonColor(tense, ON);

        perfectPlusFuISetter(verbIch, auxVerbIch, conj.getIch());
        perfectPlusFuISetter(verbDu, auxVerbDu, conj.getDu());
        perfectPlusFuISetter(verbEr, auxVerbEr, conj.getEr());
        perfectPlusFuISetter(verbWir, auxVerbWir, conj.getWir());
        perfectPlusFuISetter(verbIhr, auxVerbIhr, conj.getIhr());
        perfectPlusFuISetter(verbSie, auxVerbSie, conj.getSie());
    }

    private void perfectPlusFuISetter(TextView subj, TextView auxV, String conj){
        String [] parts = conj.split("\\s+");
        auxV.setText(parts[0]);
        subj.setText(parts[1]);
    }

    private void displayFutureII(Conjugation conj) {
        auxVerbsRaw1.setVisibility(View.VISIBLE);
        auxVerbsRaw2.setVisibility(View.VISIBLE);

        setTenseButtonColor(futureII, ON);

        futureIISetter(verbIch, auxVerbIch, auxVerbiiIch, conj.getIch());
        futureIISetter(verbDu, auxVerbDu, auxVerbiiDu, conj.getDu());
        futureIISetter(verbEr, auxVerbEr, auxVerbiiEr, conj.getEr());
        futureIISetter(verbWir, auxVerbWir, auxVerbiiWir, conj.getWir());
        futureIISetter(verbIhr, auxVerbIhr, auxVerbiiIhr, conj.getIhr());
        futureIISetter(verbSie, auxVerbSie, auxVerbiiSie, conj.getSie());
    }

    private void futureIISetter(TextView verbX, TextView auxVerbX, TextView auxVerbiiX, String conj) {
        String [] parts = conj.split("\\s+");
        auxVerbX.setText(parts[0]);
        verbX.setText(parts[1]);
        auxVerbiiX.setText(parts[2]);
    }

    private void setTenseButtonColor(TextView button, boolean on) {
        setOnOrOff(OFF, prasens, perfekt, plusquamperfekt, prateritum, futureI, futureII);
        setOnOrOff(on, button);
    }

    private void setOnOrOff(boolean on, TextView button) {
        if (on){
            button.setTextColor(Color.parseColor("#dfa80f"));
            button.setBackgroundColor(Color.parseColor("#262626"));
            button.setTextSize(22);
        }
        else{
            button.setTextColor(Color.BLACK);
            button.setBackgroundColor(Color.WHITE);
            button.setTextSize(18);
        }
    }

    private void setOnOrOff(boolean on, TextView ... button){
        for(TextView textView : button){
            setOnOrOff(on, textView);
        }
    }

    public void setHomeObj(HomeActivity homeObj) {
        this.homeObj = homeObj;
    }

    private void setSearchViewOn(boolean on) {
        ImageView clearButton = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        SearchView.SearchAutoComplete searchEditText = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        clearButton.setEnabled(on);
        searchEditText.setEnabled(on);
        searchView.setSubmitButtonEnabled(on);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
      //  outState.putString(SAVED_VERB_OBJ, verb.toString());
    }
}
