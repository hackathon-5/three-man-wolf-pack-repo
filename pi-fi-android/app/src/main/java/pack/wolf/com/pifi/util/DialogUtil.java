package pack.wolf.com.pifi.util;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import pack.wolf.com.pifi.application.AppConstants;

public class DialogUtil {

    public static AlertDialog.Builder getNeutralDialog(Context context, String title, String message,
                                                       DialogInterface.OnClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("OK", listener);
        return builder;
    }

    public static ProgressDialog getProgressDialog(Context context, String message) {
//stub
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setCanceledOnTouchOutside(false);
//        IndeterminateProgressDrawable drawable = new IndeterminateProgressDrawable(context);
//        drawable.setTint(context.getResources().getColor(R.color.purple_light));
//        progressDialog.setIndeterminateDrawable(drawable);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(message);
        return progressDialog;
    }

    public static int determineProgressPercentage(long currentUploaded, long fileSize) {
        float percent;
        Log.d(AppConstants.LOG_TAG, "currentUploaded: "+ currentUploaded);
        Log.d(AppConstants.LOG_TAG, "fileSize: "+ fileSize);
        if (currentUploaded == 0 || fileSize == 0) {
            percent = 0;
        } else {
            percent = ((float)currentUploaded) / ((float)fileSize);
        }
        Log.d(AppConstants.LOG_TAG, "percent: "+ Math.round(percent * 100));
        Log.d(AppConstants.LOG_TAG, "decimal: "+ percent);
        return Math.round(percent * 100);
    }
}
