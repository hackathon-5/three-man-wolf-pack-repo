package pack.wolf.com.pifi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pack.wolf.com.pifi.R;

public class TrackFragment extends Fragment {

    private static View rootView;
    private Context context;
    private String title;
    private String artist;
    private String album;

    public static TrackFragment newInstance(String title, String artist, String album) {
        TrackFragment fragment = new TrackFragment();
        Bundle args = new Bundle();
        args.putString("title",title);
        args.putString("artist",artist);
        args.putString("album",album);
        fragment.setArguments(args);
        return fragment;
    }

    public TrackFragment() {
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_track, container, false);
        } catch (InflateException e) {
        }

        // get args
        Bundle bundle = new Bundle();
        artist = bundle.getString("artist");
        title = bundle.getString("title");
        album = bundle.getString("album");

        // get context
        context = inflater.getContext();

        return rootView;

    }


}
