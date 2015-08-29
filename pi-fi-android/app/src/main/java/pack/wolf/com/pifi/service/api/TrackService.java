package pack.wolf.com.pifi.service.api;

import android.app.Dialog;
import android.content.Context;

import com.android.volley.Response;

import java.io.ByteArrayOutputStream;

import pack.wolf.com.pifi.model.SearchType;
import pack.wolf.com.pifi.model.Track;
import pack.wolf.com.pifi.model.User;
import pack.wolf.com.pifi.model.UserRequest;

public interface TrackService {

    void getTrack(String track_id, Context context, Response.Listener<Track> response, Dialog dialog);

    void searchTracks(String query, SearchType searchType, Context context, Response.Listener<Track> response, Dialog dialog);

}

