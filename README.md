<a href="#"><img src="http://7factory.github.io/mia-js/images/miajs.png" title="mia.js"/></a>

mia.js is an API framework based on [node.js](http://nodejs.org), [Express](https://github.com/strongloop/express) and [mongoDB](https://www.mongodb.org/) that makes it easy to build custom or enterprise-grade apis and web frontends.
Focus of mia.js is to work as middleware backend for mobile apps to have all your communication in one place and avoid overloading your mobile apps.
Passthrough, aggregate or modify other external apis or create your own ones in mia.js and provide them bundled as your project api.
Use multiple project folders to keep track of all your apis and connect them by loose coupling of ids. Mia.js provides predefined functionality like user management, device profile management, session handling, authorization layers or notification handlers (push, email). There is also an iOS and Android SDK available to work with mia.js.

# mia-tracking

The mia-tracking repository provides a general purpose tracking layer integration into mobile apps based on React-Native and
JavaScript.

## Basic Setup using ReactNative
- Follow installation instructions from https://facebook.github.io/react-native/docs/getting-started.html

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
