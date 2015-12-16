package de.prosiebensat1digital.tracking;

import android.app.Activity;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.shell.MainReactPackage;

import java.util.ArrayList;
import java.util.List;

import de.prosiebensat1digital.tracking.provider.google.GoogleAnalyticsTrackerModule;
import de.prosiebensat1digital.tracking.provider.mixpanel.MixpanelTrackerModule;

/**
 * ReactNative package that list all native modules used within the Tracking Layer.
 */
class NativeModuleReactPackage extends MainReactPackage {
    private final Activity activity;
    private final TrackingLayerConfiguration config;
    private ReactApplicationContext reactContext;

    /**
     * @param activity the Apps activity.
     * @param config the configuration data of the tracking layer.
     */
    NativeModuleReactPackage(final Activity activity, TrackingLayerConfiguration config) {
        this.activity = activity;
        this.config = config;
    }

    @Override
    public List<NativeModule> createNativeModules(final ReactApplicationContext reactContext) {
        this.reactContext = reactContext;

        List<NativeModule> modules = new ArrayList<>(super.createNativeModules(reactContext));
        if (config.getMixpanelConfiguration() != null) {
            modules.add(new MixpanelTrackerModule(config.getMixpanelConfiguration(), reactContext, activity));
        }
        if (config.getGoogleAnalyticsConfiguration() != null) {
            modules.add(new GoogleAnalyticsTrackerModule(config.getGoogleAnalyticsConfiguration(), reactContext, activity));
        }
        return modules;
    }

    /**
     * Emits a device event to JavaScript.
     * @param eventName the event name.
     * @param payload the payload.
     */
    void emit(final String eventName, final WritableMap payload) {
        DeviceEventManagerModule.RCTDeviceEventEmitter jsModule =
                reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        jsModule.emit(eventName, payload);
    }
}
