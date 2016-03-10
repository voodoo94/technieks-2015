package com.voodoo94.app.technieks2015;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Shreesha on 3/5/2015.
 */
public class NetworkUtility {
    public boolean is_connected(Context context) {
        boolean flag = false;

        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetInfo = mConnectivityManager.getActiveNetworkInfo();

        if (mNetInfo != null && mNetInfo.isConnected()){
            return true;
        }

        return flag;
    }
}
