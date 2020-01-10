package com.abhi.twitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.abhi.androidprojecttwitter.R;
import com.abhi.twitter.adapter.InterestsAdapter;
import com.abhi.twitter.model.DataSet;

import java.util.ArrayList;
import java.util.List;

public class Interested extends AppCompatActivity {
RecyclerView recyclerView;
Button btn_next;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_interested );
        recyclerView=findViewById( R.id.interestsRv );
        btn_next=findViewById( R.id.inte_next );
        textView=findViewById( R.id.inte_tv );

        List<DataSet> interestsList = new ArrayList<>();
        interestsList.add( new DataSet("Sport", "NFL", "NBA", "MLB","Soccer","NHL"));
        interestsList.add( new DataSet("News", "Weather", "History", "Politics","Health","game"));
        interestsList.add( new DataSet("Music", "Pop", "Hip-Hop", "Country","Rock","game"));
        interestsList.add( new DataSet("Country", "Nepal", "China", "Japan","Korea","game"));

        InterestsAdapter interestsAdapter = new InterestsAdapter(this, interestsList);
        recyclerView.setAdapter( interestsAdapter );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );
        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );

    }
    void NextTO(){
        Intent intent = new Intent( Interested.this,DashBoard.class );
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
