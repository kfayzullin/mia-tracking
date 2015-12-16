//
//  MixpanelConfiguration.h
//  Tracking
//
//  Created by Riemschneider Jens on 26/01/16.
//  Copyright © 2016 P7S1. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface MixpanelConfiguration : NSObject

@property (nonatomic, strong, readonly) NSString *token;

- (instancetype)initWithToken:(NSString *)token;

@end
