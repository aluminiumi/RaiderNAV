package com.deaftone.tableware.raidernav.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.deaftone.tableware.raidernav.R;
import com.deaftone.tableware.raidernav.ScheduleEntryList;
import com.deaftone.tableware.raidernav.ScheduleHandler;
import com.deaftone.tableware.raidernav.ScheduleSingleEntry;

public class ScheduleSingleEntryActivity extends AppCompatActivity {

    TextView tv;
    TextView dv;
    TextView st;
    TextView et;
    Spinner ST;
    ScheduleHandler sh;
    ScheduleEntryList sel;


    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_single_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_single_entry);
        tv = findViewById(R.id.courseName);
        dv = findViewById(R.id.buildingName);
        st = findViewById(R.id.startTime);
        et = findViewById(R.id.endTime);
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        final int entryindex = extras.getInt("entryindex");
        sh = new ScheduleHandler(getApplicationContext());
        System.out.println("SSEActivity: onCreate: index is " + index);
        System.out.println("SSEActivity: onCreate: entryindex is " + entryindex);
        sel = sh.getMasterList().get(index);
        final ScheduleSingleEntry sse = sel.getEntry(entryindex);

        System.out.println("SSEActivity: onCreate: sel is " + sel);
        System.out.println("SSEActivity: onCreate: sel has " + sel.getEntryCount() + " entries.");
        //getSupportActionBar().setTitle(sel.getName());

        tv.setText(sse.getCourseNumber());
        dv.setText(sse.getBuilding());
        st.setText(sse.getStartTime());
        et.setText(sse.getEndTime());

        //Spinner ST = (Spinner) findViewById(R.id.spinnerStartTime);
        //ST.setOnItemSelectedListener(this);
        //System.out.println(sel.getEntry(index));
        //System.out.println(sel.getEntry(index).getTimeDrop()[0]);

        //ArrayAdapter aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sel.getEntry(index).getTimeDrop()); //need a different index for getEntry
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        //ST.setAdapter(aa);


        Button savebutton = (Button) findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScheduleSingleEntry newsse = new ScheduleSingleEntry(
                        tv.getText().toString(),
                        dv.getText().toString(),
                        st.getText().toString(),
                        et.getText().toString());
                replaceEntry(index, entryindex, newsse);

                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
            }
        });

        Button deletebutton = (Button) findViewById(R.id.deleteButton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                deleteCourse(index, entryindex);
                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //tv.setText(fh.readFile());
                //tv.setText(fileContents);
            }
        });
    }

    private void deleteCourse(int index, int entryindex) {
        sel.removeEntry(sel.getEntry(entryindex));
        sh.replaceScheduleInMasterList(index, sel);
    }

    private void replaceEntry(int index, int entryindex, ScheduleSingleEntry newsse) {
        sel.replaceEntry(entryindex, newsse);
        sh.replaceScheduleInMasterList(index, sel);
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        final int entryindex = extras.getInt("entryindex");
        //System.out.println("Inside ONITEMSELECTED");
        //System.out.println(position);
        final ScheduleEntryList sel = sh.getMasterList().get(index);
        Toast.makeText(getApplicationContext(), sel.getEntry(entryindex).getTimeDrop()[position], Toast.LENGTH_LONG).show();
    }

}
