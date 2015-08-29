package pack.wolf.com.pifi.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import pack.wolf.com.pifi.R;
import pack.wolf.com.pifi.activity.BaseActionBarActivity;
import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.model.User;
import pack.wolf.com.pifi.model.UserRequest;
import pack.wolf.com.pifi.network.GsonRequest;
import pack.wolf.com.pifi.network.VolleyManager;
import pack.wolf.com.pifi.service.api.UserService;
import pack.wolf.com.pifi.util.ContextUtil;
import pack.wolf.com.pifi.util.SharedPreferenceUtil;

/**
 * Created by ryanmoore on 6/8/15.
 */
public class UserServiceImpl implements UserService {

    @Override
    public void getCurrentUser(final Context context, final Response.Listener<User> response, final Dialog dialog) {
//stub
    }

    @Override
    public void createUser(final Context context, final UserRequest user, final Dialog dialog) {

        String url = AppConstants.SERVER_PATH + AppConstants.METHOD_USER;
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);
        GsonRequest<User> request = new GsonRequest<>(AppConstants.JSON_CONTENT_TYPE, GsonRequest.Method.POST, url, User.class, null, jsonBody, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                Log.d(AppConstants.LOG_TAG, "Create user call success..");
                dialog.dismiss();
                ContextUtil.finish(context);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Log.e(AppConstants.LOG_TAG, "Error occurred during sign up call: " + error.getMessage());
                Toast.makeText(context, R.string.error_signup, Toast.LENGTH_SHORT).show();
            }
        });

        VolleyManager.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void updateUser(final Context context, final User user, final Dialog dialog, final Response.Listener<User> responseListener) {

        String url = AppConstants.SERVER_PATH + AppConstants.METHOD_USER + "/" + user.getId();
        Gson gson = new Gson();
        String jsonBody = gson.toJson(user);
        GsonRequest<User> request = new GsonRequest<>(AppConstants.JSON_CONTENT_TYPE, GsonRequest.Method.PUT, url, User.class, null, jsonBody, new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                dialog.dismiss();
                Log.d(AppConstants.LOG_TAG, "Update user call success..");
                SharedPreferenceUtil.saveUser(user);
                Toast.makeText(context, R.string.toast_profile_updated, Toast.LENGTH_SHORT).show();
                responseListener.onResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Log.e(AppConstants.LOG_TAG, "Error occurred during sign up call: " + error.getMessage());
                Toast.makeText(context, R.string.error_signup, Toast.LENGTH_SHORT).show();
            }
        });

        VolleyManager.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void saveProfilePicture(Context context, ByteArrayOutputStream imageStream, User user, final Response.Listener<User> response) {
//
//        String url = AppConstants.SERVER_PATH + AppConstants.METHOD_USER + "/" + user.getId() + AppConstants.METHOD_PHOTO;
//        MultiPartRequest request = new MultiPartRequest(url, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                response.onResponse(null);
//            }
//        }, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String serverResponse) {
//                Gson gson = new Gson();
//                User user = gson.fromJson(serverResponse, User.class);
//                response.onResponse(user);
//            }
//        }, new ByteArrayInputStream(imageStream.toByteArray()), null);
//
//        VolleyManager.getInstance().addToRequestQueue(request, context);
    }

}
