package com.example.keepb;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {
    public static final String channelID = "channelID";
    public static final String channelName = "Γευματα";
    public static final String  channelWaterId="channelWater";
    public static final String  channelNeroName ="Νερο";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createChannel() {
        Uri soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+ getApplicationContext().getPackageName() + "/" + R.raw.watersound);
        NotificationChannel channel1 = new NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH);
        channel1.getLockscreenVisibility();
        getManager().createNotificationChannel(channel1);


        NotificationChannel channel2 = new NotificationChannel(channelWaterId, channelNeroName, NotificationManager.IMPORTANCE_HIGH);
        channel2.getLockscreenVisibility();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();
        channel2.setSound(soundUri,audioAttributes);
        getManager().createNotificationChannel(channel2);






    }





    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannelNotificationa() {
        return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle("Ωρα για φαγητό")
                .setContentText("Το επομενο γευμα σας ειναι σε μιση ωρα")
                .setSmallIcon(R.drawable.image)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.front))
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setVibrate(new long[]{0, 500, 1000})


                ;



    }
    public NotificationCompat.Builder getChannelNotificationb() {


        return new NotificationCompat.Builder(getApplicationContext(), channelWaterId)
                .setContentTitle("Νερο")
                .setContentText("Ενα ακόμη ποτήρι στην υγειά σας")
                .setSmallIcon(R.drawable.notiw)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.front))
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSound(Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE+ "://" +getPackageName()+"/"+R.raw.watersound))


                .setVibrate(new long[]{0, 500, 1000})

                ;



    }

}