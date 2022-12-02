package com.example.keepb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends BaseActivity{
    EditText useremail;
    Button sendMeEmail;
    ProgressBar progressBarrFp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mAuth=FirebaseAuth.getInstance();

        useremail=findViewById(R.id.useremail);
        sendMeEmail=findViewById(R.id.fpBtn);

        progressBarrFp=findViewById(R.id.progressBarfp);
        setProgressBar(R.id.progressBarfp);
        hideProgressBar();





        sendMeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail=useremail.getText().toString();
                showProgressBar();


                if(TextUtils.isEmpty(userEmail)){
                    useremail.setError("Δωστε το email σας");
                }
                else {
                    mAuth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(forgotPassword.this,"check your mail",Toast.LENGTH_LONG);
                                startActivity(new Intent(forgotPassword.this,login.class));
                            }
                            else{
                                String messageerror = task.getException().getMessage();
                                Toast.makeText(forgotPassword.this,"Kατι πηγε στραβα"+messageerror,Toast.LENGTH_LONG);

                            }



                        }
                    });
                }
            }
        });



    }
}
