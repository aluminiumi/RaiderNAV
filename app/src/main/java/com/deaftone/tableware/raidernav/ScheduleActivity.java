package com.deaftone.tableware.raidernav;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

//public class ScheduleActivity extends AppCompatActivity {
public class ScheduleActivity extends ListActivity {
    ScheduleHandler sh;
    ArrayAdapter<ScheduleEntryList> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //final TextView tv = findViewById(R.id.savedText);
        //fh = new ScheduleFileHandler(getApplicationContext());
        sh = new ScheduleHandler(getApplicationContext());
        adapter = new InteractiveArrayAdapter(this, sh.getMasterList());
        //adapter.setNotifyOnChange(true);
        setListAdapter(adapter);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button loadbutton = (Button) findViewById(R.id.loadButton);
        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                adapter.notifyDataSetChanged();
                getListView().invalidateViews();
                getListView().refreshDrawableState();
                //((InteractiveArrayAdapter) adapter).list = sh.getMasterList();
                //adapter.notifyDataSetChanged();

                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //tv.setText(fh.readFile());
                //tv.setText(fileContents);
            }
        });

        Button createbutton = (Button) findViewById(R.id.createButton);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh.generateAnotherRandomSchedule();
                adapter.notifyDataSetChanged();
                Snackbar.make(view, "New schedule created", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    protected void onPause() {
        adapter.notifyDataSetChanged();
        super.onPause();
    }

    @Override
    protected void onStart() {
        adapter.notifyDataSetChanged();
        super.onStart();
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        System.out.println("Got activity result");
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                // A contact was picked.  Here we will just display it
                // to the user.
                System.out.println(RESULT_OK);
                //startActivity(new Intent(Intent.ACTION_VIEW, data));
            }
        }
        //((InteractiveArrayAdapter) adapter).list = sh.getMasterList();
        adapter.notifyDataSetChanged();
        finish(); //go back to main activity; can't seem to figure out why list view won't refresh
        //adapter = new InteractiveArrayAdapter(this, sh.getMasterList());
        //setListAdapter(adapter);
    }


    /*@Override
    public void onBackPressed() {
        adapter.notifyDataSetChanged();
        super.onBackPressed();
    }*/

}
