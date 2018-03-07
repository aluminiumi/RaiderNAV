package com.deaftone.tableware.raidernav.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.deaftone.tableware.raidernav.R;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not yet implemented!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });

        Button vsb = (Button) findViewById(R.id.viewschedulesbutton);
        vsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ScheduleActivity.class), 1);
            }
        });

        /*button=(Button) findViewById(R.id.createSchedule);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddCourseActivity.class), 2);
                //openCreateScheduleScreen();
            }
        });*/

    }
    /*public void openCreateScheduleScreen(){
        Intent intent = new Intent(this,AddCourseActivity.class);
        startActivity(intent);
    }*/

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        //System.out.println("MainActivity: onActivityResult: Got activity result");
        System.out.println("MainActivity: onActivityResult: requestCode: "+requestCode+", resultCode: "+resultCode);
        if (requestCode == 1 || requestCode == 2) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
                //System.out.println(RESULT_OK);
                startActivityForResult(new Intent(MainActivity.this,ScheduleActivity.class), 1);
                //startActivity(new Intent(Intent.ACTION_VIEW, data));
            }
        }

        //((InteractiveMasterListArrayAdapter) adapter).list = sh.getMasterList();
        //adapter.notifyDataSetChanged();
        //finish(); //go back to main activity; can't seem to figure out why list view won't refresh
        //adapter = new InteractiveMasterListArrayAdapter(this, sh.getMasterList());
        //setListAdapter(adapter);
    }

}
