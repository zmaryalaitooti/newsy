package com.ahmadmaaz1.newsy.presentatoin.component

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.widget.ConstraintLayout
import com.ahmadmaaz1.newsy.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoController
import com.google.android.gms.ads.nativead.MediaView
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView



@Composable
fun NativeAdComposable() {
    AndroidView(
        modifier = Modifier.fillMaxWidth(),
        factory = { context ->
            val inflater = LayoutInflater.from(context)
            val adView = inflater.inflate(R.layout.native_ads_layout, null) as NativeAdView

            val adLoader = AdLoader.Builder(
                context,
                "ca-app-pub-8992718827220232/8888932664" // ✅ Test Native Ad ID
            )
                .forNativeAd { nativeAd ->
                    populateNativeAdView(nativeAd, adView)
                }
                .withAdListener(object : AdListener() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        // Optional: Log error
                    }

                    override fun onAdClicked() {
                        super.onAdClicked()
                    }
                    override fun onAdClosed() {
                        super.onAdClosed()
                    }
                })
                .withNativeAdOptions(NativeAdOptions.Builder().build())
                .build()

            adLoader.loadAd(AdRequest.Builder().build())
            adView
        }
    )
}










private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
    val mediaView = adView.findViewById<MediaView>(R.id.ad_media)
    val iconView = adView.findViewById<ImageView>(R.id.image_ads)
    val titleView = adView.findViewById<TextView>(R.id.ads_title)
    val bodyView = adView.findViewById<TextView>(R.id.ads_body)
    val ctaView = adView.findViewById<TextView>(R.id.ads_call_to_action)

    adView.mediaView = mediaView
    adView.iconView = iconView
    adView.headlineView = titleView
    adView.bodyView = bodyView
    adView.callToActionView = ctaView

    // Set texts
    titleView.text = nativeAd.headline
    bodyView.text = nativeAd.body
    ctaView.text = nativeAd.callToAction
    adView.setNativeAd(nativeAd)

    // Handle icon
    nativeAd.icon?.let {
        iconView.setImageDrawable(it.drawable)
        iconView.visibility = View.VISIBLE
    } ?: run { iconView.visibility = View.GONE }

    // Adjust square size dynamically
    val sizeDp = if (nativeAd.mediaContent != null &&
        (nativeAd.mediaContent?.hasVideoContent() == true || nativeAd.mediaContent?.mainImage != null)
    ) 180 else 40

    val params = mediaView.layoutParams
    params.width = dpToPx(adView.context, sizeDp)
    params.height = dpToPx(adView.context, sizeDp)
    mediaView.layoutParams = params

    // Assign media content if exists
    nativeAd.mediaContent?.let { mediaView.setMediaContent(it) }
}

// Helper function to convert dp to pixels
private fun dpToPx(context: Context, dp: Int): Int {
    return (dp * context.resources.displayMetrics.density).toInt()
}





