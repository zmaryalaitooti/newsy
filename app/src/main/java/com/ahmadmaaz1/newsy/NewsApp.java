package com.ahmadmaaz1.newsy;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.ahmadmaaz1.notification.NotificationScheduler;
import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.Executors;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class NewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Notification Scheduler
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED)
            NotificationScheduler.INSTANCE.scheduleDailyNotification(this);

        Executors.newSingleThreadExecutor().execute(() -> {
            // Background work here
            Log.d("TAG", "onCreate: initialize the ads ");
            MobileAds.initialize(this);
        });

    }
}
