package de.prosiebensat1digital.tracking.provider.mixpanel;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
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
            JSONObject jsonPayload = createJsonObject(properties);
            mixpanel.track(eventName, jsonPayload);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    private JSONObject createJsonObject(final ReadableMap properties) throws JSONException {
        JSONObject jsonPayload = new JSONObject();
        for (final ReadableMapKeySetIterator iterator = properties.keySetIterator(); iterator.hasNextKey();) {
            final String key = iterator.nextKey();
            final ReadableType type = properties.getType(key);
            switch (type) {
                case String: jsonPayload.put(key, properties.getString(key)); break;
                case Array: jsonPayload.put(key, properties.getArray(key)); break;
                case Boolean: jsonPayload.put(key, properties.getBoolean(key)); break;
                case Number: jsonPayload.put(key, properties.getDouble(key)); break;
                case Map: jsonPayload.put(key, createJsonObject(properties.getMap(key))); break;
            }
            jsonPayload.put(key, type);
        }
        return jsonPayload;
    }
}
