package com.deaftone.tableware.raidernav;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class ScheduleEntryListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tv;
    TextView dv;
    Spinner ST;
    ScheduleHandler sh;
    ArrayAdapter<ScheduleSingleEntry> adapter;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_entry_list);
        tv = findViewById(R.id.courseText);
        dv = findViewById(R.id.buildingText);
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        sh = new ScheduleHandler(getApplicationContext());
        System.out.println("SELActivity: onCreate: index is "+index);
        final ScheduleEntryList sel = sh.getMasterList().get(index);
        getSupportActionBar().setTitle(sel.getName());
        System.out.println("SELActivity: onCreate: sel is "+sel);
        System.out.println("SELActivity: onCreate: sel has "+sel.getEntryCount()+" entries.");

        lv = findViewById(R.id.courses_list_view);
        adapter = new InteractiveSELArrayAdapter(this, sel);
        ((InteractiveSELArrayAdapter) adapter).setIndex(index); //so the adapter can pass it on to the next activity
        //adapter.setNotifyOnChange(true);
        lv.setAdapter(adapter);


        //TODO: figure out vanishing entry problem
        //tv.setText(sel.getEntry(0).getCourseNumber());
        //dv.setText(sel.getEntry(0).getBuilding());

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

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //fh.writeFile(tv.getText().toString());
                //sel.setName(tv.getText());
                //updateScheduleName(index, tv.getText().toString(), sel);
                //sh.replaceScheduleInMasterList(index, sel);
                System.out.println("Save: "+adapter.getItem(0));

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
                deleteSchedule(index, sel);
                finish();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //tv.setText(fh.readFile());
                //tv.setText(fileContents);
            }
        });
    }

    private void deleteSchedule(int index, ScheduleEntryList sel) {
        sh.removeScheduleFromMasterList(index);
    }

    private void updateScheduleName(int index, String name, ScheduleEntryList sel) {
        sel.setName(name);
        sh.replaceScheduleInMasterList(index, sel);
        //sh.reloadMaster();
    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        //System.out.println("Inside ONITEMSELECTED");
        //System.out.println(position);
        final ScheduleEntryList sel = sh.getMasterList().get(index);
        //Toast.makeText(getApplicationContext(), sel.getEntry(index).getTimeDrop()[position], Toast.LENGTH_LONG).show(); //invalid! can't use index from masterlist on getEntry
    }

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        //System.out.println("ScheduleActivity: onActivityResult: Got activity result");
        System.out.println("SELActivity: onActivityResult: requestCode: "+requestCode+", resultCode: "+resultCode);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //startActivityForResult(new Intent(getIntent(), ScheduleSingleEntryActivity.class), 1);
                /*if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }*/

                finish();
            }
        }

        //((InteractiveMasterListArrayAdapter) adapter).list = sh.getMasterList();
        adapter.notifyDataSetChanged();
        //finish(); //go back to main activity; can't seem to figure out why list view won't refresh
        //adapter = new InteractiveMasterListArrayAdapter(this, sh.getMasterList());
        //setListAdapter(adapter);
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}