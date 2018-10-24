package com.example.binia_000.adcmp;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNameSignUp;
     private EditText mEmailSignUp;
    private EditText mMajorSignUp;
    private EditText mUserNameSignUp;
    private EditText mPasswordSignUp;
    private Button signedUpButton;

    private ProgressDialog progressDialog;


    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        mEmailSignUp = (EditText) findViewById(R.id.emailSignUp);
        mMajorSignUp = (EditText) findViewById(R.id.majorSignUp);
        mNameSignUp = (EditText) findViewById(R.id.nameSignUp);
        mPasswordSignUp = (EditText) findViewById(R.id.passwordSignUp);
        signedUpButton = (Button) findViewById(R.id.signedUpButton);

        signedUpButton.setOnClickListener(this);





    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            //handle  the already logged in user
        }
    }

    private void registerUser(){
        String name = mNameSignUp.getText().toString().trim();
        String email = mEmailSignUp.getText().toString().trim();
        String password = mPasswordSignUp.getText().toString().trim();
        String major= mMajorSignUp.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your e-mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter your password",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(name)){
            Toast.makeText(this,"Please enter your name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(major)){
            Toast.makeText(this,"Please enter your major",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfully registered
                            Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "Error in Registering User", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    public void onClick(View view){
        if(view == signedUpButton){
            registerUser();
        }

    }


}
