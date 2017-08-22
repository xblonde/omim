package com.mapswithme.maps.search;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.search.DynamicHeightSearchAdRequest;
import com.google.android.gms.ads.search.SearchAdView;
import com.mapswithme.util.concurrency.UiThread;

class GoogleAdsLoader {

  private String adUnitId;
  private long loadingDelay;
  private Runnable loadingTask;

  GoogleAdsLoader(String adUnitId, long loadingDelay) {
    this.adUnitId = adUnitId;
    this.loadingDelay = loadingDelay;
  }

  void scheduleAdsLoading(final Context context, final String query, final AdvertLoadingListener loadingListener) {
    cancelAdsLoading();
    loadingTask = new Runnable() {
      @Override
      public void run() {
        performLoading(context, query, loadingListener);
      }
    };
    UiThread.runLater(loadingTask, loadingDelay);
  }

  void cancelAdsLoading() {
    if (loadingTask != null) {
      UiThread.cancelDelayedTasks(loadingTask);
    }
  }

  private void performLoading(Context context, String query, final AdvertLoadingListener loadingListener)
  {
    // Create a search ad. The ad size and ad unit ID must be set before calling loadAd.
    final SearchAdView view = new SearchAdView(context);
    view.setAdSize(AdSize.SEARCH);
    view.setAdUnitId(adUnitId);

    // Create an ad request.
    DynamicHeightSearchAdRequest.Builder builder =
      new DynamicHeightSearchAdRequest.Builder();

    // Set the query.
    builder.setQuery(query);

    // Optionally populate the ad request builder.
    builder.setAdTest(true);
    builder.setNumber(2);
    builder.setCssWidth(300);     // Equivalent to "width" CSA parameter.

    // Start loading the ad.
    view.loadAd(builder.build());

    view.setAdListener(new AdListener() {

      @Override
      public void onAdLoaded() {
        loadingTask = null;
        loadingListener.onLoadingFinished(view);
      }

      @Override
      public void onAdFailedToLoad(int i) {
        loadingTask = null;
      }
    });
  }

  interface AdvertLoadingListener {

    void onLoadingFinished(SearchAdView searchAdView);

  }

}
