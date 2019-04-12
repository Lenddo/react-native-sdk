//
//  LEUploadTokenData.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 12/11/2018.
//  Copyright © 2018 Lenddo Pte. Ltd. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LEUploadTokenData : NSObject

@property NSString *documentTypeId;
@property NSDictionary *meta;
@property int partIndex;

-(NSDictionary*) toDictionary;

@end
