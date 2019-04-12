//
//  LEBinApiClient.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 12/11/2018.
//  Copyright © 2018 Lenddo Pte. Ltd. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LEServiceToken.h"
#import "LEFilesData.h"

@interface LEBinApiClient : NSObject

@property NSString *binApiGateway;

-(id) initWithAttributes: (NSString *) aGateway
                  region: (NSString *) aRegion;

-(id) initWithAttributes: (NSString *) aGateway;

-(void) getBinServiceTokenWithSuccess:(void(^)(LEServiceToken*))aSuccess
                                 andFailure:(void(^)(NSError*))aFailure;

-(void) postFilesWithFilesData:(LEFilesData *) aFilesData
                                withToken:(NSString *) token
                              withSuccess:(void(^)(BOOL))aSuccess
                               andFailure:(void(^)(NSError*))aFailure;

-(void) postCompleteWithUploadToken:(NSString *) aUploadToken
                     withToken:(NSString *) token
                   withSuccess:(void(^)(BOOL))aSuccess
                    andFailure:(void(^)(NSError*))aFailure;
@end


