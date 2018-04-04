package com.example.waldrich.timetotarget;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StartScreen extends AppCompatActivity {

    //http://hyperphysics.phy-astr.gsu.edu/hbase/grav.html#bul
    //http://www.millettsights.com/resources/shooting-tips/mathematics-for-precision-shooters/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
    }

    public void login(View view) {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void newUser(View view) {
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }
}
