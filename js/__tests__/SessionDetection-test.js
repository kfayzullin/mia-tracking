'use strict';

jest.setMock('react-native', {
    NativeModules: {
        MixpanelTrackerModule: { track: jest.genMockFn() }
    }
});

var SessionDetection = require('../SessionDetection');
var React = require('react-native');

var MixpanelTrackerModule = React.NativeModules.MixpanelTrackerModule;

describe('SessionDetection', () => {
    it('detects a new session for the very first event', () => {
        let sessionDetection = new SessionDetection(10);
        sessionDetection.onEvent({time: 5});

        expect(MixpanelTrackerModule.track.mock.calls.length).toBe(1);
        expect(MixpanelTrackerModule.track.mock.calls[0][0]).toBe('SessionStarted');
        expect(MixpanelTrackerModule.track.mock.calls[0][1].sessionId).not.toBeUndefined();
        expect(MixpanelTrackerModule.track.mock.calls[0][1].startedAt).toBe(5);
    });

    it('does not detect a new session within the accepted idle time limit', () => {
        let sessionDetection = new SessionDetection(10);
        sessionDetection.onEvent({time: 5});
        MixpanelTrackerModule.track.mockClear();
        sessionDetection.onEvent({time: 5 + 10});
        expect(MixpanelTrackerModule.track).not.toBeCalled();
    });

    it('increases session timeout if a new event is received', () => {
        let sessionDetection = new SessionDetection(10);
        sessionDetection.onEvent({time: 5});
        MixpanelTrackerModule.track.mockClear();
        sessionDetection.onEvent({time: 5 + 10});
        sessionDetection.onEvent({time: 5 + 11});
        expect(MixpanelTrackerModule.track).not.toBeCalled();
    });

    it('does detect a new session after the idle time limit', () => {
        let sessionDetection = new SessionDetection(10);
        sessionDetection.onEvent({time: 5});
        MixpanelTrackerModule.track.mockClear();
        sessionDetection.onEvent({time: 5 + 11});

        expect(MixpanelTrackerModule.track.mock.calls.length).toBe(1);
        expect(MixpanelTrackerModule.track.mock.calls[0][0]).toBe('SessionStarted');
        expect(MixpanelTrackerModule.track.mock.calls[0][1].sessionId).not.toBeUndefined();
        expect(MixpanelTrackerModule.track.mock.calls[0][1].startedAt).toBe(16);
    });
});
