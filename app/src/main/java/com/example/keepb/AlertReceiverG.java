package com.example.keepb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiverG extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {








            NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotificationa();
        notificationHelper.getManager().notify(1, nb.build());




        }
    }


