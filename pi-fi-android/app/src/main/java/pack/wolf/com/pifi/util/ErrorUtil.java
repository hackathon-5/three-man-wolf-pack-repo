package pack.wolf.com.pifi.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import pack.wolf.com.pifi.application.AppConstants;
import pack.wolf.com.pifi.model.ErrorResponse;

public class ErrorUtil {

    public static ErrorResponse getErrorMessage(VolleyError volleyError, Context context) {
        Log.e(AppConstants.LOG_TAG, "Parsing error response...");
        if (volleyError.networkResponse != null && volleyError.networkResponse.data != null) {
//
//            if (volleyError.networkResponse.statusCode == 401) {
//                SharedPreferenceUtil.saveAccessToken(null);
//                context.startActivity(new Intent(context, LoginActivity.class));
//            }

            Gson gson = new Gson();
            ErrorResponse response;
            try {
                response = gson.fromJson(new String(volleyError.networkResponse.data), ErrorResponse.class);
            } catch (JsonSyntaxException jse) {
                Log.e(AppConstants.LOG_TAG, "Could not parse error in ErrorResponse");
                return null;
            }
            Log.e(AppConstants.LOG_TAG, "Result: " + response.getErrorMessage());
            return response;
        }
        Log.e(AppConstants.LOG_TAG, "Null or empty error response...");
        return null;
    }
}
