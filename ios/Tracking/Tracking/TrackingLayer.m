//
//  TrackingLayer.m
//  Tracking
//
//  Created by Riemschneider Jens on 11/12/15.
//  Copyright © 2015 P7S1. All rights reserved.
//

#import "TrackingLayer.h"
#import "RCTEventDispatcher.h"
#import "MixpanelTrackerModule.h"

@interface TrackingLayer ()

@property (nonatomic, strong) RCTBridge *bridge;
@property (nonatomic, strong) TrackingLayerConfiguration *configuration;

@end


@implementation TrackingLayer

- (instancetype)initWithConfiguration:(TrackingLayerConfiguration *)config {
    self = [super init];
    if (self) {
        NSURL *bundleUrl = config.developerSupport
        ? [NSURL URLWithString:@"http://localhost:8081/index.bundle?platform=ios"]
        : [[NSBundle bundleWithPath:[[NSBundle mainBundle] pathForResource:@"TrackingLayer" ofType:@"bundle"]] URLForResource:@"index.ios.bundle" withExtension:@"js"];
        
        NSMutableArray<id<RCTBridgeModule>>* modules = [[NSMutableArray alloc] init];

        if (config.mixpanelConfiguration != nil) {
            [modules addObject:[[MixpanelTrackerModule alloc] initWithConfiguration: config.mixpanelConfiguration]];
        }
        
        _bridge = [[RCTBridge alloc] initWithBundleURL:bundleUrl
                                        moduleProvider:^NSArray<id<RCTBridgeModule>> *{ return modules; }
                                         launchOptions:nil];
        
        return self;
    }
    
    return nil;
}

- (void)sendEvent:(NSDictionary *)event {
    [self.bridge.eventDispatcher sendDeviceEventWithName:@"MicroEvent" body:event];
}

@end
