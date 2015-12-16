//
//  TrackingLayerConfiguration.h
//  Tracking
//
//  Created by Riemschneider Jens on 26/01/16.
//  Copyright © 2016 P7S1. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "MixpanelConfiguration.h"

@interface TrackingLayerConfiguration : NSObject

@property (nonatomic) BOOL developerSupport;
@property (nonatomic, strong) MixpanelConfiguration *mixpanelConfiguration;

@end
