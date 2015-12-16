package de.prosiebensat1digital.tracking;

import android.app.Activity;

import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

/**
 * Provides functionality to configure tracking providers and to process micro-tracking events.
 * The tracking layer transforms the micro events into higher-level events for individual tracking
 * providers (such as Mixpanel, Google Analytics).
 *
 * The logic for processing micro events takes place in JavaScript running inside a simplified
 * ReactNative environment. ReactNative is only used as a JavaScript runtime environment. It does
 * not provide any UI elements.
 *
 * This class serves as a bridging layer to the JavaScript logic, by transforming the micro-tracking
 * events into {@link DeviceEventManagerModule.RCTDeviceEventEmitter} events. It also configures
 * native modules for usage within JavaScript to access the native tracking provider APIs. These
 * modules are called by the JavaScript logic to send the higher-level events to the tracking
 * providers.
 */
public class TrackingLayer {
    private NativeModuleReactPackage nativeModuleReactPackage;
    private ReactInstanceManager mReactInstanceManager;

    /**
     * Initializes the tracking layer.
     * @param activity the activity that instantiates the layer.
     */
    public TrackingLayer(final Activity activity, final TrackingLayerConfiguration config) {
        nativeModuleReactPackage = new NativeModuleReactPackage(activity, config);
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(activity.getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index")
                .addPackage(nativeModuleReactPackage)
                .setUseDeveloperSupport(config.getDeveloperSupport())
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        mReactInstanceManager.createReactContextInBackground();
        mReactInstanceManager.onResume(activity, null);
    }

    /**
     * Sends a test event to verify communication with the tracking layer.
     */
    public void sendEvent() {
        WritableMap trackEvent = Arguments.createMap();
        trackEvent.putString("event", "TrackMedia");

        WritableMap payload = Arguments.createMap();
        payload.putString("videoId", "1234552");
        trackEvent.putMap("payload", payload);
        nativeModuleReactPackage.emit("Track", trackEvent);
    }

    /**
     * Displays the developer dialog of ReactNative.
     */
    public void showDevOptionsDialog() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
        }
    }
}
