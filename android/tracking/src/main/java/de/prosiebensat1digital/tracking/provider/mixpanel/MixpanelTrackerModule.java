package de.prosiebensat1digital.tracking.provider.mixpanel;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Native module for ReactNative to bridge the Mixpanel API to JavaScript.
 * Note: Methods in this class should only be called by JavaScript!
 */
public class MixpanelTrackerModule extends ReactContextBaseJavaModule {
    private MixpanelAPI mixpanel;

    /**
     * @param config the configuration data.
     * @param reactContext the ReactNative context.
     * @param activity the app activity.
     */
    public MixpanelTrackerModule(final MixpanelConfiguration config,
                                 final ReactApplicationContext reactContext,
                                 final Activity activity) {
        super(reactContext);

        mixpanel = MixpanelAPI.getInstance(activity, config.getToken());
    }

    @Override
    public String getName() {
        return "MixpanelTrackerModule";
    }

    /**
     * Tracks an event via Mixpanel {@link MixpanelAPI#track(String, JSONObject)}.
     * @param eventName the name of the event.
     * @param properties the properties.
     */
    @ReactMethod
    public void track(final String eventName, final ReadableMap properties) {
        try {
            Log.i("track", "Sending event to mixpanel: " + eventName + ", " + properties.toString());
            JSONObject jsonPayload = new JSONObject();
            jsonPayload.put("videoId", properties.getString("videoId"));
            mixpanel.track(eventName, jsonPayload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
