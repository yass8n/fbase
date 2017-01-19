package com.gthr.android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.gthr.android.R;
import com.gthr.android.animations.Techniques;
import com.gthr.android.animations.YoYo;
import com.gthr.android.application.App;
import com.gthr.android.application.Helper;
import com.gthr.android.singleton.FirebaseSingleton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaseen on 3/16/16.
 */
public class IntroActivity extends ApplicationActivity  {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private MediaPlayer mMediaPlayer;
    private TextureView mTextureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    final Map<String, Object> post = new HashMap<>();


                    post.put("user_id", 1);
                    post.put("fname", "test");
                    post.put("lname", "test");
                    post.put("picture", "test");
                    post.put("timestamp", "test");
                    post.put("comment", "test");
                    post.put("flagged", "test");;
                    post.put("comment_picture", "test");


                    final String path = "/comments" ;

                    FirebaseDatabase.getInstance().getReferenceFromUrl(App.FB_DATABASE_URL + path).push().setValue(post);
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    final Map<String, Object> post = new HashMap<>();


                    post.put("user_id", 1);
                    post.put("fname", "test");
                    post.put("lname", "test");
                    post.put("picture", "test");
                    post.put("timestamp", "test");
                    post.put("comment", "test");
                    post.put("flagged", "test");;
                    post.put("comment_picture", "test");


                    final String path = "/comments" ;

                    FirebaseDatabase.getInstance().getReferenceFromUrl(App.FB_DATABASE_URL + path).push().setValue(post);
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        FirebaseSingleton firebaseSingleton = new FirebaseSingleton();
        firebaseSingleton.signIn(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
