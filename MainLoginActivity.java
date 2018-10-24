package com.example.binia_000.adcmp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainLoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserName;
    private EditText mPassword;
    private Button SignUp;
    private Button Login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);


        SignUp = (Button) findViewById(R.id.buttonSignUp);
        Login = (Button)findViewById(R.id.loginButton);
        mUserName = (EditText) findViewById(R.id.UserName);
        mPassword = (EditText) findViewById(R.id.Password);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        progressDialog = new ProgressDialog(this);

       Login.setOnClickListener(this);
       SignUp.setOnClickListener(this);


    }


    private void userLogin(){
        String email =  mUserName.getText().toString().trim();
        String password =  mPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter your UCSC E-mail",Toast.LENGTH_SHORT).show();
            return;
        }if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Verifying Login....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                    }
                });
    }
    public void onClick(View view){
       if(view == Login){
           userLogin();
        }
        if(view == SignUp){
           finish();
           startActivity((new Intent(this,SignUpActivity.class)));
        }

    }



}
