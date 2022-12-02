package com.example.keepb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiverW extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationHelper notificationHelper = new NotificationHelper(context);

        NotificationCompat.Builder nbb = notificationHelper.getChannelNotificationb();
        notificationHelper.getManager().notify(2, nbb.build());
    }
}
