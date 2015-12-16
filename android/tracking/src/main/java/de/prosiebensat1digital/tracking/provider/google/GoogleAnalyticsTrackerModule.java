package de.prosiebensat1digital.tracking.provider.google;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Native module for ReactNative to bridge the Google Analytics API to JavaScript.
 * Note: Methods in this class should only be called by JavaScript!
 */
public class GoogleAnalyticsTrackerModule extends ReactContextBaseJavaModule {
    private final Tracker tracker;

    /**
     * @param config the configuration data.
     * @param reactContext the ReactNative context.
     * @param activity the app activity.
     */
    public GoogleAnalyticsTrackerModule(final GoogleAnalyticsConfiguration config,
                                        final ReactApplicationContext reactContext,
                                        final Activity activity) {
        super(reactContext);

        tracker = GoogleAnalytics.getInstance(activity.getApplicationContext()).newTracker(config.getTrackerId());
    }

    @Override
    public String getName() {
        return "GoogleAnalyticsTrackerModule";
    }

    /**
     * Tracks an event via Google Analytics.
     * @param eventName the name of the event.
     * @param properties the properties.
     */
    @ReactMethod
    public void track(final String eventName, final ReadableMap properties) {
        Log.i("track", "Sending event to GA: " + eventName + ", " + properties.toString());
        Map<String, String> payload = new HitBuilders.EventBuilder()
                .setCategory("event")
                .setAction(eventName)
                .setLabel("videoId")
                .setValue(Integer.parseInt(properties.getString("videoId")))
                .build();
        tracker.send(payload);
    }
}
