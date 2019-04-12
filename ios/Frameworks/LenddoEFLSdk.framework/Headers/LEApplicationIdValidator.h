//
//  LEApplicationIdValidator.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 22/01/2019.
//  Copyright © 2019 Lenddo Pte. Ltd. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface LEApplicationIdValidator : NSObject

@property NSString *app_code;
@property NSString *partner_script_id;
@property NSString *application_id;
@property NSString *pattern;

-(id) initWithValuesForKeysWithDictionary:(NSDictionary<NSString *,id> *)keyedValues;
-(void) setValuesForKeysWithDictionary:(NSDictionary<NSString *,id> *)keyedValues;
-(NSDictionary*) toDictionary;

@end

