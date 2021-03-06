package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.UrlHandler.ResultActions;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.common.util.Intents;
import com.mopub.common.util.Strings;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.network.TrackingRequest;
import gs.gram.mopub.BuildConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class VastVideoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    @NonNull
    private final ArrayList<VastAbsoluteProgressTracker> mAbsoluteTrackers;
    @Nullable
    private String mClickThroughUrl;
    @NonNull
    private final ArrayList<VastTracker> mClickTrackers;
    @NonNull
    private final ArrayList<VastTracker> mCloseTrackers;
    @NonNull
    private final ArrayList<VastTracker> mCompleteTrackers;
    @Nullable
    private String mCustomCloseIconUrl;
    @Nullable
    private String mCustomCtaText;
    @NonNull
    private ForceOrientation mCustomForceOrientation;
    @Nullable
    private String mCustomSkipText;
    @Nullable
    private String mDiskMediaFileUrl;
    private String mDspCreativeId;
    @NonNull
    private final ArrayList<VastTracker> mErrorTrackers;
    @NonNull
    private final ArrayList<VastFractionalProgressTracker> mFractionalTrackers;
    @NonNull
    private final ArrayList<VastTracker> mImpressionTrackers;
    private boolean mIsForceOrientationSet;
    private boolean mIsRewardedVideo;
    @Nullable
    private VastCompanionAdConfig mLandscapeVastCompanionAdConfig;
    @Nullable
    private String mNetworkMediaFileUrl;
    @NonNull
    private final ArrayList<VastTracker> mPauseTrackers;
    @Nullable
    private VastCompanionAdConfig mPortraitVastCompanionAdConfig;
    @NonNull
    private final ArrayList<VastTracker> mResumeTrackers;
    @Nullable
    private String mSkipOffset;
    @NonNull
    private final ArrayList<VastTracker> mSkipTrackers;
    @NonNull
    private Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds;
    @Nullable
    private VastIconConfig mVastIconConfig;
    @Nullable
    private VideoViewabilityTracker mVideoViewabilityTracker;

    class 1 implements ResultActions {
        final /* synthetic */ Context val$context;
        final /* synthetic */ Integer val$requestCode;

        1(Context context, Integer num) {
            this.val$context = context;
            this.val$requestCode = num;
        }

        public void urlHandlingSucceeded(@NonNull String url, @NonNull UrlAction urlAction) {
            if (urlAction == UrlAction.OPEN_IN_APP_BROWSER) {
                Bundle bundle = new Bundle();
                bundle.putString(MoPubBrowser.DESTINATION_URL_KEY, url);
                bundle.putString(MoPubBrowser.DSP_CREATIVE_ID, VastVideoConfig.this.mDspCreativeId);
                Class clazz = MoPubBrowser.class;
                Intent intent = Intents.getStartActivityIntent(this.val$context, clazz, bundle);
                try {
                    if (this.val$context instanceof Activity) {
                        Preconditions.checkNotNull(this.val$requestCode);
                        ((Activity) this.val$context).startActivityForResult(intent, this.val$requestCode.intValue());
                        return;
                    }
                    Intents.startActivity(this.val$context, intent);
                } catch (ActivityNotFoundException e) {
                    MoPubLog.d("Activity " + clazz.getName() + " not found. Did you " + "declare it in your AndroidManifest.xml?");
                } catch (IntentNotResolvableException e2) {
                    MoPubLog.d("Activity " + clazz.getName() + " not found. Did you " + "declare it in your AndroidManifest.xml?");
                }
            }
        }

        public void urlHandlingFailed(@NonNull String url, @NonNull UrlAction lastFailedUrlAction) {
        }
    }

    public VastVideoConfig() {
        this.mCustomForceOrientation = ForceOrientation.FORCE_LANDSCAPE;
        this.mImpressionTrackers = new ArrayList();
        this.mFractionalTrackers = new ArrayList();
        this.mAbsoluteTrackers = new ArrayList();
        this.mPauseTrackers = new ArrayList();
        this.mResumeTrackers = new ArrayList();
        this.mCompleteTrackers = new ArrayList();
        this.mCloseTrackers = new ArrayList();
        this.mSkipTrackers = new ArrayList();
        this.mClickTrackers = new ArrayList();
        this.mErrorTrackers = new ArrayList();
        this.mSocialActionsCompanionAds = new HashMap();
        this.mIsRewardedVideo = false;
    }

    public void setDspCreativeId(@NonNull String dspCreativeId) {
        this.mDspCreativeId = dspCreativeId;
    }

    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    public void addImpressionTrackers(@NonNull List<VastTracker> impressionTrackers) {
        Preconditions.checkNotNull(impressionTrackers, "impressionTrackers cannot be null");
        this.mImpressionTrackers.addAll(impressionTrackers);
    }

    public void addFractionalTrackers(@NonNull List<VastFractionalProgressTracker> fractionalTrackers) {
        Preconditions.checkNotNull(fractionalTrackers, "fractionalTrackers cannot be null");
        this.mFractionalTrackers.addAll(fractionalTrackers);
        Collections.sort(this.mFractionalTrackers);
    }

    public void addAbsoluteTrackers(@NonNull List<VastAbsoluteProgressTracker> absoluteTrackers) {
        Preconditions.checkNotNull(absoluteTrackers, "absoluteTrackers cannot be null");
        this.mAbsoluteTrackers.addAll(absoluteTrackers);
        Collections.sort(this.mAbsoluteTrackers);
    }

    public void addCompleteTrackers(@NonNull List<VastTracker> completeTrackers) {
        Preconditions.checkNotNull(completeTrackers, "completeTrackers cannot be null");
        this.mCompleteTrackers.addAll(completeTrackers);
    }

    public void addPauseTrackers(@NonNull List<VastTracker> pauseTrackers) {
        Preconditions.checkNotNull(pauseTrackers, "pauseTrackers cannot be null");
        this.mPauseTrackers.addAll(pauseTrackers);
    }

    public void addResumeTrackers(@NonNull List<VastTracker> resumeTrackers) {
        Preconditions.checkNotNull(resumeTrackers, "resumeTrackers cannot be null");
        this.mResumeTrackers.addAll(resumeTrackers);
    }

    public void addCloseTrackers(@NonNull List<VastTracker> closeTrackers) {
        Preconditions.checkNotNull(closeTrackers, "closeTrackers cannot be null");
        this.mCloseTrackers.addAll(closeTrackers);
    }

    public void addSkipTrackers(@NonNull List<VastTracker> skipTrackers) {
        Preconditions.checkNotNull(skipTrackers, "skipTrackers cannot be null");
        this.mSkipTrackers.addAll(skipTrackers);
    }

    public void addClickTrackers(@NonNull List<VastTracker> clickTrackers) {
        Preconditions.checkNotNull(clickTrackers, "clickTrackers cannot be null");
        this.mClickTrackers.addAll(clickTrackers);
    }

    public void addErrorTrackers(@NonNull List<VastTracker> errorTrackers) {
        Preconditions.checkNotNull(errorTrackers, "errorTrackers cannot be null");
        this.mErrorTrackers.addAll(errorTrackers);
    }

    public void setClickThroughUrl(@Nullable String clickThroughUrl) {
        this.mClickThroughUrl = clickThroughUrl;
    }

    public void setNetworkMediaFileUrl(@Nullable String networkMediaFileUrl) {
        this.mNetworkMediaFileUrl = networkMediaFileUrl;
    }

    public void setDiskMediaFileUrl(@Nullable String diskMediaFileUrl) {
        this.mDiskMediaFileUrl = diskMediaFileUrl;
    }

    public void setVastCompanionAd(@Nullable VastCompanionAdConfig landscapeVastCompanionAdConfig, @Nullable VastCompanionAdConfig portraitVastCompanionAdConfig) {
        this.mLandscapeVastCompanionAdConfig = landscapeVastCompanionAdConfig;
        this.mPortraitVastCompanionAdConfig = portraitVastCompanionAdConfig;
    }

    public void setSocialActionsCompanionAds(@NonNull Map<String, VastCompanionAdConfig> socialActionsCompanionAds) {
        this.mSocialActionsCompanionAds = socialActionsCompanionAds;
    }

    public void setVastIconConfig(@Nullable VastIconConfig vastIconConfig) {
        this.mVastIconConfig = vastIconConfig;
    }

    public void setCustomCtaText(@Nullable String customCtaText) {
        if (customCtaText != null) {
            this.mCustomCtaText = customCtaText;
        }
    }

    public void setCustomSkipText(@Nullable String customSkipText) {
        if (customSkipText != null) {
            this.mCustomSkipText = customSkipText;
        }
    }

    public void setCustomCloseIconUrl(@Nullable String customCloseIconUrl) {
        if (customCloseIconUrl != null) {
            this.mCustomCloseIconUrl = customCloseIconUrl;
        }
    }

    public void setCustomForceOrientation(@Nullable ForceOrientation customForceOrientation) {
        if (customForceOrientation != null && customForceOrientation != ForceOrientation.UNDEFINED) {
            this.mCustomForceOrientation = customForceOrientation;
            this.mIsForceOrientationSet = true;
        }
    }

    public void setSkipOffset(@Nullable String skipOffset) {
        if (skipOffset != null) {
            this.mSkipOffset = skipOffset;
        }
    }

    public void setVideoViewabilityTracker(@Nullable VideoViewabilityTracker videoViewabilityTracker) {
        if (videoViewabilityTracker != null) {
            this.mVideoViewabilityTracker = videoViewabilityTracker;
        }
    }

    public void setIsRewardedVideo(boolean isRewardedVideo) {
        this.mIsRewardedVideo = isRewardedVideo;
    }

    @NonNull
    public List<VastTracker> getImpressionTrackers() {
        return this.mImpressionTrackers;
    }

    @NonNull
    public ArrayList<VastAbsoluteProgressTracker> getAbsoluteTrackers() {
        return this.mAbsoluteTrackers;
    }

    @NonNull
    public ArrayList<VastFractionalProgressTracker> getFractionalTrackers() {
        return this.mFractionalTrackers;
    }

    @NonNull
    public List<VastTracker> getPauseTrackers() {
        return this.mPauseTrackers;
    }

    @NonNull
    public List<VastTracker> getResumeTrackers() {
        return this.mResumeTrackers;
    }

    @NonNull
    public List<VastTracker> getCompleteTrackers() {
        return this.mCompleteTrackers;
    }

    @NonNull
    public List<VastTracker> getCloseTrackers() {
        return this.mCloseTrackers;
    }

    @NonNull
    public List<VastTracker> getSkipTrackers() {
        return this.mSkipTrackers;
    }

    @NonNull
    public List<VastTracker> getClickTrackers() {
        return this.mClickTrackers;
    }

    @NonNull
    public List<VastTracker> getErrorTrackers() {
        return this.mErrorTrackers;
    }

    @Nullable
    public String getClickThroughUrl() {
        return this.mClickThroughUrl;
    }

    @Nullable
    public String getNetworkMediaFileUrl() {
        return this.mNetworkMediaFileUrl;
    }

    @Nullable
    public String getDiskMediaFileUrl() {
        return this.mDiskMediaFileUrl;
    }

    @Nullable
    public VastCompanionAdConfig getVastCompanionAd(int orientation) {
        switch (orientation) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return this.mPortraitVastCompanionAdConfig;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return this.mLandscapeVastCompanionAdConfig;
            default:
                return this.mLandscapeVastCompanionAdConfig;
        }
    }

    @NonNull
    public Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds() {
        return this.mSocialActionsCompanionAds;
    }

    @Nullable
    public VastIconConfig getVastIconConfig() {
        return this.mVastIconConfig;
    }

    @Nullable
    public String getCustomCtaText() {
        return this.mCustomCtaText;
    }

    @Nullable
    public String getCustomSkipText() {
        return this.mCustomSkipText;
    }

    @Nullable
    public String getCustomCloseIconUrl() {
        return this.mCustomCloseIconUrl;
    }

    @Nullable
    public VideoViewabilityTracker getVideoViewabilityTracker() {
        return this.mVideoViewabilityTracker;
    }

    public boolean isCustomForceOrientationSet() {
        return this.mIsForceOrientationSet;
    }

    public boolean hasCompanionAd() {
        return (this.mLandscapeVastCompanionAdConfig == null || this.mPortraitVastCompanionAdConfig == null) ? false : true;
    }

    @NonNull
    public ForceOrientation getCustomForceOrientation() {
        return this.mCustomForceOrientation;
    }

    @Nullable
    public String getSkipOffsetString() {
        return this.mSkipOffset;
    }

    public boolean isRewardedVideo() {
        return this.mIsRewardedVideo;
    }

    public void handleImpression(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mImpressionTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    public void handleClickForResult(@NonNull Activity activity, int contentPlayHead, int requestCode) {
        handleClick(activity, contentPlayHead, Integer.valueOf(requestCode));
    }

    public void handleClickWithoutResult(@NonNull Context context, int contentPlayHead) {
        handleClick(context.getApplicationContext(), contentPlayHead, null);
    }

    private void handleClick(@NonNull Context context, int contentPlayHead, @Nullable Integer requestCode) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mClickTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
        if (!TextUtils.isEmpty(this.mClickThroughUrl)) {
            new Builder().withDspCreativeId(this.mDspCreativeId).withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_APP_MARKET, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).withResultActions(new 1(context, requestCode)).withoutMoPubBrowser().build().handleUrl(context, this.mClickThroughUrl);
        }
    }

    public void handleResume(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mResumeTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    public void handlePause(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mPauseTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    public void handleClose(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCloseTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
        TrackingRequest.makeVastTrackingHttpRequest(this.mSkipTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    public void handleComplete(@NonNull Context context, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mCompleteTrackers, null, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    public void handleError(@NonNull Context context, @Nullable VastErrorCode errorCode, int contentPlayHead) {
        Preconditions.checkNotNull(context, "context cannot be null");
        TrackingRequest.makeVastTrackingHttpRequest(this.mErrorTrackers, errorCode, Integer.valueOf(contentPlayHead), this.mNetworkMediaFileUrl, context);
    }

    @NonNull
    public List<VastTracker> getUntriggeredTrackersBefore(int currentPositionMillis, int videoLengthMillis) {
        if (!NoThrow.checkArgument(videoLengthMillis > 0)) {
            return Collections.emptyList();
        }
        int i;
        float progressFraction = ((float) currentPositionMillis) / ((float) videoLengthMillis);
        List<VastTracker> untriggeredTrackers = new ArrayList();
        VastAbsoluteProgressTracker absoluteTest = new VastAbsoluteProgressTracker(BuildConfig.FLAVOR, currentPositionMillis);
        int absoluteTrackerCount = this.mAbsoluteTrackers.size();
        for (i = 0; i < absoluteTrackerCount; i++) {
            VastAbsoluteProgressTracker tracker = (VastAbsoluteProgressTracker) this.mAbsoluteTrackers.get(i);
            if (tracker.compareTo(absoluteTest) > 0) {
                break;
            }
            if (!tracker.isTracked()) {
                untriggeredTrackers.add(tracker);
            }
        }
        VastFractionalProgressTracker fractionalTest = new VastFractionalProgressTracker(BuildConfig.FLAVOR, progressFraction);
        int fractionalTrackerCount = this.mFractionalTrackers.size();
        for (i = 0; i < fractionalTrackerCount; i++) {
            VastFractionalProgressTracker tracker2 = (VastFractionalProgressTracker) this.mFractionalTrackers.get(i);
            if (tracker2.compareTo(fractionalTest) > 0) {
                return untriggeredTrackers;
            }
            if (!tracker2.isTracked()) {
                untriggeredTrackers.add(tracker2);
            }
        }
        return untriggeredTrackers;
    }

    public int getRemainingProgressTrackerCount() {
        return getUntriggeredTrackersBefore(MoPubClientPositioning.NO_REPEAT, MoPubClientPositioning.NO_REPEAT).size();
    }

    @Nullable
    public Integer getSkipOffsetMillis(int videoDuration) {
        if (this.mSkipOffset != null) {
            try {
                Integer skipOffsetMilliseconds;
                if (Strings.isAbsoluteTracker(this.mSkipOffset)) {
                    skipOffsetMilliseconds = Strings.parseAbsoluteOffset(this.mSkipOffset);
                } else if (Strings.isPercentageTracker(this.mSkipOffset)) {
                    skipOffsetMilliseconds = Integer.valueOf(Math.round(((float) videoDuration) * (Float.parseFloat(this.mSkipOffset.replace("%", BuildConfig.FLAVOR)) / 100.0f)));
                } else {
                    MoPubLog.d(String.format("Invalid VAST skipoffset format: %s", new Object[]{this.mSkipOffset}));
                    return null;
                }
                if (skipOffsetMilliseconds != null) {
                    if (skipOffsetMilliseconds.intValue() < videoDuration) {
                        return skipOffsetMilliseconds;
                    }
                    return Integer.valueOf(videoDuration);
                }
            } catch (NumberFormatException e) {
                MoPubLog.d(String.format("Failed to parse skipoffset %s", new Object[]{this.mSkipOffset}));
            }
        }
        return null;
    }
}
