package com.shresthagaurav.androidprojecttwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class YourSelf extends AppCompatActivity {
Button btn_next;
TextView tv_skip;
EditText et_bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_your_self );
        tv_skip=findViewById( R.id.tv_bio_skip );
        btn_next=findViewById( R.id.btn_bio_next);
        et_bio=findViewById( R.id.et_bio );
        tv_skip.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );
        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_bio.getText().toString().isEmpty()){
                    Toast.makeText( YourSelf.this, "fill your bio first", Toast.LENGTH_SHORT ).show();
                return;}
                else{
                    NextTO();
                }
            }
        } );
    }
    void NextTO(){
        Intent intent = new Intent( YourSelf.this,Interested.class );
        startActivity( intent );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent back=new Intent( YourSelf.this,MainActivity.class );
//        startActivity( back );
        triggerRebirth( this );
    }
    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }
}
