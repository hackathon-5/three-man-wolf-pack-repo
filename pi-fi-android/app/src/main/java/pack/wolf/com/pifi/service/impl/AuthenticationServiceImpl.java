package pack.wolf.com.pifi.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.model.AccessToken;
import pack.wolf.com.pifi.model.ErrorResponse;
import pack.wolf.com.pifi.network.GsonFormEncodedRequest;
import pack.wolf.com.pifi.network.GsonRequest;
import pack.wolf.com.pifi.network.VolleyManager;
import pack.wolf.com.pifi.service.api.AuthenticationService;
import pack.wolf.com.pifi.util.ErrorUtil;

/**
 * Created by ryanmoore on 6/8/15.
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    @Override
    public void login(final String userName, final String password, final Context context,
                      final Response.Listener<AccessToken> response, final Dialog dialog) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Grant-Type", "password");

        Map<String, String> params = new HashMap<>();
        params.put(AppConstants.REQUEST_PARAM_GRANT, AppConstants.REQUEST_VALUE_PASSWORD);
        params.put(AppConstants.REQUEST_PARAM_PASSWORD, password);
        params.put(AppConstants.REQUEST_PARAM_USERNAME, userName);
        String url = AppConstants.SERVER_PATH + AppConstants.METHOD_TOKENS;

        GsonFormEncodedRequest<AccessToken> request = new GsonFormEncodedRequest<> (AppConstants.FORM_CONTENT_TYPE, GsonRequest.Method.POST,
                url, AccessToken.class, headers, params, new Response.Listener<AccessToken> () {
            @Override
            public void onResponse (AccessToken accessToken) {
                response.onResponse(accessToken);
            }
        }, new Response.ErrorListener () {
            @Override
            public void onErrorResponse (VolleyError error) {
                dialog.dismiss();
                ErrorResponse errorResponse = ErrorUtil.getErrorMessage(error, context);
                String toastMessage;
                if (errorResponse != null && StringUtils.isNotBlank(errorResponse.getErrorMessage())) {
                    toastMessage = errorResponse.getErrorMessage();
                } else {
                    toastMessage = "bad!";
                }
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        VolleyManager.getInstance().getRequestQueue().add(request);
    }

    @Override
    public void logout(Context context) {

   //stub
    }
}
