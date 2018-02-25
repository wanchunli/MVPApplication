package com.wan.grace.mvpapplication.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 开发部 on 2018/2/23.
 */

public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("网络状态发生变化");
        //检测API是不是小于21，因为到了API21之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
            }
        } else {
            //这里的就不写了，前面有写，大同小异
            System.out.println("API level 大于21");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            //获取所有网络连接的信息
            Network[] networks = connMgr.getAllNetworks();
            //用于存放网络连接信息
            StringBuilder sb = new StringBuilder();
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = connMgr.getNetworkInfo(networks[i]);
                sb.append(networkInfo.getTypeName() + " connect is " + networkInfo.isConnected());
            }
            Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
        }
    }

//    private static final String TAG = "xujun";
//    public static final String TAG1 = "xxx";

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        // 这个监听wifi的打开与关闭，与wifi的连接无关
//        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
//            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
//            Log.e(TAG1, "wifiState" + wifiState);
//            switch (wifiState) {
//                case WifiManager.WIFI_STATE_DISABLED:
//                    APP.getInstance().setEnablaWifi(false);
//                    break;
//                case WifiManager.WIFI_STATE_DISABLING:
//
//                    break;
//                case WifiManager.WIFI_STATE_ENABLING:
//                    break;
//                case WifiManager.WIFI_STATE_ENABLED:
//                    APP.getInstance().setEnablaWifi(true);
//                    break;
//                case WifiManager.WIFI_STATE_UNKNOWN:
//                    break;
//                default:
//                    break;
//
//
//            }
//        }
//        // 这个监听wifi的连接状态即是否连上了一个有效无线路由，当上边广播的状态是WifiManager
//        // .WIFI_STATE_DISABLING，和WIFI_STATE_DISABLED的时候，根本不会接到这个广播。
//        // 在上边广播接到广播是WifiManager.WIFI_STATE_ENABLED状态的同时也会接到这个广播，
//        // 当然刚打开wifi肯定还没有连接到有效的无线
//        if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
//            Parcelable parcelableExtra = intent
//                    .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
//            if (null != parcelableExtra) {
//                NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
//                State state = networkInfo.getState();
//                boolean isConnected = state == State.CONNECTED;// 当然，这边可以更精确的确定状态
//                Log.e(TAG1, "isConnected" + isConnected);
//                if (isConnected) {
//                    APP.getInstance().setWifi(true);
//                } else {
//                    APP.getInstance().setWifi(false);
//                }
//            }
//        }
//        // 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
//        // 最好用的还是这个监听。wifi如果打开，关闭，以及连接上可用的连接都会接到监听。见log
//        // 这个广播的最大弊端是比上边两个广播的反应要慢，如果只是要监听wifi，我觉得还是用上边两个配合比较合适
//        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
//            ConnectivityManager manager = (ConnectivityManager) context
//                    .getSystemService(Context.CONNECTIVITY_SERVICE);
//            Log.i(TAG1, "CONNECTIVITY_ACTION");
//
//            NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
//            if (activeNetwork != null) { // connected to the internet
//                if (activeNetwork.isConnected()) {
//                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
//                        // connected to wifi
//                        APP.getInstance().setWifi(true);
//                        Log.e(TAG, "当前WiFi连接可用 ");
//                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
//                        // connected to the mobile provider's data plan
//                        APP.getInstance().setMobile(true);
//                        Log.e(TAG, "当前移动网络连接可用 ");
//                    }
//                } else {
//                    Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
//                }
//
//
//                Log.e(TAG1, "info.getTypeName()" + activeNetwork.getTypeName());
//                Log.e(TAG1, "getSubtypeName()" + activeNetwork.getSubtypeName());
//                Log.e(TAG1, "getState()" + activeNetwork.getState());
//                Log.e(TAG1, "getDetailedState()"
//                        + activeNetwork.getDetailedState().name());
//                Log.e(TAG1, "getDetailedState()" + activeNetwork.getExtraInfo());
//                Log.e(TAG1, "getType()" + activeNetwork.getType());
//            } else {   // not connected to the internet
//                Log.e(TAG, "当前没有网络连接，请确保你已经打开网络 ");
//                APP.getInstance().setWifi(false);
//                APP.getInstance().setMobile(false);
//                APP.getInstance().setConnected(false);
//
//            }
//        }
//    }
}
