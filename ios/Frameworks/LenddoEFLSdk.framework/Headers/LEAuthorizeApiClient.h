//
//  LAApiClient.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 26/01/2017.
//  Copyright © 2017 Neil Mosca. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LEServiceToken.h"
#import "LEPartnerScriptConfiguration.h"
#import "LEOnboardingData.h"
#import "LEIdentityToken.h"
#import "LEPsychoSelfie.h"
#import "LEUploadToken.h"
#import "LELivenessInstruction.h"
#import "LEUploadTokenData.h"
#import "LEApplicationCodeDetails.h"
#import "LEApplicationIdValidator.h"

@interface LEAuthorizeApiClient : NSObject

@property NSString *partnerScriptId;
@property NSString *secret;
@property NSString *gateway;
@property NSString *psy_gateway;

-(id) initWithSessionManager: (id) sessionManager;

-(id) initWithGateway: (NSString *) aGateway;

-(id) initWithPartnerScriptId: (NSString *) aPartnerScriptId
                       secret: (NSString *) aSecret
                      gateway: (NSString *) aGateway;

-(id) initWithPartnerScriptId: (NSString *) aPartnerScriptId
                  secret: (NSString *) aSecret
                 gateway: (NSString *) aGateway
                  region: (NSString *) aRegion;

-(id) initWithPartnerScriptId: (NSString *) aPartnerScriptId
                  secret: (NSString *) aSecret
                 gateway: (NSString *) aGateway
                  region: (NSString *) aRegion
             psy_gateway: (NSString *) aPsyGateway;

-(void)getHealthCheckWithSuccess:(void(^)(BOOL))aSuccess
                                 andFailure:(void(^)(NSError*))aFailure;

-(void)getOnboardingServiceTokenWithSuccess:(void(^)(LEServiceToken*))aSuccess
                                 andFailure:(void(^)(NSError*))aFailure;

-(void)getAuthorizeUrlWithSuccess:(void(^)(LEPartnerScriptConfiguration*))aSuccess
                       andFailure:(void(^)(NSError*))aFailure;

-(void)postOnboardingSessionWithFormData:(LEOnboardingData *)aFormData
                                withToken:(NSString *) token
                              withSuccess:(void(^)(BOOL))aSuccess
                              andFailure:(void(^)(NSError*))aFailure;

-(void)postOAuthProviderWithIdentityToken:(LEIdentityToken *)anIdentityToken
                                withApiAccessToken:(NSString *) anApiAccessToken
                             withSuccess:(void(^)(BOOL))aSuccess
                              andFailure:(void(^)(NSError*))aFailure;

-(void)postPsychometricsServiceSelfies:(LEPsychoSelfie *) selfie
                       withPsychometricsAccessToken:(NSString *) aPsychometricsAccessToken
                              withSuccess:(void(^)(BOOL))aSuccess
                               andFailure:(void(^)(NSError*))aFailure;
    
-(void) getUploadTokenWithToken:(NSString *) aToken
                   withUploadTokenData:(LEUploadTokenData *) anUploadTokenData
                    withSuccess:(void(^)(LEUploadToken*)) aSuccess
                     andFailure:(void(^)(NSError*))aFailure;
    
-(void) getLivenessTestInstructionWithToken:(NSString *) aToken
                                withSuccess:(void(^)(LELivenessInstruction*)) aSuccess
                           andFailure:(void(^)(NSError*))aFailure;

-(void) postUploadCompleteWithUploadToken:(NSString *) anUploadToken
                           withApiToken:(NSString *) aToken
                           withSuccess:(void(^)(BOOL)) aSuccess
                            andFailure:(void(^)(NSError*)) aFailure;

-(void) getApplicationCodeDetailWithAppCode:(NSString *) appCode
                                  withQuery:(NSString *) query
                                  withToken:(NSString *) aToken
                                withSuccess:(void(^)(LEApplicationCodeDetails*)) aSuccess
                                 andFailure:(void(^)(NSError*))aFailure;

-(void) postValidateApplicationIdWithValidator:(LEApplicationIdValidator *) aValidator
                             withApiToken:(NSString *) aToken
                              withSuccess:(void(^)(NSDictionary*)) aSuccess
                               andFailure:(void(^)(NSError*)) aFailure;
@end
