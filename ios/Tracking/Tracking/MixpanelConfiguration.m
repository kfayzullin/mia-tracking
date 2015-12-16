//
//  MixpanelConfiguration.m
//  Tracking
//
//  Created by Riemschneider Jens on 26/01/16.
//  Copyright © 2016 P7S1. All rights reserved.
//

#import "MixpanelConfiguration.h"

@implementation MixpanelConfiguration

- (instancetype)initWithToken:(NSString *)token {
    self = [super init];
    if (self) {
        _token = token;
        
        return self;
    }
    
    return nil;
}

@end
