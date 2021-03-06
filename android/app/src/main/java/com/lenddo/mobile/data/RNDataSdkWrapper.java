package com.lenddo.mobile.data;


import android.text.TextUtils;

import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lenddo.mobile.datasdk.AndroidData;
import com.lenddo.mobile.datasdk.DataManager;
import com.lenddo.mobile.datasdk.listeners.OnDataSendingCompleteCallback;
import com.lenddo.mobile.datasdk.models.ApplicationPartnerData;
import com.lenddo.mobile.datasdk.models.ClientOptions;
import com.lenddo.mobile.datasdk.utils.AndroidDataUtils;
import com.lenddo.mobile.core.LenddoCoreInfo;
import com.lenddo.mobile.core.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RNDataSdkWrapper extends ReactContextBaseJavaModule {
    public static final String PROVIDER_FACEBOOK = "facebook";
    public static final String PROVIDER_LINKEDIN = "linkedin";
    public static final String PROVIDER_YAHOO = "yahoo";
    public static final String PROVIDER_WINDOWSLIVE = "windowslive";
    public static final String PROVIDER_GOOGLE = "google";
    public static final String PROVIDER_KAKAOTALK = "kakaostory";
    public static final String PROVIDER_TWITTER = "twitter";
    private static final int FAIL = 0;
    private static final int SUCCESS = 1;
    private static final int ERROR = 2;
    private static final int CANCELLED = 3;
    private static final int STARTED = 4;

    private static final String TAG = "RNDataSdkWrapper";
    private ReactApplicationContext reactContext;
    private String partnerScriptId;
    private String apiSecret;
    private String partnerId;

    public RNDataSdkWrapper(ReactApplicationContext reactContext) {
        super(reactContext);
        Log.d(TAG, "RNDataSdkWrapper");
        this.reactContext = reactContext;
    }

    @ReactMethod
    public void setProviderAccessToken(String provider, String accessToken, String providerId, String extra_data, String expiration, final Callback callback) {
        Log.d(TAG, "setProviderAccessToken:: provider:: " + provider + ", accessToken:: " + accessToken + ", providerId:: " + providerId + ", extra_data:: " + extra_data + ", expiration:: " + expiration);
        if (callback != null) {
            AndroidData.setProviderAccessToken(reactContext, TextUtils.isEmpty(provider) ? "" : provider, TextUtils.isEmpty(accessToken) ? "" : accessToken, TextUtils.isEmpty(providerId) ? "" : providerId, extra_data, TextUtils.isEmpty(expiration) || expiration.equals("null") ? 0 : Long.valueOf(expiration), new OnDataSendingCompleteCallback() {
                @Override
                public void onDataSendingStart() {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", STARTED);
                    params.putInt("statusCode", 201);
                    params.putString("method", "setProviderAccessToken");
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("onDataSendingStart", params);
                }

                @Override
                public void onDataSendingSuccess() {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", SUCCESS);
                    params.putInt("statusCode", 200);
                    params.putString("method", "setProviderAccessToken");
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("onDataSendingSuccess", params);
                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Provider Access Token Callback: Success!");
                                        callback.invoke(SUCCESS, "Success!");
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }

                @Override
                public void onDataSendingError(final int statusCode, final String errorMessage) {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", ERROR);
                    params.putString("status", errorMessage);
                    params.putInt("statusCode", statusCode);
                    params.putString("method", "setProviderAccessToken");
                    reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingError", params);
                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Provider Access Token Callback: Error: " + errorMessage);
                                        callback.invoke(ERROR, "Error: " + errorMessage, statusCode);
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }

                @Override
                public void onDataSendingFailed(final Throwable t) {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", FAIL);
                    params.putString("status", t.getMessage());
                    params.putInt("statusCode", 500);
                    params.putString("method", "setProviderAccessToken");
                    reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingFailed", params);
                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Provider Access Token Callback: Failed: " + t.getMessage());
                                        callback.invoke(FAIL, "Failed: " + t.getMessage());
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }
            });
        } else {
            AndroidData.setProviderAccessToken(reactContext, TextUtils.isEmpty(provider) ? "" : provider, TextUtils.isEmpty(accessToken) ? "" : accessToken, TextUtils.isEmpty(providerId) ? "" : providerId, extra_data, TextUtils.isEmpty(expiration) || expiration.equals("null") ? 0 : Long.valueOf(expiration));
        }
    }

    @ReactMethod
    public void statisticsEnabled(Callback callback) {
        Log.d(TAG, "statisticsEnabled");
        try {
            callback.invoke(AndroidData.statisticsEnabled(reactContext));
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void clear() {
        Log.d(TAG, "clear");
        AndroidData.clear(reactContext);

        // Clear is a demo method, re-init lenddo core info after clearing
        LenddoCoreInfo.initCoreInfo(reactContext);
        LenddoCoreInfo.setDataPartnerScriptId(reactContext, this.partnerScriptId);
    }

    @ReactMethod
    public void sendPartnerApplicationData(String firstName, String middleName, String lastName,
                                           String dateOfBirth, String mobile, String home,
                                           String email, String employer, String university,
                                           String motherMaidenFirstName, String motherMaidenMiddleName,
                                           String motherMaidenLastName, String addressLine1,
                                           String addressLine2, String city, String administrativeRegion,
                                           String countryCode, String postalCode, String latitude,
                                           String longitude, String applicationId, String jsonPayload,
                                           final Callback callback) {
        ApplicationPartnerData.verification_data vd = new ApplicationPartnerData.verification_data();
        vd = new ApplicationPartnerData.verification_data();
        vd.address = new ApplicationPartnerData.verification_data.address();
        vd.employment_period = new ApplicationPartnerData.verification_data.employment_period();
        vd.mothers_maiden_name = new ApplicationPartnerData.verification_data.mothers_maiden_name();
        vd.name = new ApplicationPartnerData.verification_data.name();
        vd.phone = new ApplicationPartnerData.verification_data.phone();

        // Store data to model
        vd.name.first = TextUtils.isEmpty(firstName) ? null : firstName;
        vd.name.middle = TextUtils.isEmpty(middleName) ? null : middleName;
        vd.name.last = TextUtils.isEmpty(lastName) ? null : lastName;
        vd.date_of_birth = TextUtils.isEmpty(dateOfBirth) ? null : dateOfBirth;
        vd.phone.mobile = TextUtils.isEmpty(mobile) ? null : mobile;
        vd.phone.home = TextUtils.isEmpty(home) ? null : home;
        vd.email = TextUtils.isEmpty(email) ? null : email;
        vd.employer = TextUtils.isEmpty(employer) ? null : employer;
        vd.university = TextUtils.isEmpty(university) ? null : university;
        vd.mothers_maiden_name.first = TextUtils.isEmpty(motherMaidenFirstName) ? null : motherMaidenFirstName;
        vd.mothers_maiden_name.middle = TextUtils.isEmpty(motherMaidenMiddleName) ? null : motherMaidenMiddleName;
        vd.mothers_maiden_name.last = TextUtils.isEmpty(motherMaidenLastName) ? null : motherMaidenLastName;
        vd.address.line_1 = TextUtils.isEmpty(addressLine1) ? null : addressLine1;
        vd.address.line_2 = TextUtils.isEmpty(addressLine2) ? null : addressLine2;
        vd.address.city = TextUtils.isEmpty(city) ? null : city;
        vd.address.administrative_division = TextUtils.isEmpty(administrativeRegion) ? null : administrativeRegion;
        vd.address.country_code = TextUtils.isEmpty(countryCode) ? null : countryCode;
        vd.address.postal_code = TextUtils.isEmpty(postalCode) ? null : postalCode;

        if (!TextUtils.isEmpty(latitude)) {
            vd.address.latitude = Float.parseFloat(latitude);
        }

        if (!TextUtils.isEmpty(longitude)) {
            vd.address.longitude = Float.parseFloat(longitude);
        }

        ApplicationPartnerData apd = new ApplicationPartnerData();
        apd.reference_number = TextUtils.isEmpty(applicationId) ? null : applicationId;
        if (TextUtils.isEmpty(jsonPayload)) {
            apd.application = new JsonObject();
        } else {
            try {
                apd.application = new JsonParser().parse(jsonPayload).getAsJsonObject();
            } catch (Exception e) {
                e.printStackTrace();
                apd.application = new JsonObject();
            }
        }
        apd.verification_data = vd;

        String payload = new GsonBuilder().serializeSpecialFloatingPointValues().disableHtmlEscaping().create().toJson(apd);
        this.sendPartnerApplicationData(payload, callback);
    }

    @ReactMethod
    public void sendPartnerApplicationData(String payload, final Callback callback) {
        Log.d(TAG, "sendPartnerApplicationData:: payload:: " + payload);
        if (callback != null) {
            AndroidData.sendPartnerApplicationData(reactContext, payload, new OnDataSendingCompleteCallback() {
                @Override
                public void onDataSendingStart() {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", STARTED);
                    params.putInt("statusCode", 201);
                    params.putString("method", "sendPartnerApplicationData");
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("onDataSendingStart", params);
                }

                @Override
                public void onDataSendingSuccess() {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", SUCCESS);
                    params.putInt("statusCode", 200);
                    params.putString("method", "sendPartnerApplicationData");
                    reactContext
                            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                            .emit("onDataSendingSuccess", params);
                            
                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Partner Data Callback: Success!");
                                        callback.invoke(SUCCESS, "Success!");
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }

                @Override
                public void onDataSendingError(final int statusCode, final String errorMessage) {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", ERROR);
                    params.putString("status", errorMessage);
                    params.putInt("statusCode", statusCode);
                    params.putString("method", "sendPartnerApplicationData");
                    reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingError", params);

                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Partner Data Callback: Error: " + errorMessage);
                                        callback.invoke(ERROR, "Error: " + errorMessage, statusCode);
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }

                @Override
                public void onDataSendingFailed(final Throwable t) {
                    WritableMap params = Arguments.createMap();
                    params.putInt("action", FAIL);
                    params.putString("status", t.getMessage());
                    params.putInt("statusCode", 500);
                    params.putString("method", "sendPartnerApplicationData");
                    reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingFailed", params);
                    UiThreadUtil.runOnUiThread(
                            new GuardedRunnable(reactContext) {
                                @Override
                                public void runGuarded() {
                                    try {
                                        Log.d(TAG, "Send Partner Data Callback: Failed: " + t.getMessage());
                                        callback.invoke(FAIL, "Failed: " + t.getMessage());
                                    } catch (Exception e) {
                                        //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                    }
                                }
                            });
                }
            });
        } else {
            AndroidData.sendPartnerApplicationData(reactContext, payload, null);
        }
    }


    @ReactMethod
    public void submitFormFillingAnalytics() {
        Log.d(TAG, "submitFormFillingAnalytics");
        AndroidData.submitFormFillingAnalytics(reactContext);
    }

    @Override
    public String getName() {
        return "RNDataSdkWrapper";
    }


    @ReactMethod
    public void setup() {
        AndroidData.setup(reactContext, null);
    }

    
    /**
     * Please use registerDataSendingCompletionCallback
     * @param callback - React native callback to capture data sending callback
     */
    @Deprecated
    @ReactMethod
    public void setupWithCallback(final Callback callback) {
        Log.d(TAG, "setupWithCallback");
        this.registerDataSendingCompletionCallback(callback);
    }

    @ReactMethod
    public void registerDataSendingCompletionCallback(final Callback callback) {
        Log.d(TAG, "registerDataSendingCompletionCallback");
        DataManager.getInstance().getClientOptions().registerDataSendingCompletionCallback(new OnDataSendingCompleteCallback() {
            @Override
            public void onDataSendingStart() {
                WritableMap params = Arguments.createMap();
                params.putInt("action", STARTED);
                params.putInt("statusCode", 201);
                params.putString("method", "registerDataSendingCompletionCallback");
                reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingStart", params);
            }
            
            @Override
            public void onDataSendingSuccess() {
                WritableMap params = Arguments.createMap();
                params.putInt("action", SUCCESS);
                params.putInt("statusCode", 200);
                params.putString("method", "registerDataSendingCompletionCallback");
                reactContext
                        .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                        .emit("onDataSendingSuccess", params);

                UiThreadUtil.runOnUiThread(
                        new GuardedRunnable(reactContext) {
                            @Override
                            public void runGuarded() {
                                try {
                                    Log.d(TAG, "Data Sending Callback: Success!");
                                    callback.invoke(SUCCESS, "Success!");
                                } catch (Exception e) {
                                    //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                }
                            }
                        });
            }

            @Override
            public void onDataSendingError(final int statusCode, final String errorMessage) {
                WritableMap params = Arguments.createMap();
                params.putInt("action", ERROR);
                params.putString("status", errorMessage);
                params.putInt("statusCode", statusCode);
                params.putString("method", "registerDataSendingCompletionCallback");
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onDataSendingError", params);

                UiThreadUtil.runOnUiThread(
                        new GuardedRunnable(reactContext) {
                            @Override
                            public void runGuarded() {
                                try {
                                    Log.d(TAG, "Data Sending Callback: Error: " + errorMessage);
                                    callback.invoke(ERROR, "Error: " + errorMessage, statusCode);
                                } catch (Exception e) {
                                    //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                }
                            }
                        });
            }

            @Override
            public void onDataSendingFailed(final Throwable t) {
                WritableMap params = Arguments.createMap();
                params.putInt("action", FAIL);
                params.putString("status", t.getMessage());
                params.putInt("statusCode", 500);
                params.putString("method", "registerDataSendingCompletionCallback");
                reactContext
                    .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                    .emit("onDataSendingFailed", params);

                UiThreadUtil.runOnUiThread(
                        new GuardedRunnable(reactContext) {
                            @Override
                            public void runGuarded() {
                                try {
                                    Log.d(TAG, "Data Sending Callback: Failed: " + t.getMessage());
                                    callback.invoke(FAIL, "Failed: " + t.getMessage());
                                } catch (Exception e) {
                                    //Catches the exception: java.lang.RuntimeException·Illegal callback invocation from native module
                                }
                            }
                        });
            }
        });
    }

    
    /**
     * Please use applyClientOptions
     */
    @Deprecated
    @ReactMethod
    public void setupWithClientOptions() {
        Log.d(TAG, "setupWithClientOptions");
        this.applyClientOptions();
    }

    @ReactMethod
    public void applyClientOptions() {
        Log.d(TAG, "applyClientOptions");
        DataManager.getInstance().setClientOptions(RNClientOptions.getInstance().getClientOptions());
    }

    @ReactMethod
    public void startAndroidData(String applicationId) {
        Log.d(TAG, "startAndroidData:: applicationId:: " + applicationId);
        AndroidData.startAndroidData(getCurrentActivity(), applicationId);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(PROVIDER_FACEBOOK, AndroidData.PROVIDER_FACEBOOK);
        constants.put(PROVIDER_LINKEDIN, AndroidData.PROVIDER_LINKEDIN);
        constants.put(PROVIDER_YAHOO, AndroidData.PROVIDER_YAHOO);
        constants.put(PROVIDER_WINDOWSLIVE, AndroidData.PROVIDER_WINDOWSLIVE);
        constants.put(PROVIDER_GOOGLE, AndroidData.PROVIDER_GOOGLE);
        constants.put(PROVIDER_KAKAOTALK, AndroidData.PROVIDER_KAKAOTALK);
        constants.put(PROVIDER_TWITTER, AndroidData.PROVIDER_TWITTER);
        return constants;
    }

    @ReactMethod
    public void getApplicationId(Callback callback) {
        Log.d(TAG, "getApplicationId");
        try {
            callback.invoke(AndroidDataUtils.getApplicationId(reactContext));
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void getDeviceUID(Callback callback) {
        Log.d(TAG, "getDeviceUID");
        try {
            callback.invoke(AndroidDataUtils.getDeviceUID(reactContext));
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void getServiceToken(Callback callback) {
        Log.d(TAG, "getServiceToken");
        try {
            callback.invoke(AndroidDataUtils.getServiceToken(reactContext));
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void setApplicationId(String applicationId) {
        Log.d(TAG, "setApplicationId: " + applicationId);
        AndroidDataUtils.setApplicationId(reactContext, applicationId);
    }

    @ReactMethod
    public void getApiSecret(Callback callback) {
        Log.d(TAG, "getApiSecret");
        try {
            callback.invoke(apiSecret);
        } catch (Exception e) {

        }
    }


    @ReactMethod
    public void setPartnerScriptId(String partnerScriptId) {
        this.partnerScriptId = partnerScriptId;
        LenddoCoreInfo.setDataPartnerScriptId(reactContext, this.partnerScriptId);
        Log.d(TAG, "setPartnerScriptId: " + partnerScriptId);
    }

    @ReactMethod
    public void getPartnerScriptId(Callback callback) {
        Log.d(TAG, "getPartnerScriptId: " + this.partnerScriptId);
        try {
            callback.invoke(this.partnerScriptId);
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
        LenddoCoreInfo.setCoreInfo(reactContext, LenddoCoreInfo.COREINFO_API_SECRET, apiSecret);
        Log.d(TAG, "setApiSecret: " + apiSecret);
    }

    @ReactMethod
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
        Log.d(TAG, "setPartnerId: " + partnerId);
    }

    @java.lang.Override
    @ReactMethod
    public java.lang.String toString() {
        return "RNDataSdkWrapper";
    }

}
