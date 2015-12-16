//
//  MixpanelTrackerModule.h
//  TestApp
//
//  Created by Riemschneider Jens on 09/12/15.
//  Copyright © 2015 Riemschneider Jens. All rights reserved.
//

#import "RCTBridgeModule.h"
#import "MixpanelConfiguration.h"

@interface MixpanelTrackerModule : NSObject<RCTBridgeModule>

- (instancetype)init NS_UNAVAILABLE;
- (instancetype)initWithConfiguration:(MixpanelConfiguration *)config;

@end
