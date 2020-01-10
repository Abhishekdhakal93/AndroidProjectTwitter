package com.abhi.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.abhi.androidprojecttwitter.R;
import com.abhi.twitter.api.ApiClass;
import com.abhi.twitter.model.Check;
import com.abhi.twitter.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUP extends AppCompatActivity {
    EditText sn_email, sn_username;
    ImageView sn_Us, sn_Em, back;
    Button btn_next;
    int countUsername = 0;
    int initialbtn = 0;
    String method = "email";
    String Email = "";
    String Username = "";
    boolean chekU=false;
    boolean chekE=false;
    TextView tvChange, sn_em_error, sn_us_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sign_up );
        sn_email = findViewById( R.id.SN_email );
        sn_username = findViewById( R.id.SN_usernmae );
        back=findViewById( R.id.SN_back );
        sn_em_error = findViewById( R.id.SN_pass_error );
        sn_us_error = findViewById( R.id.SN_username_error );
        sn_Us = findViewById( R.id.SN_userP );
        sn_Em = findViewById( R.id.SN_emailP );
        btn_next = findViewById( R.id.btn_FS_signup );
        tvChange = findViewById( R.id.textView9 );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Email = bundle.getString( "email" );
            Username = bundle.getString( "username" );
            sn_email.setText( bundle.getString( "email" ) );
            sn_username.setText( bundle.getString( "username" ) );
        }
        back.setOnClickListener( new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent back=new Intent( SignUP.this,MainActivity.class );
                                         startActivity( back );
                                     }
                                 }
        );
        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekE==true&&chekU==true){

                        User user = new User( Email );
                    Checkuser( user );

                }else{
                    Toast.makeText( SignUP.this, "fill require field with valid information", Toast.LENGTH_SHORT ).show();
                    return;
                }

            }
        } );
        sn_username.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countL = sn_username.length();
                if (count > 0) {
                    if (countUsername >= 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.BLACK );
                        sn_us_error.setText( "" + countUsername );
                        sn_Us.setImageResource( R.drawable.ic_checked );
                        chekU=true;
                        Username = sn_username.getText().toString();
                        return;
                    } else if (countUsername < 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.RED );
                        sn_us_error.setText( "Must be 50 characters or fewer." );
                        sn_us_error.append( "      " + countUsername );
                        chekU=false;
                        sn_Us.setImageResource( R.drawable.ic_clear );
                        return;


                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        tvChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initialbtn == 0) {
                    method = "phone";
                    sn_email.setText( "" );
                    initialbtn++;
                    sn_email.setHint( "used Phone number" );
                    sn_email.setInputType( InputType.TYPE_CLASS_PHONE );
                    sn_email.setMaxLines( 13 );
                    tvChange.setText( "use email instead" );
                    return;
                } else {
                    method = "email";
                    sn_email.setText( "" );
                    initialbtn = 0;
                    sn_email.setInputType( InputType.TYPE_CLASS_TEXT );
                    sn_email.setHint( "used Email" );
                    tvChange.setText( "use phone instead" );
                    return;
                }

            }
        } );
        sn_email.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch (method) {
                    case "email":
                        sn_em_error.setText( "" );
                        if ((sn_email.getText().toString().toLowerCase().contains( "@" )) && (sn_email.getText().toString().toLowerCase().contains( ".com" ))) {
                            sn_Em.setImageResource( R.drawable.ic_checked );
                            Email = sn_email.getText().toString();
                            chekE=true;
                        } else {
                            sn_em_error.setText( "check your email" );
                            sn_Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;

                        }
                        break;
                    case "phone":
                        sn_em_error.setText( "" );
                        if ((sn_email.length() != 10)) {
                            sn_em_error.setText( "check your number" );
                            sn_Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;
                            return;

                        } else {
                            sn_Em.setImageResource( R.drawable.ic_checked );
                            Email = sn_email.getText().toString();
                            chekE=true;
                            return;

                        }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }

    void Checkuser(User us) {
        ApiClass apiClass = new ApiClass();
        Call<Check> checkCall = apiClass.calls().check( us );
        checkCall.enqueue( new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( SignUP.this, "error" + response.code(), Toast.LENGTH_SHORT ).show();
                    Log.d( "error", "error" + response.code() );
                    return;
                }
                Check check = response.body();
                //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                if (check.getStatus().equals( "good to go" )) {
                    Intent next = new Intent( SignUP.this, Customize.class );
                    next.putExtra( "email", Email );
                    next.putExtra( "username", Username );
                    startActivity( next );
                    return;
                } else {
                    //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                    sn_em_error.setText( "exited" );
                    sn_em_error.setTextColor( Color.RED );
                }
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Toast.makeText( SignUP.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                Log.d( "error", "error   " + t.getLocalizedMessage() );

            }
        } );
    }
}
