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
    NSDictionary *event = @{ @"event": @"TrackMedia",
                             @"payload": @{ @"videoId": @"1234552"}};

    [self.trackingLayer sendEvent:@"Track" withPayload:event];
}

@end
