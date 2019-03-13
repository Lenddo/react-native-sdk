
# IOS LenddoEFL Onboarding Sdk Wrapper (@lenddo/react-native-sdk)

## Getting started

`$ npm install @lenddo/react-native-sdk --save`

### Mostly automatic installation

`$ react-native link  @lenddo/react-native-sdk`

#### Issue on automatic installation

You might encounter auto-linking of ‘LenddoEFLSdk.framework’ file, as shown in the picture below

![LenddoEFLSdk framework auto-linking failed](https://github.com/Lenddo/react-native-sdk/wiki/LenddoEFLSdk.framework_auto-linking_failed.png)

To manual link of `@lenddo/react-native-sdk`’s `LenddoEFLSdk.framework` into your project
1. In Xcode, select the project, then select the main target (under `Targets`), then go to the `Build Settings` tab, and find the `Framework Search Paths` section. Add `../node_modules/\@lenddo/react-native-sdk/ios/Frameworks` (non-recursive) for each of your configurations (e.g. Debug and Release). Note: *a \ (backslash) is required before @*
2. Find the LenddoEFLSdk.framework file in `../node_modules/@lenddo/react-native-sdk/ios/Frameworks` and drag it into Xcode under the `Frameworks` section. In the dialog that pops up, **uncheck** `Copy items if needed`, **choose** `Create groups`, and ensure your main target is checked under `Add to targets`.
3. In Xcode, select the project, then select the main target (under `Targets`), then go to the "General" tab and find the `Embedded Binaries` section. Click the `+` icon and select LenddoEFLSdk.framework which appears under `Frameworks` then click `Add`.
4. In Xcode do `Product` -> `Clean`.

### Manual installation

#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `@lenddo/react-native-sdk/ios` and add `RNLenddoEFLLibrary.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNLenddoEFLLibrary.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

### Usage
```javascript
import {Platform} from 'react-native';
import {RNOnboardingSdkWrapperIOS} from '@lenddo/react-native-sdk';
 
// Call showAuthorizeWithFormData method to run LenddoEFL onboarding
if (Platform.OS === 'ios') {
	RNOnboardingSdkWrapperIOS.showAuthorizeWithFormData(this.state.formData, (error, events) => {
		if (error) {
			console.error(error);
		} else {
		    // Setup is successful
			console.log(JSON.stringify(events));
		}
	})
}
```
```formData``` is an model object that contain key-value variable to setup and initialize onboarding. You must copy-paste the object and add values on your app. You may delete some variables that you might not need and set it to there default values. There are few variables that are required and will be discussed further below.

```javascript
this.state = {
      formData: {
        partnerScriptId: 'YOUR_PARTNER_SCRIPT_ID',
        secret: '',
 
        applicationId: 'YOUR_APPLICATION_ID',
        firstName: '',
        middleName: '',
        lastName: '',
 
        email: '',
        workEmail: '',
        birthday: '',
 
        mobilePhone: '',
        homePhone: '',
 
        employerName: '',
        employmentStartDate: '',
        employmentEndDate: '',
 
        motherFirstName: '',
        motherLastName: '',
        motherMiddleName: '',
 
        addressLine1: '',
        addressLine2: '',
        city: '',
        administrativeDivision: '',
        countryCode: '',
        postalCode: '',
 
        workAddressLine1: '',
        workAddressLine2: '',
        workCity: '',
        workAdministrativeDivision: '',
        workCountryCode: '',
        workPostalCode: '',
 
        themeColor: ''
      },
    }
``` 
#### Variables Definition

```
partnerScriptId
secret
```
 - Are LenddoEFL partner identifiers and are tagged **required**, please refer to your LenddoEFL representative if you don't have it yet.

```
applicationId
```
- An identifier for your LenddoEFL Onboarding session and is tagged **required**. This variable must be unique for each onboarding session you completed.

```
themeColor
```
 - Will be base color for the navigation bar of LenddoEFL Onboarding screen (Default theme would be black). Sample is shown below:

```javascript
class ThemeColor  {
  constructor(red, green, blue, alpha) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }
}

formData.theme = new ThemeColor(0x00, 0xff, 0x00, 1);
```

#### Applying localization
The SDK Wrapper requires ```Use Base Internationalization``` enable where some text within the onboarding session screen, like ```back``` text and strings for the cancel dialog should be declared.
```Localizable.strings``` file is where you add translation data as key-value pairs.
Earlier versions of XCode used to generate a Localizable.strings file by default, and we were able to easily duplicate the Localizable.strings file for other languages. 
Recent versions of XCode doesn’t create Localizable.strings file by default. 
To add ```Localizable.strings``` file, go to File->New->File , choose Strings File under Resource tab of iOS, name it Localizable.strings , and create the file and create it under ```Base.lproj``` folder.

![Creating Localizable.strings](https://github.com/Lenddo/react-native-sdk/wiki/Creating_Localizable.strings_file.png)

![Saving Localizable.strings](https://github.com/Lenddo/react-native-sdk/wiki/Saving_Localizable.strings_file.png)

Now, you have a Localizable.strings file for Base language as below. and then you must add the key-value pair below the image.

![Saving Localizable.strings](https://github.com/Lenddo/react-native-sdk/wiki/Localizable.strings_file.png)

```strings 
"back" = "Back";
 
// Cancel Dialog
"cancel_dialog_title" = "Cancel Process";
"cancel_dialog_message" = "Are you sure you want to cancel?";
"cancel_dialog_ok_button_text" = "Yes";
"cancel_dialog_cancel_button_text" = "No";
```

#### Registering onboarding callback
On your App.js, setup LenddoEFL onboarding callback to capture response from your ```showAuthorizeWithFormData``` call, by using RCTNativeEventEmitter to capture onboarding progress. As shown in LenddoEFL React-Native Demo app.

```javascript
import {Platform, NativeEventEmitter} from 'react-native';
import {RNOnboardingSdkWrapperIOS} from '@lenddo/react-native-sdk';
 
const OnboardingEventEmitter = Platform.OS == 'ios' ? new NativeEventEmitter(RNOnboardingSdkWrapperIOS) : DeviceEventEmitter;
 
export default class RNLenddoEFLSDKDemo extends PureComponent {    
    
    // Other method description
    
    
    componentWillMount() {
        OnboardingEventEmitter.addListener('onAuthorizeStarted',(params) => {
            // Do any source code before lenddo onboarding start.
            console.log("onAuthorizeStarted")
            console.log(params)
            }
        )
        OnboardingEventEmitter.addListener('onAuthorizeComplete',(params) => {
                console.log("onAuthorizeComplete")
                console.log(params)
            }
        )
        OnboardingEventEmitter.addListener('onAuthorizeCancelled',(params) => {
                console.log("onAuthorizeCancelled")
                console.log(params)
            }
        )
        OnboardingEventEmitter.addListener('onAuthorizeError',(params) => {
                console.log("onAuthorizeError")
                console.log(params)
            }
        )
        if (Platform.OS == 'android') {
            OnboardingEventEmitter.addListener('onAuthorizeFailure',(params) => {
                    console.log("onAuthorizeFailure")
                    console.log(params)
               }
            )
        }
    )
}
```
 
#### Adding Native Google SignIn

If you are required to have an email onboarding step, you should add a native google sign-in workflow, and [you can follow this instructions from LenddoEFL native iOS Onboarding and for more information](https://github.com/Lenddo/ios-lenddo-onboarding#8-google-sign-in-sdk-integration).
On the said procedure you will be asked to create ```GoogleProvider``` class **(You may copy the GoogleProvider.m and GoogleProvider.h file from the [React-Native Sample App source code](https://github.com/Lenddo/react-native-sdk-sample-app/tree/feature/add_ios_onboarding_library/ios))** and after having the class you need to send it unto RNOnboardingSdkIOS via your ```ApplicationDelegate```'s ```application: didFinishLaunchingWithOptions:``` method.
    
```objective-c

#import "RNOnboardingSdkWrapperIOS.h"
#import "GoogleProvider.h"

@implementation AppDelegate
 
- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {
  
  // Other member declartion
  
  // Create GoogleProvider object and add into a NSArray
  GoogleProvider *googleProvider = [[GoogleProvider alloc] init];
  NSArray *providers = [NSArray arrayWithObjects: googleProvider, nil];

  // Add GoogleProvider object into wrapper
  [RNOnboardingSdkWrapperIOS setProviders:providers];
  
  return YES;
}

```

#### Issue you might encounter for implementing GoogleSignIn when using CocoaPods 

Commonly used way of importing GoogleSignIn Library is through CocoaPods. Adding it up only on Podfile sometimes might not just do the work. As shown in the sample Podfile below, GoogleSignIn is properly imported

```
# Uncomment the next line to define a global platform for your project
# platform :ios, '9.0'

target 'RNLenddoEFLSdkWrapperDemo' do
  # Uncomment the next line if you're using Swift or would like to use dynamic frameworks
  # use_frameworks!

  # Pods for RNLenddoEFLSdkWrapperDemo
  pod 'GoogleSignIn'

  target 'RNLenddoEFLSdkWrapperDemoTests' do
    inherit! :search_paths
    # Pods for testing
  end

end

target 'RNLenddoEFLSdkWrapperDemo-tvOS' do
  # Uncomment the next line if you're using Swift or would like to use dynamic frameworks
  # use_frameworks!

  # Pods for RNLenddoEFLSdkWrapperDemo-tvOS

  target 'RNLenddoEFLSdkWrapperDemo-tvOSTests' do
    inherit! :search_paths
    # Pods for testing
  end

end
```

But you might encounter error below.

![framework not found GoogleSignIn](https://user-images.githubusercontent.com/25608370/37246165-6451dd0a-24a4-11e8-8a05-503022a70b8a.png)

To fix the problem,
1. Find the GoogleSignIn.framework file in “../ios/Pods/GoogleSignIn/Frameworks” and drag it into Xcode under the "Frameworks" section. In the dialog that pops up, uncheck "Copy items if needed", choose "Create groups", and ensure your main target is checked under "Add to targets".
2. Unlike LenddoEFLSdk.Framework, it is not require to add the the framework on "General" > “Embedded Binaries" section. Thus, it should add the `$(PROJECT_DIR)/Pods/GoogleSignIn/Frameworks` automatically in `Framework Search Paths`
3. In Xcode do "Product" -> "Clean".