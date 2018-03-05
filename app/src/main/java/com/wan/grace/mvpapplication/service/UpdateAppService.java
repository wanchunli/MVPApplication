package com.wan.grace.mvpapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.wan.grace.mvpapplication.R;
import com.wan.grace.mvpapplication.download.DownloadStateListener;
import com.wan.grace.mvpapplication.manager.UpdateManager;
import com.wan.grace.mvpapplication.ui.main.MainActivity;

import rx.Subscriber;

/**
 * 下载apk文件的Service
 * UpdateAppService
 * author wanchun
 * email 1596900283@qq.com
 * create 2018/3/1 16:36
 */
public class UpdateAppService extends Service implements DownloadStateListener {

    public static final int DEFAULT_NOTIFICATION_ID = 1;
    private UpdateManager updateManager;
    public static final String APK_URL = "QQ_794.apk";
    private static final int DOWN_PROGRESS = 2;
    private static final int DOWN_OK = 1;
    private static final int DOWN_ERROR = 0;
    private static final int UPDATE_PROGRESS = 3;
    private NotificationManager notificationManager;
    private Notification notification;
    private Intent updateIntent;
    private PendingIntent pendingIntent;
    private int notification_id = 0;
    private RemoteViews contentView;

    public UpdateAppService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateManager = new UpdateManager(this);
        updateManager.download(APK_URL, new Subscriber() {
            @Override
            public void onCompleted() {
                Toast.makeText(UpdateAppService.this, "完成", Toast.LENGTH_SHORT).show();
//                updateManager.installApk();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onStartDownload() {
        Toast.makeText(UpdateAppService.this, "开始下载", Toast.LENGTH_SHORT).show();
        createNotification();
    }

    @Override
    public void onProgress(int progress) {
        Message msg = new Message();
        msg.what = DOWN_PROGRESS;
        msg.obj = progress;
        handler.handleMessage(msg);
    }

    @Override
    public void onFinishDownload() {
        Message msg = new Message();
        msg.what = DOWN_OK;
        handler.handleMessage(msg);
    }

    @Override
    public void onFail(String errorInfo) {
        Message msg = new Message();
        msg.what = DOWN_ERROR;
        handler.handleMessage(msg);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_PROGRESS:
                    int progress = (int) msg.obj;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Message msg = new Message();
                            msg.what = UPDATE_PROGRESS;
                            msg.obj = progress;
                            handler.sendMessage(msg);
                        }
                    }).start();
                    break;
                case DOWN_OK:
                    Notification.Builder builder = new Notification.Builder(UpdateAppService.this);
                    builder.setSmallIcon(R.mipmap.ic_launcher_round);
                    builder.setContentTitle("APP");
                    builder.setContentText("下载成功,点击安装");
                    builder.setAutoCancel(true);
                    builder.setWhen(System.currentTimeMillis());
                    PendingIntent pendingIntent = PendingIntent.getActivity(UpdateAppService.this, 0, updateManager.getIntentInstallApk(), PendingIntent.FLAG_CANCEL_CURRENT);
                    builder.setContentIntent(pendingIntent);
                    Notification notification_ok = builder.build();
                    notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification_ok);
                    break;
                case DOWN_ERROR:
                    Notification.Builder builder1 = new Notification.Builder(UpdateAppService.this);
                    builder1.setContentText("下载失败！");
                    builder1.setContentTitle("APP");
                    builder1.setSmallIcon(R.mipmap.ic_launcher);
                    builder1.setTicker("新消息");
                    builder1.setAutoCancel(true);
                    builder1.setWhen(System.currentTimeMillis());
                    Notification notification_error = builder1.build();
                    notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification_error);
                    break;
                case UPDATE_PROGRESS:
                    int updateProgress = (int) msg.obj;
                    contentView.setTextViewText(R.id.notificationPercent, updateProgress + "%");
                    contentView.setProgressBar(R.id.notificationProgress, 100, updateProgress, false);
                    notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification);
                    break;
            }
        }
    };

    public void createNotification() {
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notification = new Notification();
        notification.icon = R.mipmap.ic_launcher;
//        Notification.Builder builder = new Notification.Builder(UpdateAppService.this);
//        builder.setContentTitle("App下载");
//        builder.setContentText("App下载");
//        builder.setTicker("App开始下载");//设置通知在第一次到达时在状态栏中显示的文本
//        builder.setVibrate(new long[]{0, 300, 500, 700});//设置使用振动模式
//        builder.setPriority(Notification.PRIORITY_DEFAULT);//设置通知优先级
//        builder.setAutoCancel(true);//当用户点击面板就可以让通知自动取消
//        builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知小Icon
//        builder.setCategory(Notification.CATEGORY_MESSAGE);//设置通知类别
//        builder.setColor(0x0000ff);//设置通知栏颜色
//        PendingIntent pendingIntent = PendingIntent.getActivity(UpdateAppService.this,
//                0, updateManager.getIntentInstallApk(), PendingIntent.FLAG_CANCEL_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        Notification notification = builder.build();
//        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notification);

        contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
        contentView.setTextViewText(R.id.notificationTitle, "loading...");
        contentView.setTextViewText(R.id.notificationPercent, "0%");
        contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
        notification.contentView = contentView;
        updateIntent = new Intent(this, MainActivity.class);
        updateIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, updateIntent, 0);
        notification.contentIntent = pendingIntent;
        notificationManager.notify(notification_id, notification);
    }

}
