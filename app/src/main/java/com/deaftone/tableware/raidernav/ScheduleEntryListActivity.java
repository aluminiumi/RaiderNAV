package com.deaftone.tableware.raidernav;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScheduleEntryListActivity extends AppCompatActivity {
    TextView tv;
    TextView dv;
    ScheduleHandler sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_entry_list);
        tv = findViewById(R.id.nameText);
        dv = findViewById(R.id.dataText);
        Bundle extras=getIntent().getExtras();
        final int index=extras.getInt("index");
        sh = new ScheduleHandler(getApplicationContext());
        System.out.println(index);
        final ScheduleEntryList sel = sh.getMasterList().get(index);
        System.out.println(sel);
        tv.setText(sel.getName());
        dv.setText(sel.toString());

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


}
