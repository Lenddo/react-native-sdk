//
//  LEAuthorizeEventDelegate.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 25/01/2017.
//  Copyright © 2017 Neil Mosca. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LEServiceToken.h"

@protocol LEAuthorizeEventDelegate <NSObject>

-(void)onAuthorizeComplete:(NSString*)applicationId;
-(void)onAuthorizeStarted:(LEServiceToken *) serviceToken;
-(void)onAuthorizeError:(NSError*)error;
-(void)onAuthorizeCancelled:(NSString*)applicationId;

@end
