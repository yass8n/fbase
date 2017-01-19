package com.gthr.android.application;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.gthr.android.R;
import com.gthr.android.activities.ApplicationActivity;
import com.gthr.android.activities.IntroActivity;



import com.gthr.android.singleton.FirebaseSingleton;

import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class App extends Application {

    public static final boolean PRODUCTION = false;

    public static final String FB_DATABASE_URL = "https://blinding-torch-5580.firebaseio.com/";
    public static final String SENDER_ID = "865830595955";


//    --------------------------------------------------------

    public static final int[] colorArray = {R.color.dark_green,
            R.color.normal_greean, R.color.dark_blue, R.color.purple,
            R.color.orangee, R.color.dark_orange, R.color.red};

    public static final String[] hex_array = {"#16a085",
            "#29b664", "#2980b9", "#8e44ad",
            "#f39c12", "#d35400", "#c0392b"};

    public static final String[] hex_lists_array = {"#7c4589",
            "#e94b35", "#00a185", "#2b3e51",
            "#29b66b", "#f59d00"};

    public static final int[] listsColorArray = {R.color.get_coffee,
            R.color.listen_to_music, R.color.dance, R.color.happy_hour,
            R.color.watch_movies, R.color.adventure};

//    public static final HashMap<String, Integer> color_hash = initializeColorHash();

    private static App thisApp;

    public static SimpleDateFormat db_date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean IS_RUNNING = false;

    public static boolean IS_SIGNING_IN = false;

    public static boolean IS_SIGNING_UP = false;

    public static String phone = "";

    public static String iso_code = "";

    public static String first_name = "";

    public static String last_name = "";


    public static String token = null;


    public static Handler HANDLER = new Handler();

    public static Runnable IS_IN_BACKGROUND_RUNNABLE = null;

    public static Runnable CLOSE_APP_RUNNABLE = null;

    public static boolean on_start = true;

    public static boolean IS_IN_BACKGROUND = true;

    public static boolean successfully_loaded_contacts_and_friends = false;

    public static boolean loading_contacts_and_friends = false;

    public static boolean get_user_data_call_completed = false;


    // You have to use it like: TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, getResources().getDisplayMetrics());;
    public static int INVITE_VIEW_HEIGHT;

    public static final int MAX_INVITE_CHARS = 80;

    public static final int MAX_LIST_CHARS = 40;

    public static boolean CAN_CHANGE_STATUS_BAR_COLOR;

    public static DatabaseReference FIREBASE;

    public static MixpanelAPI MIXPANEL = null;

    public static boolean isInitialized = false;

    public static final String WHITE_IMAGE_URL = "https://s3-us-west-1.amazonaws.com/assets.gatherwith.us/assets/white_image.jpg";

    private static boolean first_activity_created = false;
//    private Receiver receiver = new Receiver();

//    public static boolean receiver_registered = false;


    public static long last_error_toast = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        App.thisApp = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        //MIGRATING DB IF NEEDED
        try {
            Realm realm = Realm.getInstance(realmConfiguration);
            realm.close();
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(realmConfiguration);
                //Realm file has been deleted.
                Realm realm = Realm.getInstance(realmConfiguration);
                realm.close();
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }




        //DONE MIGRATING
        Realm.setDefaultConfiguration(realmConfiguration);
        inializeAllSingletons();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(final Activity activity,
                                          Bundle savedInstanceState) {

                if (!App.first_activity_created) {
                    App.first_activity_created = true;

                }

                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



                if (!App.successfully_loaded_contacts_and_friends
                        && !App.loading_contacts_and_friends) {


                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {

                            TwitterAuthConfig authConfig = new TwitterAuthConfig("f6CtF2Atk070Vy71Ijs0i6gt7", "bgAHsu7tgOTuvswmrl7iEV8URQWQ3WXNkj3jpWca0mAWWCjNaG");
                            Fabric.with(activity, new TwitterCore(authConfig), new TweetComposer());
                            if (App.PRODUCTION) {
                                Fabric.with(activity, new Crashlytics());
                            }

                            INVITE_VIEW_HEIGHT = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 140, activity.getResources().getDisplayMetrics());

                            registerInBackground();
                        }
                    });

                }

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }


        });

        App.runCreateAppCode();
        setupFireBaseInstance();
        App.iso_code = getResources().getConfiguration().locale.getCountry();
    }


    private static void registerInBackground() {

        class RegisterGCM extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... params) {
                String regid = "";
                try {
                    GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(ApplicationActivity.activity.getApplicationContext());
                    regid = gcm.register(App.SENDER_ID);

                } catch (Exception ex) {
                    regid = "";
                }
                return regid;
            }

            @Override
            protected void onPostExecute(final String regid) {

                super.onPostExecute(regid);
            }
        }

        RegisterGCM reg_gcm = new RegisterGCM();
        reg_gcm.execute();
    }

    private void inializeAllSingletons(){

    }

    public static String getRegistrationId(Context context, final SharedPreferences user_pref) {
        String registrationId = user_pref.getString("device_token", "");
        if (registrationId.isEmpty()) {
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing registration ID is not guaranteed to work with
        // the new app version.
        int registeredVersion = user_pref.getInt("app_version_number", 0);
        int currentVersion = getAppVersion(context);
        if (registeredVersion != currentVersion) {
            return "";
        }
        return registrationId;
    }

    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int findIndexOfColor(String color) {
        int i;
        for (i = 0; i < hex_array.length; i++) {
            if (color.equals(hex_array[i])) {
                break;
            }
        }
        if (i == hex_array.length)
            i = 0;
        return i;
    }

    public static int indexOfListColor(int color) {
        int i;
        for (i = 0; i < listsColorArray.length; i++) {
            if (color == listsColorArray[i]) {
                break;
            }
        }
        if (i == listsColorArray.length)
            i = 0;
        return i;
    }

    public static String getToken() {

        return token;
    }
    public static void runCreateAppCode() {
        App.isInitialized = true;
        App.setAppCanChangeStatusBar();
        FacebookSdk.sdkInitialize(App.thisApp);
        IS_IN_BACKGROUND = false;

    }


    private static void doneLoadingUserDataBroadcast() {
        Intent ntent = new Intent("com.gthr.android.activities.doneLoadingUserData");
        ApplicationActivity.activity.sendBroadcast(ntent);
        final SharedPreferences user_pref = ApplicationActivity.activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        if (!user_pref.contains("doneLoadingUserDataFirstTime")) {
            SharedPreferences.Editor editor = user_pref.edit();
            editor.putString("doneLoadingUserDataFirstTime", "DONE");
            editor.apply();
            Intent ntent2 = new Intent("com.gthr.android.activities.doneLoadingUserDataFirstTime");
            ApplicationActivity.activity.sendBroadcast(ntent2);
        }
    }

    public static void setToFailed() {
        successfully_loaded_contacts_and_friends = false;
        loading_contacts_and_friends = false;
        get_user_data_call_completed = true;
        Intent ntent = new Intent("com.gthr.android.activities.doneLoadingUserData");
        ApplicationActivity.activity.sendBroadcast(ntent);
    }

    public static void badInternet() {
        if (ApplicationActivity.activity != null) {
            toast(ApplicationActivity.activity, "Error occurred, please check your internet", Toast.LENGTH_SHORT);
        }
    }

    public static void toast(final Context context, final String text, final int length) {
        if (SystemClock.elapsedRealtime() - App.last_error_toast > 500) {
            last_error_toast = SystemClock.elapsedRealtime();
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, text, length).show();
                }
            });
        }
    }

    public static void slowInternet() {
        if (ApplicationActivity.activity != null) {
            toast(ApplicationActivity.activity, "Load timeout. Please check your internet connection and try again.", Toast.LENGTH_LONG);
        }
    }


    public static void logOut() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.close();
    }

    public static void setUsersSharedPreferences(Activity activity, JSONObject user, String token, String regid, String phone) throws JSONException {
        SharedPreferences sharedpreferences = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();

        editor.putString("token", token);
        editor.putString("user_id", user.getString("user_id"));
        editor.putString("first", user.getString("first"));
        editor.putString("last", user.getString("last"));
        editor.putString("username", user.getString("username"));
        editor.putString("picture", user.getString("picture"));
        editor.putString("phone", phone);
        editor.putString("color", user.getString("color"));

        if (user.has("location")) {
            editor.putString("lat", user.getJSONObject("location").getString("latitude"));
            editor.putString("lon", user.getJSONObject("location").getString("longitude"));
        }

        editor.putString("device_token", regid);
        editor.putString("version_1.4", "DONE");
        if (user.has("iso_code")) editor.putString("iso_code", user.getString("iso_code"));
        if (user.has("location_hidden")) editor.putBoolean("location_hidden", user.getBoolean("location_hidden"));

        if (!regid.equals("")) {
            int appVersion = App.getAppVersion(ApplicationActivity.activity.getApplicationContext());
            editor.putInt("app_version_number", appVersion);
        }
        editor.commit();
        logOut();
    }

    public static void updateUserPreferences(final JSONObject user) throws JSONException {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences = ApplicationActivity.activity.getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                try {
                    editor.putString("last", user.getString("last"));
                    editor.putString("first", user.getString("first"));
                    editor.putString("username", user.getString("username"));
                    editor.putString("picture", user.getString("picture"));
                    editor.putString("color", user.getString("color"));

                    if (user.has("location")) {
                        editor.putString("lat", user.getJSONObject("location").getString("latitude"));
                        editor.putString("lon", user.getJSONObject("location").getString("longitude"));
                    }

                    editor.putBoolean("location_hidden", user.getBoolean("location_hidden"));

                    if (user.has("iso_code")) editor.putString("iso_code", user.getString("iso_code"));
                    editor.apply();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public static void setAppCanChangeStatusBar() {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion > android.os.Build.VERSION_CODES.LOLLIPOP) {
            CAN_CHANGE_STATUS_BAR_COLOR = true;
        } else {
            CAN_CHANGE_STATUS_BAR_COLOR = false;
        }
    }

    public static void setStatusBarColor(Activity activity, int color, String background) {
        if (CAN_CHANGE_STATUS_BAR_COLOR) {
            Window window = activity.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            float[] hsv = new float[3];
            Color.colorToHSV(color, hsv);
            hsv[2] *= 0.9f;
            color = Color.HSVToColor(hsv);

            if (background.equals("light")) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                window.getDecorView().setSystemUiVisibility(View.STATUS_BAR_VISIBLE);
            }
        }
    }

    public static void setStatusBarColorWithoutDarken(Activity activity, int color, String background) {
        if (CAN_CHANGE_STATUS_BAR_COLOR) {
            Window window = activity.getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


            if (background.equals("light")) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                window.getDecorView().setSystemUiVisibility(View.STATUS_BAR_VISIBLE);
            }
        }
    }

    public static void setStatusBarTransparent(Activity activity) {
        if (CAN_CHANGE_STATUS_BAR_COLOR) {
            Window window = activity.getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public static void setStatusBarVisibility(Activity activity, boolean visible) {
        Window window = activity.getWindow();
        if (visible) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void setupFireBaseInstance() {


    }

    public static void resetNotifCounts() {

    }

    private static HashMap<String, Integer> initializeColorHash() {
        HashMap<String, Integer> hash = new HashMap<>();
        for (int i = 0; i < hex_array.length; i++) {
            hash.put(hex_array[i], colorArray[i]);
        }
        return hash;
    }

    public static void addNumberOfInvites(final Activity activity) {
        (new Handler()).post(new Runnable() {
            @Override
            public void run() {
                SharedPreferences user_pref = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = user_pref.edit();
                if (!user_pref.contains("numberOfInvites")) {
                    editor.putInt("numberOfInvites", 1);
                    editor.apply();
                } else {
                    int new_number = user_pref.getInt("numberOfInvites", 0) + 1;
                    editor.putInt("numberOfInvites", new_number);
                    editor.apply();
                }
            }
        });
    }

    private void removeTestingDataFromPref() {
        SharedPreferences user_pref = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = user_pref.edit();
        editor.remove("contacts_agreement_accepted");
        editor.remove("doneLoadingUserDataFirstTime"); //so we get the broadcast to run again in app.java
        editor.commit();
    }
}
