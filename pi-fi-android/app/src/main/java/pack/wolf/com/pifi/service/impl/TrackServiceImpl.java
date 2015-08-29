package pack.wolf.com.pifi.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;

import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.model.SearchType;
import pack.wolf.com.pifi.model.Track;
import pack.wolf.com.pifi.network.GsonRequest;
import pack.wolf.com.pifi.service.api.TrackService;

/**
 * Created by Whitney Champion on 8/29/15.
 */
public class TrackServiceImpl implements TrackService {

    @Override
    public void getTrack(String track_id, final Context context, final Response.Listener<Track> response, final Dialog dialog) {
        String url = AppConstants.SERVER_PATH + AppConstants.METHOD_TRACK + "/" + track_id;
        Gson gson = new Gson();
        /*String jsonBody = gson.toJson(user);
        GsonRequest<User> request = new GsonRequest<>(AppConstants.JSON_CONTENT_TYPE, GsonRequest.Method.GET, url, String.class, null, jsonBody, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                dialog.dismiss();
                Log.d(AppConstants.LOG_TAG, "Get track call success...");
                responseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Log.e(AppConstants.LOG_TAG, "Error occurred during get track call: " + error.getMessage());
            }
        });*/
    }

    @Override
    public void searchTracks(String query, SearchType searchType, final Context context, final Response.Listener<Track> response, final Dialog dialog) {
        StringBuilder url = new StringBuilder(AppConstants.SERVER_PATH + AppConstants.METHOD_SEARCH + "?query=");
        url.append(query);
        if(searchType != null) {
            url.append(searchType.name());
        }
        Gson gson = new Gson();

//        GsonRequest<User> request = new GsonRequest<>(null, GsonRequest.Method.GET, url, String.class, null, null, new Response.Listener<User>() {
//            @Override
//            public void onResponse(User response) {
//                dialog.dismiss();
//                Log.d(AppConstants.LOG_TAG, "Get track call success...");
//                responseListener.onResponse(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                dialog.dismiss();
//                Log.e(AppConstants.LOG_TAG, "Error occurred during get track call: " + error.getMessage());
//            }
//        });
    }

}