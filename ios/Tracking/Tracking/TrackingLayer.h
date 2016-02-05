//
//  TrackingLayer.h
//  Tracking
//
//  Created by Riemschneider Jens on 11/12/15.
//  Copyright © 2015 P7S1. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "TrackingLayerConfiguration.h"

@interface TrackingLayer : NSObject

- (instancetype)initWithConfiguration:(TrackingLayerConfiguration *)config;

- (void)sendEvent:(NSDictionary *)event;

@end
