package com.deaftone.tableware.raidernav;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleEntryListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView tv;
    TextView dv;
    Spinner ST;
    ScheduleHandler sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_entry_list);
        tv = findViewById(R.id.nameText);
        dv = findViewById(R.id.dataText);
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        sh = new ScheduleHandler(getApplicationContext());
        System.out.println(index);
        final ScheduleEntryList sel = sh.getMasterList().get(index);
        System.out.println(sel);
        tv.setText(sel.getName());
        dv.setText(sel.getEntry(index).getBuilding());

        //  dv.setText(sel.toString());
        //TODO: Remove the following->>>

       // sh.initializeSchedule();

        Spinner ST = (Spinner) findViewById(R.id.spinnerStartTime);
        ST.setOnItemSelectedListener(this);
        System.out.println(sel.getEntry(index));
        System.out.println(sel.getEntry(index).getTimeDrop()[0]);

        ArrayAdapter aa = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sel.getEntry(index).getTimeDrop());
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        ST.setAdapter(aa);


        Button savebutton = (Button) findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                //fh.writeFile(tv.getText().toString());
                //sel.setName(tv.getText());
                updateScheduleName(index, tv.getText().toString(), sel);
                //sh.replaceScheduleInMasterList(index, sel);
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
        Toast.makeText(getApplicationContext(), sel.getEntry(index).getTimeDrop()[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
// TODO Auto-generated method stub

    }
}