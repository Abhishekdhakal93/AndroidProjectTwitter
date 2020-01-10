package com.abhi.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.abhi.androidprojecttwitter.R;

public class Final_Step_signUP extends AppCompatActivity {
    Button btn_signup;
    String email="";
    String username="";

    TextView tvemail, tvuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_final__step_sign_up );
        btn_signup = findViewById( R.id.btn_FS_signup );
        tvemail=findViewById( R.id.email_final );
        tvuser=findViewById( R.id.username_final );

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email= bundle.getString( "email" );
            username= bundle.getString( "username" );
            tvemail.setText( bundle.getString( "email" ) );
            tvuser.setText( bundle.getString( "username" ) );
        }
        btn_signup.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( Final_Step_signUP.this, Verification.class );
                intent.putExtra( "email",email );
                intent.putExtra( "username",username );
                startActivity( intent );
            }
        } );
tvuser.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        returnto();
    }
} );
tvemail.setOnClickListener( new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        returnto();
    }
} );

    }
    void returnto(){
        Intent returns=new Intent( Final_Step_signUP.this,SignUP.class );
        returns.putExtra( "email",email );
        returns.putExtra( "username" ,username);
        startActivity( returns );

    }
}
