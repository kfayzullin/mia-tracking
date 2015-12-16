//
//  MixpanelTrackerModule.m
//  TestApp
//
//  Created by Riemschneider Jens on 09/12/15.
//  Copyright © 2015 Riemschneider Jens. All rights reserved.
//

#import "MixpanelTrackerModule.h"
#import "Mixpanel.h"

@implementation MixpanelTrackerModule

RCT_EXPORT_MODULE();

- (instancetype)init {
    return nil;
}

- (instancetype)initWithConfiguration:(MixpanelConfiguration *)config {
    self = [super init];
    if (self) {
        [Mixpanel sharedInstanceWithToken:config.token];
    }
    return self;
}

RCT_EXPORT_METHOD(track:(NSString *)event payload:(NSDictionary *)payload) {
    NSLog(@"Sending event to mixpanel: %@ %@", event, payload);
    [[Mixpanel sharedInstance] track:event properties:payload];
}

@end
