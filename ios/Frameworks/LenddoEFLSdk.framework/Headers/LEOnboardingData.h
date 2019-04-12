//
//  LAOnboardingData.h
//  LenddoEFLSdk
//
//  Created by Neil Mosca on 26/01/2017.
//  Copyright © 2017 Neil Mosca. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "LEThemeColor.h"

@interface LEOnboardingData : NSObject

@property NSString *lastName;
@property NSString *firstName;
@property NSString *middleName;

@property NSString *email;
@property NSString *workEmail;
@property NSDate *birthday;

@property NSString *employerName;
@property NSDate *employmentStartDate;
@property NSDate *employmentEndDate;
@property NSString *mobilePhone;
@property NSString *homePhone;

@property NSString *motherFirstName;
@property NSString *motherLastName;
@property NSString *motherMiddleName;

@property NSString *universityName;

@property NSString *addressLine1;
@property NSString *addressLine2;
@property NSString *city;
@property NSString *administrativeDivision;
@property NSString *countryCode;
@property NSString *postalCode;
@property long latitude;
@property long longitude;

@property NSString *workAddressLine1;
@property NSString *workAddressLine2;
@property NSString *workCity;
@property NSString *workAdministrativeDivision;
@property NSString *workCountryCode;
@property NSString *workPostalCode;
@property long workLatitude;
@property long workLongitude;

@property NSString *partnerScriptId;
@property NSString *secret;
@property NSString *authorizeApiGateway;
@property NSString *region;
@property NSString *clientId;
@property NSString *applicationCode;
@property NSString *installationId;
@property NSString *facebookToken;
@property NSInteger expiration;
@property NSString *binApiGateway;
@property Boolean enableKYC;
@property Boolean enableDataCollection;

@property NSDictionary *fields;

@property LEThemeColor *themeColor;

@property Boolean enableAssistedPsychometrics;
@property NSString *psychometricsApiGateway;

-(NSDictionary*) toDictionary;

@end
