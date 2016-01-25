package com.gmk.miniproject.app.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by admin on 1/24/16 AD.
 */
public class Utils {

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static boolean checkStringNullEmpty(String stringText) {
        if(stringText != null && !stringText.isEmpty())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
