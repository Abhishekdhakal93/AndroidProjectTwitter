package com.shresthagaurav.androidprojecttwitter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Password extends AppCompatActivity {
    EditText txtpass;
    TextView txterror;
    ImageButton imageButton;
    int i=0;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_password );
        txtpass = findViewById( R.id.txtPass );
        txterror = findViewById( R.id.txtxP_error );
        imageButton = findViewById( R.id.btn_P_showpass );
        btnlogin = findViewById( R.id.btn_P_login );

        txtpass.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (txtpass.length() >= 6) {
                    txterror.setTextColor( Color.BLACK );
                    txterror.setText( "" );
                    txtpass.setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( "#00acee" ) ) );
               return; } else {
                    txtpass.setBackgroundTintList( ColorStateList.valueOf( Color.RED ) ) ;
                    txterror.setTextColor( Color.RED );
                    txterror.setText( "password must be more than 6 chracters" );
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i==0){
                    txtpass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    i++;
                }else{
                    txtpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    i=0;
                }

            }
        } );
    }
}