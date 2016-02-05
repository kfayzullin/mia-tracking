//
//  ReactView.m
//  TestApp
//
//  Created by Riemschneider Jens on 09/12/15.
//  Copyright © 2015 Riemschneider Jens. All rights reserved.
//

#import "ReactView.h"
#import "TrackingLayer.h"

@interface ReactView ()

@property (nonatomic, strong) TrackingLayer *trackingLayer;

@end


@implementation ReactView

- (void)awakeFromNib {
    TrackingLayerConfiguration* config = [[TrackingLayerConfiguration alloc] init];
    config.mixpanelConfiguration = [[MixpanelConfiguration alloc] initWithToken: @"YOUR_MIXPANEL_PROJECT_TOKEN"];
    config.developerSupport = YES;
    self.trackingLayer = [[TrackingLayer alloc] initWithConfiguration:config];
}

- (void)sendEvent {
    double timeInMs = CACurrentMediaTime() * 1000;
    [self.trackingLayer sendEvent:@{ @"time": @(timeInMs) }];
}

@end
