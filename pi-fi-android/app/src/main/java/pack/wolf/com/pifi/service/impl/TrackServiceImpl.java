package pack.wolf.com.pifi.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.model.Track;
import pack.wolf.com.pifi.model.User;
import pack.wolf.com.pifi.model.UserRequest;
import pack.wolf.com.pifi.network.GsonRequest;
import pack.wolf.com.pifi.network.VolleyManager;
import pack.wolf.com.pifi.service.api.TrackService;
import pack.wolf.com.pifi.service.api.UserService;
import pack.wolf.com.pifi.util.ContextUtil;
import pack.wolf.com.pifi.util.SharedPreferenceUtil;

/**
 * Created by Whitney Champion on 8/29/15.
 */
public class TrackServiceImpl implements TrackService {

    @Override
    public void getTrack(final Context context, final Response.Listener<Track> response, final Dialog dialog) {
        //stub
    }

}
