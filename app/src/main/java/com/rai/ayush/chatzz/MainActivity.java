package com.rai.ayush.chatzz;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar toolbar;

    private ViewPager viewPager;
    private SecPagerAdapter secPagerAdapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.INTERNET,Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        mAuth=FirebaseAuth.getInstance();

        toolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Chatzz");

        //tabs
        viewPager=(ViewPager) findViewById(R.id.MaintabPager);
        secPagerAdapter=new SecPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(secPagerAdapter);
        tabLayout=(TabLayout)findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser== null)
        {
           sendtoStart();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_btn)
        {
            FirebaseAuth.getInstance().signOut();
            sendtoStart();
        }
        return true;
    }
    private void sendtoStart()
    {
        Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }
}
