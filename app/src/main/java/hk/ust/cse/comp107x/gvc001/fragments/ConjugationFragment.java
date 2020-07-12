package hk.ust.cse.comp107x.gvc001.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;


import hk.ust.cse.comp107x.gvc001.R;
import hk.ust.cse.comp107x.gvc001.activities.HomeActivity;
import hk.ust.cse.comp107x.gvc001.verb.Verb;

public class ConjugationFragment extends Fragment {
    public static final String VERB_OBJ_CHECK = "VERB_OBJ_CHECK";
    public static final byte CONJUGATION_FRAGMENT = 0;
    private View view;
    private LinearLayout searchViewContainer;
    private SearchView searchVerb;
    private HomeActivity homeObj;
    private ProgressBar loading;
    public ConjugationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_conjugation, container, false);
        setViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        searchVerb.setQuery("", false);
        searchViewContainer.requestFocus();
    }

    private void setViews() {
        searchViewContainer = view.findViewById(R.id.conjugation_fragment_search_view_container);
        searchVerb = view.findViewById(R.id.result_search_view);
        searchVerb.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Result result = new Result();
                showLoading(true);
                homeObj.searchVerb(query, CONJUGATION_FRAGMENT, result, ConjugationFragment.this);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        loading = view.findViewById(R.id.conjugation_progress_bar);

    }

    public void setHomeObj(HomeActivity homeObj){
        this.homeObj = homeObj;
    }

    public void showLoading(boolean on){
        if(on)
            loading.setVisibility(View.VISIBLE);
        else
            loading.setVisibility(View.GONE);
        setSearchViewOn(!on);
    }

    private void setSearchViewOn(boolean on) {
        ImageView clearButton = (ImageView) searchVerb.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        SearchView.SearchAutoComplete searchEditText = (SearchView.SearchAutoComplete) searchVerb.findViewById(android.support.v7.appcompat.R.id.search_src_text);

        clearButton.setEnabled(on);
        searchEditText.setEnabled(on);
        searchVerb.setSubmitButtonEnabled(on);
    }

}
