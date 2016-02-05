'use strict';

var React = require('react-native');

var MixpanelTrackerModule = React.NativeModules.MixpanelTrackerModule;

/**
 * Detects new session based on idle times.
 * A new session is generated and reported to Mixpanel whenever an event is processed after the configured idle time.
 */
class SessionDetection {
    /**
     * @param maxIdleTime {Number} the maximum time in ms that a session can be idle before a new one must be created.
     */
    constructor(maxIdleTime) {
        this.maxIdleTime = maxIdleTime;
        this.lastSessionEventTime = -maxIdleTime;
    }

    /**
     * Called whenever a micro event is passed to the tracking layer.
     * @param event {Object} the micro event.
     */
    onEvent(event) {
        let time = event.time;
        if (time - this.lastSessionEventTime > this.maxIdleTime) {
            this.onNewSessionStarted(event);
        }

        this.lastSessionEventTime = event.time;
    }

    /**
     * Called if a new session was detected.
     * @param event {Object} the event that triggered the new session.
     */
    onNewSessionStarted(event) {
        var payload = {
            sessionId: this.generateNewSessionId(),
            startedAt: event.time
        };

        MixpanelTrackerModule.track('SessionStarted', payload);
    }

    /**
     * Generates a new session ID based on a UUID. Algorithm from: http://stackoverflow.com/a/2117523
     * @return {String} the generated session ID.
     */
    generateNewSessionId() {
        return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
            var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
            return v.toString(16);
        });
    }
}

module.exports = SessionDetection;