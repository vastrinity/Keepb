

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
        import com.google.firebase.auth.AuthCredential;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.EmailAuthProvider;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

public class deleteUser extends BaseActivity
{


    private static final String TAG = "EmailPassword";


    private EditText mEmailField;
    private EditText mPasswordField;

    // [START declare_auth]
    private FirebaseAuth mAuth;
    Button deletemyaccount,Forgot;

    ProgressBar deletePB;

    // [END declare_auth]

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);


        deletePB=findViewById(R.id.progressDU);

        setProgressBar(R.id.progressDU);
        hideProgressBar();


        mEmailField = findViewById(R.id.usernameDU);
        mPasswordField = findViewById(R.id.passwordDU);



        // Buttons

        deletemyaccount=findViewById(R.id.deleteUserbtn);
       deletemyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               deleteUserAccount();
            }
        });



        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]


        Forgot=findViewById(R.id.fpDU);
        Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(deleteUser.this, forgotPassword.class);
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





    private void deleteUserAccount(){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        showProgressBar();

        // Get auth credentials from the user for re-authentication. The example below shows
        // email and password credentials but there are multiple possible providers,
        // such as GoogleAuthProvider or FacebookAuthProvider.
        AuthCredential credential = EmailAuthProvider
                .getCredential(mEmailField.getText().toString(), mPasswordField.getText().toString());

        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "User account deleted.");
                                            Intent intent = new Intent(deleteUser.this, enter.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                    }
                });

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



}
