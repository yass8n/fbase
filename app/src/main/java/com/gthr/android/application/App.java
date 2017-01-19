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


    public static final String FB_DATABASE_URL = "https://blinding-torch-5580.firebaseio.com/";
    public static final String SENDER_ID = "865830595955";

    @Override
    public void onCreate() {
        super.onCreate();
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
    }

}
