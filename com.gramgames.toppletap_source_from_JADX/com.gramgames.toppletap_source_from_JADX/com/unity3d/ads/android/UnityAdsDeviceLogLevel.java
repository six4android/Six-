package com.unity3d.ads.android;

public class UnityAdsDeviceLogLevel {
    private static final String LOG_TAG = "UnityAds";
    private String _receivingMethodName;

    public UnityAdsDeviceLogLevel(String str) {
        this._receivingMethodName = null;
        this._receivingMethodName = str;
    }

    public String getLogTag() {
        return LOG_TAG;
    }

    public String getReceivingMethodName() {
        return this._receivingMethodName;
    }
}
