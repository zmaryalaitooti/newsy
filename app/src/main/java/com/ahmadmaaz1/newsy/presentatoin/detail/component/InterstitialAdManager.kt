package com.ahmadmaaz1.newsy.presentatoin.detail.component

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object InterstitialAdManager {

    private var mInterstitialAd: InterstitialAd? = null
    private const val TAG = "InterstitialAd"

    fun loadAd(activity: Activity) {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(
            activity,
            "ca-app-pub-8992718827220232/4006488689", // ✅ read interstitial ad ID
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.e(TAG, "Ad failed to load: ${adError.message}")
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad loaded successfully")
                    mInterstitialAd = interstitialAd
                }
            }
        )
    }

    fun showAd(activity: Activity, onAdClosed: () -> Unit = {} ,onAdsShow:(Int)-> Unit) {
        mInterstitialAd?.let { ad ->
            ad.show(activity)
            mInterstitialAd = null
            loadAd(activity) // Preload next ad
            onAdClosed()
            onAdsShow.invoke(1)
        } ?: run {
            Log.d(TAG, "Interstitial ad not ready yet.")
            onAdClosed()
        }
    }
}
