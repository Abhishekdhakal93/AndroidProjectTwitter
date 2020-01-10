package com.abhi.twitter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.abhi.androidprojecttwitter.R;
import com.abhi.twitter.api.LoginBLL;
import com.abhi.twitter.model.User;
import com.abhi.twitter.strictMode.StrictModeClass;

public class Login_activity extends AppCompatActivity {
    EditText et_email, et_password;
    ImageButton ib_show_P;
    Button Tx_sp;
    Button btn_login;
    public static String Token = "";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login_activity );
        Tx_sp = findViewById( R.id.Tx_pp );
        et_email = findViewById( R.id.login_email );
        et_password = findViewById( R.id.login_password );
        ib_show_P = findViewById( R.id.btn_SP );
        btn_login = findViewById( R.id.btn_login );
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            String username,password;
            username=bundle.getString( "email" );
            password=bundle.getString( "password" );
            User user = new User( username, password );
            login( user );

        }

        ib_show_P.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    et_password.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    i++;
                } else {
                    et_password.setInputType( InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD );
                    i = 0;
                }

            }
        } );
        Tx_sp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent( Login_activity.this, SignUP.class );
                startActivity( back );
            }
        } );
        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty( et_email.getText().toString() )) {
                    if (!TextUtils.isEmpty( et_password.getText().toString() )) {
                        User u = new User( et_email.getText().toString(),
                                et_password.getText().toString() );
                        login( u );
                    } else {
                        et_password.setError( "empty" );
                    }
                } else {
                    et_email.setError( "empty" );
                }
            }
        } );
    }

    public boolean login(User u) {
        LoginBLL loginBLL = new LoginBLL();
        StrictModeClass.StrictMode();
        if (loginBLL.checkUser( u.getEmail(), u.getPassword() )) {
            Store( u );
            Intent intent = new Intent( Login_activity.this, DashBoard.class );
            Token = loginBLL.Token;
            startActivity( intent );
            //Toast.makeText( this, "welcome "+loginBLL.Token,Toast.LENGTH_SHORT ).show();
            return true;
        }
        Toast.makeText( this, "Either username or password is incorrect", Toast.LENGTH_SHORT ).show();
        return false;

    }

    void Store(User u) {

        SharedPreferences sharedPreferences = getSharedPreferences( "User", MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString( "username", u.getEmail() );
        editor.putString( "password", u.getPassword() );
        //Toast.makeText( this, "saved user", Toast.LENGTH_SHORT ).show();
        editor.commit();

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent back=new Intent( Login_activity.this,MainActivity.class );
        startActivity( back );
    }
}
