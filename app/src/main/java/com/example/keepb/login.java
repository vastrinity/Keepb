/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.keepb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends BaseActivity implements CompoundButton.OnCheckedChangeListener
     {

         private static final String USERNAME = "username";
         private static final String PASSWORD = "password";
         public static final String SHARED_PREFS_LOGIN = "sharedPrefsLogin";


    private static final String TAG = "EmailPassword";


    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    Button signIn,Forgot;
    CheckBox SignIncheckBox;
    ProgressBar loginPB;

    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LOGIN, MODE_PRIVATE);

        loginPB=findViewById(R.id.progressSI);

        setProgressBar(R.id.progressSI);
        hideProgressBar();


        mEmailField = findViewById(R.id.username);
        mPasswordField = findViewById(R.id.password);
        SignIncheckBox=findViewById(R.id.SignIncheckBox);
        SignIncheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int remember =SignIncheckBox.isChecked()?1:0;
                if(remember==1){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(USERNAME,mEmailField.getText().toString());
                    editor.putString(PASSWORD,mPasswordField.getText().toString());
                    editor.apply();
                    Toast.makeText(login.this,"Θα σε θυμαμαι",Toast.LENGTH_SHORT);

                }

            }
        });
        if (sharedPreferences.contains(USERNAME)) {
            mEmailField.setText(sharedPreferences.getString(USERNAME,""));
            mPasswordField.setText(sharedPreferences.getString(PASSWORD,""));
        }


        // Buttons

        signIn=findViewById(R.id.SignInbtn);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn(mEmailField.getText().toString(), mPasswordField.getText().toString());
            }
        });



        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        Forgot=findViewById(R.id.fp);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgotPassword.class);
                startActivity(intent);
            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }
    // [END on_start_check_user]



    private void signIn(String email, String password) {

        showProgressBar();




        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }




        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(!verified(user)){
                                Toast.makeText(login.this,"verify your mail first",Toast.LENGTH_LONG);
                                return;
                            }
                            else {
                            openMain();}
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                           Toast.makeText(login.this,"Προβλημα....",Toast.LENGTH_SHORT);
                        }
                        hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    private void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public boolean verified(FirebaseUser user){
        boolean validemail = true;

        if(user.isEmailVerified()){
            validemail=true;
        }
        else{


            validemail=false;
        }
        return validemail;

    }




    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        mEmailField.requestFocus();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        mPasswordField.requestFocus();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }


         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

         }
     }
