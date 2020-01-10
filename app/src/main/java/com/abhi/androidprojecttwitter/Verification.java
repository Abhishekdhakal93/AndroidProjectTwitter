package com.abhi.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abhi.androidprojecttwitter.R;

public class Verification extends AppCompatActivity {
    TextView tx_veri;
    Button btn_next;
    ImageView Ve_back;
    String email = "";
    String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_variafction );
        tx_veri = findViewById( R.id.code );
        btn_next = findViewById( R.id.btn_V_login );


        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep( 1000 );
                    tx_veri.setText( "1,2,5,4,0,6" );
                    Bundle bundle = getIntent().getExtras();
                    if (bundle != null) {
                        email = bundle.getString( "email" );
                        username = bundle.getString( "username" );
                        Log.d( "email", email );
                        Log.d( "username", username );

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tx_veri.getText().toString().isEmpty()) {
                    Toast.makeText( Verification.this, "Wait", Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent( Verification.this, Password.class );
                intent.putExtra( "email", email );
                intent.putExtra( "username", username );
                startActivity( intent );
            }
        } );
    }

}

