<a href="#"><img src="http://7factory.github.io/mia-js/images/miajs.png" title="mia.js"/></a>

mia.js is an API framework based on [node.js](http://nodejs.org), [Express](https://github.com/strongloop/express) and [mongoDB](https://www.mongodb.org/) that makes it easy to build custom or enterprise-grade apis and web frontends.
Focus of mia.js is to work as middleware backend for mobile apps to have all your communication in one place and avoid overloading your mobile apps.
Passthrough, aggregate or modify other external apis or create your own ones in mia.js and provide them bundled as your project api.
Use multiple project folders to keep track of all your apis and connect them by loose coupling of ids. Mia.js provides predefined functionality like user management, device profile management, session handling, authorization layers or notification handlers (push, email). There is also an iOS and Android SDK available to work with mia.js.

# Mia-Tracking

The mia-tracking repository provides a general purpose tracking layer integration into mobile apps based on React Native, Mixpanel and
JavaScript. This repository consists of two applications for both Android and iOS:
- The actual tracking layer library
- A test application to trigger a simple micro event in native code and process it within the tracking logic in JavaScript and output it to Mixpanel

## Overview

The idea behind the mia-tracking layer is to bridge a gap between simple micro tracking events generated by a native mobile application and tracking-related data that is required by a non-development department of an organization.
Mia-Tracking therefore provides:
- a platform independent way to implement tracking logic
- a clear separation from application code and tracking logic via micro events
- a clear separation from the application and tracking provider APIs

To achieve this mia-tracking is based on the core parts of React Native. Although React Native is usually used to implement UIs, it also offers a JavaScript engine on Android and iOS with a couple of interesting advantages:
- It is based on JavaScriptCore on all platforms -> the chance for incompatibilities between the platforms is very low
- It provides an easy-to-use development environment with:
    - Support for hot code deployment without recompiling the native application
    - Support for debugging in Chrome directly from the running application
    - Direct support for ES6
- Decoupling from UI threads/asynchronous communication with the tracking logic -> Impact on UI performance is very low
- Support for native modules without much development overhead -> Easy integration of tracking provider APIs

Therefore, mia-tracking is ideally suited for integration into pure native apps as well as apps built completely with React Native.

## Tracking Provider APIs

Currently, mia-tracking directly supports Mixpanel. It can easily be extended to connect other tracking providers, e.g. Google Analytics. Adding a new tracking provider should not require any changes to the micro events generated by the native application. Instead the tracking provider APIs are only addressed from the tracking logic.

Many tracking providers offers different types of APIs - often web-based APIs and mobile APIs. In theory, the web-based APIs could be used from within the tracking logic. However, it is usually better for mobile application to use a mobile API for the following reasons:
- The web-based APIs usually depend on typical browser objects: e.g. window and document. These are not available within React Native
- The mobile APIs often offer queueing of events if network connections are lost

# Setup 

## Basic Setup using ReactNative
- Follow installation instructions from https://facebook.github.io/react-native/docs/getting-started.html

## Mixpanel Setup
- The tracking layer is currently based on Mixpanel. It can be extended to use other tracking providers as well, but out-of-the-box, a Mixpanel project is required
- The Mixpanel project to be used by the tracking layer is configured within the test app:
    - on iOS: ios/TestApp/TestApp/ReactView.m
    - on Android: android/app/src/main/java/de/prosiebensat1digital/testapp/FullscreenActivity.java
- Search for "YOUR_MIXPANEL_PROJECT_TOKEN" and replace it with the token from the Mixpanel project settings

## iOS Setup
- Call 'pod install' in 'ios/Tracking'
- Call 'pod install' in 'ios/TestApp'
- Call 'react-native start --root js' in the root directory
- Start TestApp in XCode
- The app displays a simple 'Send Event' Button that is used to trigger a micro event

## Android Setup
- Call 'react-native start --root js' in the root directory
- Simply build and run the test app in Android Studio
- The app displays a simple 'Send Event' Button that is used to trigger a micro event

# The Test Project
The mia-tracking repository contains a very basic test application that is used to generate micro events. By pressing a button in the native UI, a micro event is generated and passed to the JavaScript tracking logic. In this default project, the tracking logic simply keeps track of sessions.

## Demo Code
Out of the box mia-tracking implements an example tracking logic that detects sessions based on an idle timeout. If the application does not generate a new micro event within 1 min, a new session is started and a corresponding event is send to the Mixpanel tracking API.
This sample shows many of the features of mia-tracking and React Native, such as:
- Handing over the micro events from the native application to the JavaScript environment
  - iOS: ReactView.m
```
    double timeInMs = CACurrentMediaTime() * 1000;
    [self.trackingLayer sendEvent:@{ @"time": @(timeInMs) }];
```
  - Android: FullscreenActivity.java
```
    final WritableMap microEvent = Arguments.createMap();
    microEvent.putDouble("time", new Date().getTime());
    trackingLayer.sendEvent(microEvent);
```

- Implementing the tracking logic in JavaScript: Tracker.js and SesionDetection.js
```
    DeviceEventEmitter.addListener('MicroEvent', (event) => {
        // For this demo code, we simply hook up the session detection here.
        this.sessionDetection.onEvent(event);
    });
```

- Using native modules for the tracking providers in JavaScript: SessionDetection.js
```
    var payload = {
        sessionId: this.generateNewSessionId(),
        startedAt: event.time
    };

    MixpanelTrackerModule.track('SessionStarted', payload);
```

In addition to this, the code also shows how to implement unit tests for the tracking logic using jest and jasmine. Have a look at the ```__test__``` directory.
