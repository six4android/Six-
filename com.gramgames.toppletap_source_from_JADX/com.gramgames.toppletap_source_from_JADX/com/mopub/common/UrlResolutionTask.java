package com.mopub.common;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.common.util.AsyncTasks;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@VisibleForTesting
public class UrlResolutionTask extends AsyncTask<String, Void, String> {
    private static final int REDIRECT_LIMIT = 10;
    @NonNull
    private final UrlResolutionListener mListener;

    interface UrlResolutionListener {
        void onFailure(@NonNull String str, @Nullable Throwable th);

        void onSuccess(@NonNull String str);
    }

    public static void getResolvedUrl(@NonNull String urlString, @NonNull UrlResolutionListener listener) {
        try {
            AsyncTasks.safeExecuteOnExecutor(new UrlResolutionTask(listener), urlString);
        } catch (Exception e) {
            listener.onFailure("Failed to resolve url", e);
        }
    }

    UrlResolutionTask(@NonNull UrlResolutionListener listener) {
        this.mListener = listener;
    }

    @Nullable
    protected String doInBackground(@Nullable String... urls) {
        if (urls == null || urls.length == 0) {
            return null;
        }
        String previousUrl = null;
        try {
            String locationUrl = urls[0];
            int redirectCount = 0;
            while (locationUrl != null && redirectCount < REDIRECT_LIMIT) {
                if (!UrlAction.OPEN_IN_APP_BROWSER.shouldTryHandlingUrl(Uri.parse(locationUrl))) {
                    return locationUrl;
                }
                previousUrl = locationUrl;
                locationUrl = getRedirectLocation(locationUrl);
                redirectCount++;
            }
            return previousUrl;
        } catch (IOException e) {
            return null;
        } catch (URISyntaxException e2) {
            return null;
        }
    }

    @Nullable
    private String getRedirectLocation(@NonNull String urlString) throws IOException, URISyntaxException {
        HttpURLConnection httpUrlConnection = null;
        try {
            httpUrlConnection = (HttpURLConnection) new URL(urlString).openConnection();
            httpUrlConnection.setInstanceFollowRedirects(false);
            String resolveRedirectLocation = resolveRedirectLocation(urlString, httpUrlConnection);
            return resolveRedirectLocation;
        } finally {
            if (httpUrlConnection != null) {
                httpUrlConnection.disconnect();
            }
        }
    }

    @Nullable
    @VisibleForTesting
    static String resolveRedirectLocation(@NonNull String baseUrl, @NonNull HttpURLConnection httpUrlConnection) throws IOException, URISyntaxException {
        URI baseUri = new URI(baseUrl);
        int responseCode = httpUrlConnection.getResponseCode();
        String redirectUrl = httpUrlConnection.getHeaderField("Location");
        String result = null;
        if (responseCode >= Strategy.TTL_SECONDS_DEFAULT && responseCode < 400) {
            try {
                result = baseUri.resolve(redirectUrl).toString();
            } catch (IllegalArgumentException e) {
                throw new URISyntaxException(redirectUrl, "Unable to parse invalid URL");
            }
        }
        return result;
    }

    protected void onPostExecute(@Nullable String resolvedUrl) {
        super.onPostExecute(resolvedUrl);
        if (isCancelled() || resolvedUrl == null) {
            onCancelled();
        } else {
            this.mListener.onSuccess(resolvedUrl);
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        this.mListener.onFailure("Task for resolving url was cancelled", null);
    }
}
