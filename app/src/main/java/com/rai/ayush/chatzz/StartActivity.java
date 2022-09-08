package com.rai.ayush.chatzz;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button LBtn;
    private Button RegBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);
    RegBtn=findViewById(R.id.start_reg_btn);
        LBtn=findViewById(R.id.haveacc);
    RegBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent reg_intent = new Intent(StartActivity.this,RegisterActivity.class);
            startActivity(reg_intent);
        }
    });
        LBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent login_intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(login_intent);
            }
        });
    }
}
