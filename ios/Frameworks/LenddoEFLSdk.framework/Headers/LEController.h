//
//  LAController.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 25/01/2017.
//  Copyright © 2017 Neil Mosca. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "LEAuthorizeEventDelegate.h"
#import "LECollectionEventDelegate.h"
#import "LECollectionSetup.h"
#import "LEOnboardingData.h"
#import "LEWebViewController.h"

@interface LEController : NSObject

+(LEWebViewController*) openAuthorize:(UIViewController *)controller withData:(LEOnboardingData *)formData withDelegate:(id <LEAuthorizeEventDelegate>)delegate;
+(LEWebViewController*) openAuthorize:(UIViewController *)controller withGoogleProvider:(LEProvider *)googleProvider withData:(LEOnboardingData *)formData withDelegate:(id <LEAuthorizeEventDelegate>)delegate;

+(LEWebViewController*) openAuthorize:(UIViewController *)controller withProviders:(NSArray *) providers withData:(LEOnboardingData *)formData withDelegate:(id <LEAuthorizeEventDelegate>)delegate;

/*
+(void) requestAccessForDataCollection;
+(void) setupDataCollection:(LECollectionSetup *)setup;
+(void) startDataCollection:(NSString *)applicationId  withDelegate:(id <LECollectionEventDelegate>)delegate;
*/

+(NSString *) getInstallationId;
@end
