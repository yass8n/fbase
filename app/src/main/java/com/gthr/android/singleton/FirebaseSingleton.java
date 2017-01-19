package com.gthr.android.singleton;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.gthr.android.application.App;
import com.gthr.android.application.Helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yaseen on 6/8/16.
 */
public class FirebaseSingleton {
    private static boolean isVerified = false;

    public void signIn(Activity activity){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously()
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()){
                            Log.d("", "");
                        } else {
                            isVerified  = true;

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

                        }
                    }
                });
    }

}
