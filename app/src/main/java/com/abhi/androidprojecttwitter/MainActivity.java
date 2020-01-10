package com.abhi.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abhi.androidprojecttwitter.R;

public class MainActivity extends AppCompatActivity {
    TextView twLogin;
    Button createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        twLogin = findViewById( R.id.twLogin );
        createUser = findViewById( R.id.createUser );
        twLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent( MainActivity.this,Login_activity.class );
                startActivity( login );
            }
        } );
        createUser.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent signUP= new Intent( MainActivity.this,SignUP.class );
                                               startActivity( signUP );
                                           }
                                       }
        );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
