package com.abhi.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abhi.androidprojecttwitter.R;

public class Customize extends AppCompatActivity {
String email,username;
Button btn_next;
TextView tv_help;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_customize );
        btn_next=findViewById( R.id.btn_cus_next );

        tv_help=findViewById( R.id.Help );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email= bundle.getString( "email" );
            username= bundle.getString( "username" );}
        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Customize.this, Final_Step_signUP.class );
                intent.putExtra( "email",email );
                intent.putExtra( "username",username );
                startActivity( intent );
            }
        } );
        tv_help.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData( Uri.parse("https://help.twitter.com/en"));
                startActivity(intent);
            }
        } );

    }
}
