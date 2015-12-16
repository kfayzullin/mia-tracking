'use strict';

import {DeviceEventEmitter, NativeModules} from 'react-native';

export default class Tracker {
    init() {
        let nativeModules = [NativeModules.MixpanelTrackerModule];
        let trackerModules = nativeModules.filter((module) => { return module; });

        DeviceEventEmitter.addListener('Track', (event) => {
            trackerModules.forEach((module) => {
                module.track(event.event, event.payload);
            });
        });
    }
}
