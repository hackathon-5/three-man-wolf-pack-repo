package pack.wolf.com.pifi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.activity.BaseActionBarActivity;
import pack.wolf.com.pifi.application.AppConstants;

public class SearchFragment extends Fragment {

    private static View rootView;
    private Context context;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_search, container, false);
        } catch (InflateException e) {
        }

        // hide title bar
        BaseActionBarActivity.hideTitleBar();

        // color search view
        SearchView searchView = (SearchView) rootView.findViewById(R.id.search_view);
        searchView.setBackgroundColor(getResources().getColor(R.color.light_green));

        // get context
        context = inflater.getContext();

        return rootView;

    }


}
