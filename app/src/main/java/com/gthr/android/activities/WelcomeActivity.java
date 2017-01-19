package com.gthr.android.activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gthr.android.R;
import com.gthr.android.animations.Techniques;
import com.gthr.android.animations.YoYo;
import com.gthr.android.application.App;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


public class WelcomeActivity extends ApplicationActivity implements View.OnKeyListener {
    public static Activity welcomeActivity;
    private String phone = "";
    private EditText phone_input = null;
    private static long last_authentication_attempt = 0;
    private static Boolean changing_text = false;
    private String currentCountryCode;
    private String currentCountryShortText;
    private TextWatcher textWatcher = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        welcomeActivity = this;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onCreateActivity() {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
        }
        return false;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//            acceptContactsAgreement(phone_input);
            return super.dispatchKeyEvent(e);
        }
        return super.dispatchKeyEvent(e);
    }

    private void initializeVariables(){



    }



}
