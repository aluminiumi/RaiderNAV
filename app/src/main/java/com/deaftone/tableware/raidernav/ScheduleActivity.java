package com.deaftone.tableware.raidernav;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//public class ScheduleActivity extends AppCompatActivity {
public class ScheduleActivity extends ListActivity {
    ScheduleHandler sh;
    ArrayAdapter<ScheduleEntryList> adapter;
    ListActivity la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schedule);
        setContentView(R.layout.activity_schedule);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //final TextView tv = findViewById(R.id.savedText);
        //fh = new ScheduleFileHandler(getApplicationContext());
        sh = new ScheduleHandler(getApplicationContext());
        //la = new ListActivity();
        adapter = new InteractiveArrayAdapter(this, sh.getMasterList());
        if(sh.getMasterList() == null) {
            System.out.println("scheduleactivity: masterlist null");
        }
        if(adapter == null) {
            System.out.println("scheduleactivity: adapter is null");
        }
        setListAdapter(adapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        Button savebutton = (Button) findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //fh.writeFile(tv.getText().toString());
            }
        });

        Button loadbutton = (Button) findViewById(R.id.loadButton);
        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

        Button deletebutton = (Button) findViewById(R.id.deleteButton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //tv.setText(fh.readFile());
                //tv.setText(fileContents);
            }
        });
    }


}
