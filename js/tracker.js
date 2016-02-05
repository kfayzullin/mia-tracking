'use strict';

var {DeviceEventEmitter} = require('react-native');
var SessionDetection = require('./SessionDetection');

/**
 * Establishes a listener for React Native device events that are used to transfer micro events from the native
 * implementation to the JavaScript engine.
 */
class Tracker {
    constructor() {
        this.sessionDetection = new SessionDetection(60 * 1000); // 1 min
    }

    /**
     * Starts listening for micro events.
     */
    startListener() {
        DeviceEventEmitter.addListener('MicroEvent', (event) => {
            // For this demo code, we simply hook up the session detection here.
            this.sessionDetection.onEvent(event);
        });
    }
}

module.exports = Tracker;
