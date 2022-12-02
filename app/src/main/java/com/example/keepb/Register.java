package com.example.keepb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends  BaseActivity {
    private static final String TAG = "EmailPassword";

    private FirebaseAuth mAuth;
    private ProgressBar progreessRE;
    private EditText UsernameRe,pass1,pass2,NameRegister;
    private Button RegisterNow,verifyRe,SignInRe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        UsernameRe=findViewById(R.id.userRe);
        pass1=findViewById(R.id.passRe);
        pass2=findViewById(R.id.PassConRe);
        NameRegister=findViewById(R.id.Namere);
        RegisterNow = findViewById(R.id.registerRe);
        SignInRe=findViewById(R.id.SignInRe);
        SignInRe.setVisibility(View.GONE);

        verifyRe=findViewById(R.id.verifyRe);
        verifyRe.setVisibility(View.GONE);
        progreessRE=findViewById(R.id.progressRE);
       setProgressBar(R.id.progressRE);
       hideProgressBar();

        mAuth = FirebaseAuth.getInstance();


        RegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(UsernameRe.getText().toString(), pass1.getText().toString());


            }
        });

        verifyRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendEmailVerification();


            }
        });


        SignInRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openlogin();
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();


    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        showProgressBar();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            RegisterNow.setVisibility(View.GONE);
                            verifyRe.setVisibility(View.VISIBLE);




                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            String message =task.getException().toString();
                            Toast.makeText(Register.this, "Authentication failed."+message,
                                    Toast.LENGTH_LONG).show();

                        }

                        // [START_EXCLUDE]

                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
        hideProgressBar();
    }
    private void deleteUserAccount(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        showProgressBar();

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(UsernameRe.getText().toString(), pass1.getText().toString());

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                });

    }
    private void sendEmailVerification() {


        // Disable button


        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button


                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this,
                                    "Check Your mail Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_LONG).show();
                            verifyRe.setVisibility(View.GONE);
                            SignInRe.setVisibility(View.VISIBLE);



                        } else {
                            Log.e(TAG, "sendEmailVerification", task.getException());
                            Toast.makeText(Register.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                            deleteUserAccount();
                            RegisterNow.setVisibility(View.VISIBLE);


                        }
                        // [END_EXCLUDE]
                    }
                });
        hideProgressBar();
        // [END send_email_verification]
    }
    private void openlogin() {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }







    private boolean validateForm() {
        boolean valid = true;

        String email = UsernameRe.getText().toString();
        UsernameRe.requestFocus();
        if (TextUtils.isEmpty(email)) {
            UsernameRe.setError("Required.");
            valid = false;
        } else {
            UsernameRe.setError(null);
        }

        String password1 = pass1.getText().toString();
      pass1.requestFocus();
        if (TextUtils.isEmpty(password1)) {
           pass1.setError("Required.");
            valid = false;
        } else {
            pass1.setError(null);
        }


       String password2 = pass2.getText().toString();
        pass2.requestFocus();
        if (TextUtils.isEmpty(password2)) {
            pass2.setError("Required.");
            valid = false;
        } else {
            pass2.setError(null);
        }

        String nameOfUser =NameRegister.getText().toString();
        NameRegister.requestFocus();
        if (TextUtils.isEmpty(nameOfUser)) {
           NameRegister.setError("Required.");
            valid = false;
        } else {
            NameRegister.setError(null);
        }

        if(!(password1.equals(password2))){
            pass2.setError("επιβεβαιωστε το Password");
            valid = false;
        }

        else {
            pass2.setError(null);
        }








        return valid;
    }


}