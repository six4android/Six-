package com.facebook.appevents.internal;

import android.content.Context;
import android.os.Bundle;
import com.facebook.LoggingBehavior;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.Logger;
import java.util.Locale;

class SessionLogger {
    private static final long[] INACTIVE_SECONDS_QUANTA;
    private static final String TAG;

    SessionLogger() {
    }

    static {
        TAG = SessionLogger.class.getCanonicalName();
        INACTIVE_SECONDS_QUANTA = new long[]{300000, 900000, 1800000, 3600000, 21600000, 43200000, 86400000, 172800000, 259200000, 604800000, 1209600000, 1814400000, 2419200000L, 5184000000L, 7776000000L, 10368000000L, 12960000000L, 15552000000L, 31536000000L};
    }

    public static void logActivateApp(Context applicationContext, String activityName, SourceApplicationInfo sourceApplicationInfo, String appId) {
        String sourAppInfoStr = sourceApplicationInfo != null ? sourceApplicationInfo.toString() : "Unclassified";
        Bundle eventParams = new Bundle();
        eventParams.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, sourAppInfoStr);
        new InternalAppEventsLogger(activityName, appId, null).logEvent(AppEventsConstants.EVENT_NAME_ACTIVATED_APP, eventParams);
    }

    public static void logDeactivateApp(Context applicationContext, String activityName, SessionInfo sessionInfo, String appId) {
        Long interruptionDurationMillis = Long.valueOf(sessionInfo.getDiskRestoreTime() - sessionInfo.getSessionLastEventTime().longValue());
        if (interruptionDurationMillis.longValue() < 0) {
            interruptionDurationMillis = Long.valueOf(0);
            logClockSkewEvent();
        }
        Long sessionLength = Long.valueOf(sessionInfo.getSessionLength());
        if (sessionLength.longValue() < 0) {
            logClockSkewEvent();
            sessionLength = Long.valueOf(0);
        }
        Bundle eventParams = new Bundle();
        eventParams.putInt(AppEventsConstants.EVENT_NAME_SESSION_INTERRUPTIONS, sessionInfo.getInterruptionCount());
        eventParams.putString(AppEventsConstants.EVENT_NAME_TIME_BETWEEN_SESSIONS, String.format(Locale.ROOT, "session_quanta_%d", new Object[]{Integer.valueOf(getQuantaIndex(interruptionDurationMillis.longValue()))}));
        SourceApplicationInfo sourceApplicationInfo = sessionInfo.getSourceApplicationInfo();
        eventParams.putString(AppEventsConstants.EVENT_PARAM_SOURCE_APPLICATION, sourceApplicationInfo != null ? sourceApplicationInfo.toString() : "Unclassified");
        eventParams.putLong(Constants.LOG_TIME_APP_EVENT_KEY, sessionInfo.getSessionLastEventTime().longValue() / 1000);
        new InternalAppEventsLogger(activityName, appId, null).logEvent(AppEventsConstants.EVENT_NAME_DEACTIVATED_APP, (double) (sessionLength.longValue() / 1000), eventParams);
    }

    private static void logClockSkewEvent() {
        Logger.log(LoggingBehavior.APP_EVENTS, TAG, "Clock skew detected");
    }

    private static int getQuantaIndex(long timeBetweenSessions) {
        int quantaIndex = 0;
        while (quantaIndex < INACTIVE_SECONDS_QUANTA.length && INACTIVE_SECONDS_QUANTA[quantaIndex] < timeBetweenSessions) {
            quantaIndex++;
        }
        return quantaIndex;
    }
}
