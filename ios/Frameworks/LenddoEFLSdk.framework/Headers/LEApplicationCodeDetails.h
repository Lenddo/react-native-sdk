//
//  LEApplicationCodeDetails.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 11/01/2019.
//  Copyright © 2019 Lenddo Pte. Ltd. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "LEApplicationCodeTheme.h"

@interface LEApplicationCodeDetails : NSObject

@property NSString *code;
@property NSString *partner_script_id;
@property NSString *privacy_policy_url;
@property NSString *terms_and_conditions_url;
@property NSString *contact_us;
@property Boolean enableDataCollection;
@property NSString *partner_name;
@property NSString *language;
@property NSString *logo;
@property NSArray *verification_form;
@property NSArray *document_groups;
@property NSDictionary *theme;

-(id) initWithValuesForKeysWithDictionary:(NSDictionary<NSString *,id> *)keyedValues;
-(void) setValuesForKeysWithDictionary:(NSDictionary<NSString *,id> *)keyedValues;

@end
