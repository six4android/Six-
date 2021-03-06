package com.facebook.internal;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.internal.WebDialog.Builder;
import com.facebook.internal.WebDialog.OnCompleteListener;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class FacebookDialogFragment extends DialogFragment {
    public static final String TAG = "FacebookDialogFragment";
    private Dialog dialog;

    class 1 implements OnCompleteListener {
        1() {
        }

        public void onComplete(Bundle values, FacebookException error) {
            FacebookDialogFragment.this.onCompleteWebDialog(values, error);
        }
    }

    class 2 implements OnCompleteListener {
        2() {
        }

        public void onComplete(Bundle values, FacebookException error) {
            FacebookDialogFragment.this.onCompleteWebFallbackDialog(values);
        }
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.dialog == null) {
            WebDialog webDialog;
            FragmentActivity activity = getActivity();
            Bundle params = NativeProtocol.getMethodArgumentsFromIntent(activity.getIntent());
            if (params.getBoolean(NativeProtocol.WEB_DIALOG_IS_FALLBACK, false)) {
                String url = params.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
                if (Utility.isNullOrEmpty(url)) {
                    Utility.logd(TAG, "Cannot start a fallback WebDialog with an empty/missing 'url'");
                    activity.finish();
                    return;
                }
                webDialog = new FacebookWebFallbackDialog(activity, url, String.format("fb%s://bridge/", new Object[]{FacebookSdk.getApplicationId()}));
                webDialog.setOnCompleteListener(new 2());
            } else {
                String actionName = params.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY);
                Bundle webParams = params.getBundle(NativeProtocol.WEB_DIALOG_PARAMS);
                if (Utility.isNullOrEmpty(actionName)) {
                    Utility.logd(TAG, "Cannot start a WebDialog with an empty/missing 'actionName'");
                    activity.finish();
                    return;
                }
                webDialog = new Builder(activity, actionName, webParams).setOnCompleteListener(new 1()).build();
            }
            this.dialog = webDialog;
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (this.dialog == null) {
            onCompleteWebDialog(null, null);
            setShowsDialog(false);
        }
        return this.dialog;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.dialog instanceof WebDialog) {
            ((WebDialog) this.dialog).resize();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void onCompleteWebDialog(Bundle values, FacebookException error) {
        FragmentActivity fragmentActivity = getActivity();
        fragmentActivity.setResult(error == null ? -1 : 0, NativeProtocol.createProtocolResultIntent(fragmentActivity.getIntent(), values, error));
        fragmentActivity.finish();
    }

    private void onCompleteWebFallbackDialog(Bundle values) {
        FragmentActivity fragmentActivity = getActivity();
        Intent resultIntent = new Intent();
        if (values == null) {
            values = new Bundle();
        }
        resultIntent.putExtras(values);
        fragmentActivity.setResult(-1, resultIntent);
        fragmentActivity.finish();
    }
}
