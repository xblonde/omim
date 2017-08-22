package com.mapswithme.maps.search;

import android.support.annotation.NonNull;

import com.facebook.ads.AdView;
import com.google.android.gms.ads.search.SearchAdView;

public class GoogleAdsBanner implements SearchData
{

  @NonNull
  private SearchAdView adView;

  public GoogleAdsBanner(@NonNull SearchAdView adView) {
    this.adView = adView;
  }

  @NonNull
  public SearchAdView getAdView() {
    return adView;
  }

}
