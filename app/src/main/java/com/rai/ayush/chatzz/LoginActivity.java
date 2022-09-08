package com.rai.ayush.chatzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout Email;
    private TextInputLayout Password;
    private Button Loginbtn;
    private Toolbar toolbar;
    private ProgressDialog loginprogress;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        toolbar=(Toolbar)findViewById(R.id.login_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //reg fields
        loginprogress=new ProgressDialog(this);
        Email=(TextInputLayout)findViewById(R.id.login_email);
        Password=(TextInputLayout)findViewById(R.id.login_password);
        Loginbtn=findViewById(R.id.login_btn);
        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=Email.getEditText().getText().toString();
                String password=Password.getEditText().getText().toString();
                if(!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password))
                {
                    login_user(email,password);
                    loginprogress.setTitle("Logging In");
                    loginprogress.setMessage("Please wait while we check your credentials");
                    loginprogress.setCanceledOnTouchOutside(false);
                    loginprogress.show();
                }


            }
        });
    }

    private void login_user(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            loginprogress.dismiss();
                            Intent mainintent = new Intent(LoginActivity.this,MainActivity.class);
                            mainintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(mainintent);
                            finish();


                        } else {
                            // If sign in fails, display a message to the user.
                            loginprogress.hide();
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}
