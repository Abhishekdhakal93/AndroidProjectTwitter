package com.shresthagaurav.androidprojecttwitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.shresthagaurav.androidprojecttwitter.model.User;

public class SplashScreen extends AppCompatActivity {
    ProgressBar progressBar;
    private Handler handler = new Handler();
    int initialPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );
        progressBar = findViewById( R.id.progressBar );


        RunProBar();
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep( 2000 );
                    CheckUser();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    public void RunProBar() {
        initialPoint = 40;
        new Thread( new Runnable() {
            @Override
            public void run() {
                while (initialPoint < 100) {
                    initialPoint += 30;
                    handler.post( new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress( initialPoint );
                        }
                    } );
                    try {
                        Thread.sleep( 2000 );

                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                    if (initialPoint >= 100) {


                    }

                }

            }
        } ).start();
    }

    void CheckUser() {
        SharedPreferences sharedPreferences = getSharedPreferences( "User", MODE_PRIVATE );
        String username = sharedPreferences.getString( "username", "" );
        String password = sharedPreferences.getString( "password", "" );
        if(username.isEmpty()&&password.isEmpty()){
            Intent intent = new Intent( SplashScreen.this,MainActivity.class );
            startActivity( intent );
            return;
        }

        Intent login = new Intent( SplashScreen.this,Login_activity.class );
        login.putExtra( "email",username );
        login.putExtra( "password",password );
        startActivity( login );


    }
}
