package com.ahmadmaaz1.newsy;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;

import java.util.concurrent.Executors;

import dagger.hilt.android.HiltAndroidApp;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DispatchedCoroutine;

@HiltAndroidApp
public class NewsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Executors.newSingleThreadExecutor().execute(() -> {
            // Background work here
            Log.d("TAG", "onCreate: initialize the ads ");
            MobileAds.initialize(this);
        });

    }
}
