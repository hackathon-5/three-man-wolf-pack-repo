package pack.wolf.com.pifi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.activity.BaseActionBarActivity;
import pack.wolf.com.pifi.util.SharedPreferenceUtil;

public class MainFragment extends Fragment {

    private static View rootView;
    private Context context;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MainFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_main, container, false);
        } catch (InflateException e) {
        }

        // set title
        BaseActionBarActivity.setTitle(getString(R.string.home));

        // get context
        context = inflater.getContext();

        // get button, bring to front
        Button startButton = (Button) rootView.findViewById(R.id.startButton);
        startButton.bringToFront();
        startButton.setOnClickListener(new BaseActionBarActivity.FragmentOnClickListener(getString(R.string.search),SearchFragment.newInstance()));

        return rootView;



    }


}
