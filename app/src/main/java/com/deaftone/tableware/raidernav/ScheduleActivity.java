package com.deaftone.tableware.raidernav;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

//public class ScheduleActivity extends AppCompatActivity {
public class ScheduleActivity extends ListActivity {
    ScheduleHandler sh;
    ArrayAdapter<ScheduleEntryList> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        tb.setTitle("My Schedules");


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //final TextView tv = findViewById(R.id.savedText);
        //fh = new ScheduleFileHandler(getApplicationContext());
        sh = new ScheduleHandler(getApplicationContext());
        adapter = new InteractiveMasterListArrayAdapter(this, sh.getMasterList());
        adapter.setNotifyOnChange(true);
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

        /*Button loadbutton = (Button) findViewById(R.id.loadButton);
        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                adapter.notifyDataSetChanged();
                getListView().invalidateViews();
                getListView().refreshDrawableState();
                //((InteractiveMasterListArrayAdapter) adapter).list = sh.getMasterList();
                //adapter.notifyDataSetChanged();

                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //tv.setText(fh.readFile());
                //tv.setText(fileContents);
            }
        });*/

        Button createbutton = (Button) findViewById(R.id.createButton);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh.generateAnotherRandomSchedule();
                //adapter.insert(sh.getScheduleFromMasterList(sh.getMasterList().size()-1), 0);
                adapter.notifyDataSetChanged();
                /*Snackbar.make(view, "New schedule created", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();*/
                adapter.notifyDataSetInvalidated();
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();

            }
        });

        Button resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sh.initializeSchedule();
                adapter.notifyDataSetChanged();
                //finishActivity(1);
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
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
        //System.out.println("ScheduleActivity: onActivityResult: Got activity result");
        System.out.println("ScheduleActivity: onActivityResult: requestCode: "+requestCode+", resultCode: "+resultCode);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
            }
        }

        //((InteractiveMasterListArrayAdapter) adapter).list = sh.getMasterList();
        adapter.notifyDataSetChanged();
        //finish(); //go back to main activity; can't seem to figure out why list view won't refresh
        //adapter = new InteractiveMasterListArrayAdapter(this, sh.getMasterList());
        //setListAdapter(adapter);
    }


    /*@Override
    public void onBackPressed() {
        adapter.notifyDataSetChanged();
        super.onBackPressed();
    }*/

}
