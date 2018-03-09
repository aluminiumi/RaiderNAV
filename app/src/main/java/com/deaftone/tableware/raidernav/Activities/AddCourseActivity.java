package com.deaftone.tableware.raidernav.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import com.deaftone.tableware.raidernav.R;
import com.deaftone.tableware.raidernav.ScheduleEntryList;
import com.deaftone.tableware.raidernav.ScheduleHandler;
import com.deaftone.tableware.raidernav.ScheduleSingleEntry;

public class AddCourseActivity extends AppCompatActivity {
    private Button addClassButton;
    private Button finishCreatingButton;
    TextView tv;
    TextView dv;
    TextView st;
    TextView et;
    CheckBox monday;
    CheckBox tuesday;
    CheckBox wednesday;
    CheckBox thursday;
    CheckBox friday;
    CheckBox saturday;
    CheckBox sunday;
    ScheduleHandler sh;
    ScheduleEntryList sel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        tv = findViewById(R.id.courseName);
        dv = findViewById(R.id.buildingName);
        st = findViewById(R.id.startTime);
        et = findViewById(R.id.endTime);
        monday = findViewById(R.id.checkBox_monday);
        tuesday = findViewById(R.id.checkBox_tuesday);
        wednesday = findViewById(R.id.checkBox_wednesday);
        thursday = findViewById(R.id.checkBox_thursday);
        friday = findViewById(R.id.checkBox_friday);
        saturday = findViewById(R.id.checkBox_saturday);
        sunday = findViewById(R.id.checkBox_sunday);
        Bundle extras = getIntent().getExtras();
        final int index = extras.getInt("index");
        //final int entryindex = extras.getInt("entryindex");
        sh = new ScheduleHandler(getApplicationContext());
        sel = sh.getMasterList().get(index);


        /*addClassButton =(Button) findViewById(R.id.addAnotherClass);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not yet implemented!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        finishCreatingButton =(Button) findViewById(R.id.finishCreatingSchedule);
        finishCreatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean newdays[] = new boolean[7];
                if(sunday.isChecked()) newdays[0] = true;
                if(monday.isChecked()) newdays[1] = true;
                if(tuesday.isChecked()) newdays[2] = true;
                if(wednesday.isChecked()) newdays[3] = true;
                if(thursday.isChecked()) newdays[4] = true;
                if(friday.isChecked()) newdays[5] = true;
                if(saturday.isChecked()) newdays[6] = true;
                ScheduleSingleEntry newsse = new ScheduleSingleEntry(
                        tv.getText().toString(),
                        dv.getText().toString(),
                        st.getText().toString(),
                        et.getText().toString(),
                        newdays
                );
                addEntry(index, newsse);

                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
                /*Snackbar.make(view, "Not yet implemented!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void addEntry(int index, ScheduleSingleEntry newsse) {
        sel.addEntry(newsse);
        sh.replaceScheduleInMasterList(index, sel);
    }

    /*private void replaceEntry(int index, int entryindex, ScheduleSingleEntry newsse) {
        sel.replaceEntry(entryindex, newsse);
        sh.replaceScheduleInMasterList(index, sel);
    }*/
}
