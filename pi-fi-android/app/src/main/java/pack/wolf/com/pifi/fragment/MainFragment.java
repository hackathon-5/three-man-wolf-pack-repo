package pack.wolf.com.pifi.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.volley.Response;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.activity.BaseActionBarActivity;
import pack.wolf.com.pifi.model.Track;
import pack.wolf.com.pifi.service.TrackServiceFactory;
import pack.wolf.com.pifi.util.DialogUtil;

public class MainFragment extends Fragment {

    private static View rootView;
    private Context context;
    ProgressDialog dialog;

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

        // get context
        context = inflater.getContext();
        dialog = DialogUtil.getProgressDialog(context, getString(R.string.signing_in));


        TrackServiceFactory.getInstance().getTrack("2daZovie6pc2ZK7StayD1K", context, new Response.Listener<Track>() {
            @Override
            public void onResponse(Track response) {
                Log.e("meow",response.getName());
            }
        }, dialog);





        // set title
        BaseActionBarActivity.setTitle(getString(R.string.home));

        // get context
        context = inflater.getContext();
        dialog = DialogUtil.getProgressDialog(context, getString(R.string.signing_in));

        // get button, bring to front
        Button startButton = (Button) rootView.findViewById(R.id.startButton);
        startButton.bringToFront();
        startButton.setOnClickListener(new BaseActionBarActivity.FragmentOnClickListener(getString(R.string.search),SearchFragment.newInstance()));


        return rootView;



    }


}
