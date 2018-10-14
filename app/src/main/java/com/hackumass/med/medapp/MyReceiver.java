package com.hackumass.med.medapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public static boolean CURRENT = true;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("mychannel","Medapp",NotificationManager.IMPORTANCE_HIGH);
            mNotifyMgr.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context,"mychannel")
                .setSmallIcon(R.drawable.lifestyle)
                .setContentTitle("Health Tracker")
                .setContentText("Add your details for today")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        Intent i = new Intent(context,AddActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(context,1,i,0);


        //mBuilder.setContentIntent(pendingIntent);
        mBuilder.addAction(R.drawable.add,"Add",pendingIntent);
        //mBuilder.setContentIntent(pendingIntent);

        mNotifyMgr.notify(1, mBuilder.build());
        Toast.makeText(context,"Add your day !!",Toast.LENGTH_LONG).show();

    }
}
