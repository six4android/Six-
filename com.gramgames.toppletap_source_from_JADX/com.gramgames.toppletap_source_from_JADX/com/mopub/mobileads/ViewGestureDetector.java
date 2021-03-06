package com.mopub.mobileads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.mopub.common.AdReport;
import com.mopub.common.logging.MoPubLog;
import org.json.simple.parser.Yytoken;

public class ViewGestureDetector extends GestureDetector {
    private AdAlertGestureListener mAdAlertGestureListener;
    private UserClickListener mUserClickListener;
    private final View mView;

    public interface UserClickListener {
        void onResetUserClick();

        void onUserClick();

        boolean wasClicked();
    }

    public ViewGestureDetector(@NonNull Context context, @NonNull View view, @Nullable AdReport adReport) {
        this(context, view, new AdAlertGestureListener(view, adReport));
    }

    private ViewGestureDetector(Context context, View view, AdAlertGestureListener adAlertGestureListener) {
        super(context, adAlertGestureListener);
        this.mAdAlertGestureListener = adAlertGestureListener;
        this.mView = view;
        setIsLongpressEnabled(false);
    }

    public void sendTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case Yylex.YYINITIAL /*0*/:
                onTouchEvent(motionEvent);
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (this.mUserClickListener != null) {
                    this.mUserClickListener.onUserClick();
                } else {
                    MoPubLog.d("View's onUserClick() is not registered.");
                }
                this.mAdAlertGestureListener.finishGestureDetection();
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                if (isMotionEventInView(motionEvent, this.mView)) {
                    onTouchEvent(motionEvent);
                } else {
                    resetAdFlaggingGesture();
                }
            default:
        }
    }

    public void setUserClickListener(UserClickListener listener) {
        this.mUserClickListener = listener;
    }

    void resetAdFlaggingGesture() {
        this.mAdAlertGestureListener.reset();
    }

    private boolean isMotionEventInView(MotionEvent motionEvent, View view) {
        if (motionEvent == null || view == null) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (x < 0.0f || x > ((float) view.getWidth()) || y < 0.0f || y > ((float) view.getHeight())) {
            return false;
        }
        return true;
    }

    @Deprecated
    void setAdAlertGestureListener(AdAlertGestureListener adAlertGestureListener) {
        this.mAdAlertGestureListener = adAlertGestureListener;
    }
}
