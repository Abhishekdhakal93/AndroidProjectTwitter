package com.shresthagaurav.androidprojecttwitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Verification extends AppCompatActivity {
    TextView tx_veri;
    Button btn_next;

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
                startActivity( intent );
            }
        } );
    }

}
