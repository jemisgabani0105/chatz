package com.rai.ayush.chatzz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private TextInputLayout DisplayName;
    private TextInputLayout Email;
    private TextInputLayout Password;
    private Button Createbtn;
    private Toolbar toolbar;
    private ProgressDialog regprogress;
    //firebase
    private DatabaseReference database;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //firebase
        mAuth = FirebaseAuth.getInstance();
        regprogress=new ProgressDialog(this);
        toolbar=(Toolbar)findViewById(R.id.register_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //reg fields
        DisplayName=(TextInputLayout)findViewById(R.id.reg_display_name);
        Email=(TextInputLayout)findViewById(R.id.reg_email);
        Password=(TextInputLayout)findViewById(R.id.reg_password);
        Createbtn=findViewById(R.id.reg_create_btn);
        Createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name=DisplayName.getEditText().getText().toString();
                String email=Email.getEditText().getText().toString();
                String password=Password.getEditText().getText().toString();
                if(!TextUtils.isEmpty(display_name)||!TextUtils.isEmpty(email)||!TextUtils.isEmpty(password))
                {
                    register_user(display_name,email,password);
                    regprogress.setTitle("Registering User");
                    regprogress.setMessage("Please wait while we create your account");
                    regprogress.setCanceledOnTouchOutside(false);
                    regprogress.show();
                }


            }
        });
    }

    public void register_user(final String displayname, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                           /* FirebaseUser current_user=FirebaseAuth.getInstance().getCurrentUser();
                            String uid=current_user.getUid();
                            database=FirebaseDatabase.getInstance().getReference().child("users").child(uid);

                            HashMap<String,String> usermap=new HashMap<>();
                            usermap.put("name",displayname);
                            usermap.put("status","Hi there, I'm using Chatzz app ...!!");
                            usermap.put("image","default");
                            usermap.put("thumb_image","default");
                            database.setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {*/
                                        regprogress.dismiss();
                                        Intent mainIntent = new Intent(RegisterActivity.this,MainActivity.class);
                                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(mainIntent);
                                        finish();
                                  /*  }
                                }
                            });*/


                        } else {
                            // If sign in fails, display a message to the user.
                            regprogress.hide();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
}



