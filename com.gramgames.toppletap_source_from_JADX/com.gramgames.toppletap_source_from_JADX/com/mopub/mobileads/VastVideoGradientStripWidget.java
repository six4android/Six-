package com.mopub.mobileads;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.common.util.Dips;
import com.mopub.mobileads.resource.DrawableConstants.GradientStrip;
import org.json.simple.parser.Yytoken;

public class VastVideoGradientStripWidget extends ImageView {
    @NonNull
    ForceOrientation mForceOrientation;
    private boolean mHasCompanionAd;
    private boolean mIsVideoComplete;
    private int mVisibilityForCompanionAd;

    public VastVideoGradientStripWidget(@NonNull Context context, @NonNull Orientation gradientOrientation, @NonNull ForceOrientation forceOrientation, boolean hasCompanionAd, int visibilityForCompanionAd, int layoutVerb, int layoutAnchor) {
        super(context);
        this.mForceOrientation = forceOrientation;
        this.mVisibilityForCompanionAd = visibilityForCompanionAd;
        this.mHasCompanionAd = hasCompanionAd;
        setImageDrawable(new GradientDrawable(gradientOrientation, new int[]{GradientStrip.START_COLOR, GradientStrip.END_COLOR}));
        LayoutParams layoutParams = new LayoutParams(-1, Dips.dipsToIntPixels(72.0f, context));
        layoutParams.addRule(layoutVerb, layoutAnchor);
        setLayoutParams(layoutParams);
        updateVisibility();
    }

    void notifyVideoComplete() {
        this.mIsVideoComplete = true;
        updateVisibility();
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateVisibility();
    }

    private void updateVisibility() {
        if (this.mIsVideoComplete) {
            if (this.mHasCompanionAd) {
                setVisibility(this.mVisibilityForCompanionAd);
            } else {
                setVisibility(8);
            }
        } else if (this.mForceOrientation == ForceOrientation.FORCE_PORTRAIT) {
            setVisibility(4);
        } else if (this.mForceOrientation == ForceOrientation.FORCE_LANDSCAPE) {
            setVisibility(0);
        } else {
            switch (getResources().getConfiguration().orientation) {
                case Yylex.YYINITIAL /*0*/:
                    MoPubLog.d("Screen orientation undefined: do not show gradient strip widget");
                    setVisibility(4);
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    setVisibility(4);
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    setVisibility(0);
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    MoPubLog.d("Screen orientation is deprecated ORIENTATION_SQUARE: do not show gradient strip widget");
                    setVisibility(4);
                default:
                    MoPubLog.d("Unrecognized screen orientation: do not show gradient strip widget");
                    setVisibility(4);
            }
        }
    }
}
