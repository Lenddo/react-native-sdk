package com.lenddo.mobile.data;


import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lenddo.mobile.datasdk.AndroidData;
import com.lenddo.mobile.datasdk.listeners.OnDataSendingCompleteCallback;
import com.lenddo.mobile.datasdk.models.ApplicationPartnerData;
import com.lenddo.mobile.datasdk.models.ClientOptions;
import com.lenddo.mobile.datasdk.mpermission.MPermissionActivity;
import com.lenddo.mobile.datasdk.utils.AndroidDataUtils;
import com.lenddo.mobile.datasdk.DataManager;
import com.lenddo.mobile.core.LenddoCoreInfo;
import com.lenddo.mobile.core.Log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RNClientOptions extends ReactContextBaseJavaModule {
    private static final String TAG = "RNClientOptions";
    private Callback callback;
    private ClientOptions clientOptions;
    private static RNClientOptions mInstance = null;

    public static synchronized void initClientOptions(ReactApplicationContext reactContext) {
        if (mInstance == null) {
            mInstance = new RNClientOptions(reactContext);
        }
    }

    public static synchronized RNClientOptions getInstance() {
        if (mInstance == null){ //if there is no instance available... create new one
            throw new NullPointerException("Call initClientOptions before calling getInstance");
        }
        return mInstance;
    }

    public RNClientOptions(ReactApplicationContext reactContext) {
        super(reactContext);

        Log.d(TAG, "RNClientOptions");
        refreshClientOptions();
    }

    public ClientOptions getClientOptions() {
        return clientOptions;
    }

    @Override
    public String getName() {
        return "RNClientOptions";
    }

    @ReactMethod
    public void getClientOptions(Callback callback) {
        Log.d(TAG, "getClientOptions: " + this.clientOptions);
        try {
            callback.invoke(this.clientOptions);
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void refreshClientOptions() {
        int[] customMPermissionLayout = DataManager.getInstance().getClientOptions().getCustomMPermissionLayout();

        this.clientOptions = new ClientOptions();
        this.setCustomMPermissionLayout(customMPermissionLayout[MPermissionActivity.INDEX_LAYOUT_INTRO], 
            customMPermissionLayout[MPermissionActivity.INDEX_LAYOUT_RATIONALE], 
            customMPermissionLayout[MPermissionActivity.INDEX_LAYOUT_FEEDBACK], 
            customMPermissionLayout[MPermissionActivity.INDEX_LAYOUT_THANKYOU]);
    }

    @ReactMethod
    public void setCustomMPermissionLayout(int intro, int rationale, int feedback, int thankyou) {
        this.clientOptions.setCustomMPermissionLayout(intro, rationale, feedback, thankyou);
    }

    @ReactMethod
    public void getCustomMPermissionLayout(Callback callback) {
        Log.d(TAG, "getCustomMPermissionLayout: " + this.clientOptions.getCustomMPermissionLayout());
        try {
            callback.invoke(this.clientOptions.getCustomMPermissionLayout());
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void isCustomMPermission(Callback callback) {
        Log.d(TAG, "isCustomMPermission: " + this.clientOptions.isCustomMPermission());
        try {
            callback.invoke(this.clientOptions.isCustomMPermission());
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void isWifiOnly(Callback callback) {
        Log.d(TAG, "isWifiOnly: " + this.clientOptions.isWifiOnly());
        try {
            callback.invoke(this.clientOptions.isWifiOnly());
        } catch (Exception e) {

        }
    }

    @ReactMethod
    public void setWifiOnly(boolean wifiOnly) {
        this.clientOptions.setWifiOnly(wifiOnly);
    }

    @ReactMethod
    public void setThemeColor(String color) {
        this.clientOptions.setThemeColor(color);
    }

    @ReactMethod
    public void getThemeColor(Callback callback) {
        Log.d(TAG, "getThemeColor: " + this.clientOptions.getThemeColor());
        try {
            callback.invoke(this.clientOptions.getThemeColor());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void disableSMSDataCollection() {
        this.clientOptions.disableSMSDataCollection();
    }

    @ReactMethod
    public void disableCallLogDataCollection() {
        this.clientOptions.disableCallLogDataCollection();
    }

    @ReactMethod
    public void disableContactDataCollection() {
        this.clientOptions.disableContactDataCollection();
    }

    @ReactMethod
    public void disableCalendarEventDataCollection() {
        this.clientOptions.disableCalendarEventDataCollection();
    }

    @ReactMethod
    public void disableInstalledAppDataCollection() {
        this.clientOptions.disableInstalledAppDataCollection();
    }

    @ReactMethod
    public void disableBrowserHistoryDataCollection() {
        this.clientOptions.disableBrowserHistoryDataCollection();
    }

    @ReactMethod
    public void disableLocationDataCollection() {
        this.clientOptions.disableLocationDataCollection();
    }

    @ReactMethod
    public void disableBattChargeDataCollection() {
        this.clientOptions.disableBattChargeDataCollection();
    }

    @ReactMethod
    public void disableGalleryMetaDataCollection() {
        this.clientOptions.disableGalleryMetaDataCollection();
    }

    @ReactMethod
    public void disableMediaMetaDataCollection() {
        this.clientOptions.disableMediaMetaDataCollection();
    }

    @ReactMethod
    public void disableTelephonyInfoDataCollection() {
        this.clientOptions.disableTelephonyInfoDataCollection();
    }

    @ReactMethod
    public void disableStoredFilesInformationCollection() {
        this.clientOptions.disableStoredFilesInformationCollection();
    }

    @ReactMethod
    public void disableSensorsCollection() {
        this.clientOptions.disableSensorsCollection();
    }

    @ReactMethod
    public void disableLauncherAppsCollection() {
        this.clientOptions.disableLauncherAppsCollection();
    }

    @ReactMethod
    public void disableWifiInfoCollection() {
        this.clientOptions.disableWifiInfoCollection();
    }

    @ReactMethod
    public void disableBluetoothInfoCollection() {
        this.clientOptions.disableBluetoothInfoCollection();
    }

    @ReactMethod
    public void disableAccountsInfoCollection() {
        this.clientOptions.disableAccountsInfoCollection();
    }

    @ReactMethod
    public void disableGmailLabelsInfoCollection() {
        this.clientOptions.disableGmailLabelsInfoCollection();
    }
    @ReactMethod
    public void disablePeriodicalDataGathering() {
        this.clientOptions.disablePeriodicalDataGathering();
    }

    @ReactMethod
    public void disableSMSBodyCollection() {
        this.clientOptions.disableSMSBodyCollection();
    }

    @ReactMethod
    public void enablePhoneNumberHashing() {
        this.clientOptions.enablePhoneNumberHashing();
    }

    @ReactMethod
    public void enableContactsNameHashing() {
        this.clientOptions.enableContactsNameHashing();
    }

    @ReactMethod
    public void enableContactsEmailHashing() {
        this.clientOptions.enableContactsEmailHashing();
    }

    @ReactMethod
    public void enableCalendarOrganizerHashing() {
        this.clientOptions.enableCalendarOrganizerHashing();
    }

    @ReactMethod
    public void enableCalendarDisplayNameHashing() {
        this.clientOptions.enableCalendarDisplayNameHashing();
    }

    @ReactMethod
    public void enableCalendarEmailHashing() {
        this.clientOptions.enableCalendarEmailHashing();
    }

    @ReactMethod
    public void enableSMSDataCollection() {
        this.clientOptions.enableSMSDataCollection();
    }

    @ReactMethod
    public void enableCallLogDataCollection() {
        this.clientOptions.enableCallLogDataCollection();
    }

    @ReactMethod
    public void isEnableSMS(Callback callback) {
        Log.d(TAG, "isEnableSMS: " + this.clientOptions.isEnableSMS());
        try {
            callback.invoke(this.clientOptions.isEnableSMS());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableCallLog(Callback callback) {
        Log.d(TAG, "isEnableCallLog: " + this.clientOptions.isEnableCallLog());
        try {
            callback.invoke(this.clientOptions.isEnableCallLog());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableContact(Callback callback) {
        Log.d(TAG, "isEnableContact: " + this.clientOptions.isEnableContact());
        try {
            callback.invoke(this.clientOptions.isEnableContact());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableCalendarEvent(Callback callback) {
        Log.d(TAG, "isEnableCalendarEvent: " + this.clientOptions.isEnableCalendarEvent());
        try {
            callback.invoke(this.clientOptions.isEnableCalendarEvent());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableInstalledApp(Callback callback) {
        Log.d(TAG, "isEnableInstalledApp: " + this.clientOptions.isEnableInstalledApp());
        try {
            callback.invoke(this.clientOptions.isEnableInstalledApp());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableBrowserHistory(Callback callback) {
        Log.d(TAG, "isEnableBrowserHistory: " + this.clientOptions.isEnableBrowserHistory());
        try {
            callback.invoke(this.clientOptions.isEnableBrowserHistory());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableLocation(Callback callback) {
        Log.d(TAG, "isEnableLocation: " + this.clientOptions.isEnableLocation());
        try {
            callback.invoke(this.clientOptions.isEnableLocation());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableBatteryCharge(Callback callback) {
        Log.d(TAG, "isEnableBatteryCharge: " + this.clientOptions.isEnableBatteryCharge());
        try {
            callback.invoke(this.clientOptions.isEnableBatteryCharge());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableGalleryMetaData(Callback callback) {
        Log.d(TAG, "isEnableGalleryMetaData: " + this.clientOptions.isEnableGalleryMetaData());
        try {
            callback.invoke(this.clientOptions.isEnableGalleryMetaData());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableMediaMetaData(Callback callback) {
        Log.d(TAG, "isEnableMediaMetaData: " + this.clientOptions.isEnableMediaMetaData());
        try {
            callback.invoke(this.clientOptions.isEnableMediaMetaData());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableTelephonyInformation(Callback callback) {
        Log.d(TAG, "isEnableTelephonyInformation: " + this.clientOptions.isEnableTelephonyInformation());
        try {
            callback.invoke(this.clientOptions.isEnableTelephonyInformation());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableStoredFilesInformation(Callback callback) {
        Log.d(TAG, "isEnableStoredFilesInformation: " + this.clientOptions.isEnableStoredFilesInformation());
        try {
            callback.invoke(this.clientOptions.isEnableStoredFilesInformation());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableSensors(Callback callback) {
        Log.d(TAG, "isEnableSensors: " + this.clientOptions.isEnableSensors());
        try {
            callback.invoke(this.clientOptions.isEnableSensors());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableLauncherApps(Callback callback) {
        Log.d(TAG, "isEnableLauncherApps: " + this.clientOptions.isEnableLauncherApps());
        try {
            callback.invoke(this.clientOptions.isEnableLauncherApps());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableWifiInfo(Callback callback) {
        Log.d(TAG, "isEnableWifiInfo: " + this.clientOptions.isEnableWifiInfo());
        try {
            callback.invoke(this.clientOptions.isEnableWifiInfo());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableBluetoothInfo(Callback callback) {
        Log.d(TAG, "isEnableBluetoothInfo: " + this.clientOptions.isEnableBluetoothInfo());
        try {
            callback.invoke(this.clientOptions.isEnableBluetoothInfo());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableAccountsInfo(Callback callback) {
        Log.d(TAG, "isEnableAccountsInfo: " + this.clientOptions.isEnableAccountsInfo());
        try {
            callback.invoke(this.clientOptions.isEnableAccountsInfo());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableGmailLabelsInfo(Callback callback) {
        Log.d(TAG, "isEnableGmailLabelsInfo: " + this.clientOptions.isEnableGmailLabelsInfo());
        try {
            callback.invoke(this.clientOptions.isEnableGmailLabelsInfo());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnablePeriodicalData(Callback callback) {
        Log.d(TAG, "isEnablePeriodicalData: " + this.clientOptions.isEnablePeriodicalData());
        try {
            callback.invoke(this.clientOptions.isEnablePeriodicalData());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableSMSBody(Callback callback) {
        Log.d(TAG, "isEnableSMSBody: " + this.clientOptions.isEnableSMSBody());
        try {
            callback.invoke(this.clientOptions.isEnableSMSBody());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnablePhoneNumberHashing(Callback callback) {
        Log.d(TAG, "isEnablePhoneNumberHashing: " + this.clientOptions.isEnablePhoneNumberHashing());
        try {
            callback.invoke(this.clientOptions.isEnablePhoneNumberHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableContactsNameHashing(Callback callback) {
        Log.d(TAG, "isEnableContactsNameHashing: " + this.clientOptions.isEnableContactsNameHashing());
        try {
            callback.invoke(this.clientOptions.isEnableContactsNameHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableContactsEmailHashing(Callback callback) {
        Log.d(TAG, "isEnableContactsEmailHashing: " + this.clientOptions.isEnableContactsEmailHashing());
        try {
            callback.invoke(this.clientOptions.isEnableContactsEmailHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableCalendarOrganizerHashing(Callback callback) {
        Log.d(TAG, "isEnableCalendarOrganizerHashing: " + this.clientOptions.isEnableCalendarOrganizerHashing());
        try {
            callback.invoke(this.clientOptions.isEnableCalendarOrganizerHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableCalendarDisplayNameHashing(Callback callback) {
        Log.d(TAG, "isEnableCalendarDisplayNameHashing: " + this.clientOptions.isEnableCalendarDisplayNameHashing());
        try {
            callback.invoke(this.clientOptions.isEnableCalendarDisplayNameHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void isEnableCalendarEmailHashing(Callback callback) {
        Log.d(TAG, "isEnableCalendarEmailHashing: " + this.clientOptions.isEnableCalendarEmailHashing());
        try {
            callback.invoke(this.clientOptions.isEnableCalendarEmailHashing());
        } catch (Exception e) {
            Log.e(TAG, "Error: ", e);
        }
    }

    @ReactMethod
    public void setApiGatewayUrl(String endPoint) {
        this.clientOptions.setApiGatewayUrl(endPoint);
    }

    @ReactMethod
    public void enableLogDisplay(boolean isEnable) {
        this.clientOptions.enableLogDisplay(isEnable);
    }

    @java.lang.Override
    @ReactMethod
    public java.lang.String toString() {
        return "RNClientOptions";
    }

    @ReactMethod(isBlockingSynchronousMethod=true)
    public int resource(String key) throws Exception {
        Activity activity = this.getCurrentActivity();
        if (activity != null) {
            return activity.getResources().getIdentifier(key, null, null);
        }
        return View.NO_ID;
    }
}
