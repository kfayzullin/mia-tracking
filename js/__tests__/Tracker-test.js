'use strict';

var SessionDetectionMock = jest.genMockFn();
var onEventMock = jest.genMockFn();
SessionDetectionMock.mockImpl(() => { return { onEvent: onEventMock } });
jest.setMock('../SessionDetection', SessionDetectionMock);

jest.setMock('react-native', {DeviceEventEmitter: {addListener: jest.genMockFn()}});

var Tracker = require('../Tracker');
var SessionDetection = require('../SessionDetection');
var {DeviceEventEmitter} = require('react-native');

describe('Tracker', () => {
    it('listens for micro events', () => {
        let tracker = new Tracker();
        tracker.startListener();

        expect(DeviceEventEmitter.addListener).toBeCalled();
        expect(DeviceEventEmitter.addListener.mock.calls[0][0]).toBe('MicroEvent');
    });

    it('transfers events to the session detection', () => {
        let tracker = new Tracker();
        tracker.startListener();

        var event = {time: 5};
        let listenerFunc = DeviceEventEmitter.addListener.mock.calls[0][1];
        listenerFunc(event);
        expect(onEventMock).toBeCalledWith(event);
    });
});
