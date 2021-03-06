package com.mopub.nativeads;

import android.os.SystemClock;
import android.support.annotation.NonNull;

class TimestampWrapper<T> {
    long mCreatedTimestamp;
    @NonNull
    final T mInstance;

    TimestampWrapper(@NonNull T instance) {
        this.mInstance = instance;
        this.mCreatedTimestamp = SystemClock.uptimeMillis();
    }
}
