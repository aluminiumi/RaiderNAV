package com.deaftone.tableware.raidernav.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.deaftone.tableware.raidernav.InteractiveSELArrayAdapter;
import com.deaftone.tableware.raidernav.R;
import com.deaftone.tableware.raidernav.ScheduleEntryList;
import com.deaftone.tableware.raidernav.ScheduleHandler;
import com.deaftone.tableware.raidernav.ScheduleSingleEntry;

//public class ScheduleEntryListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
public class ScheduleEntryListActivity extends Activity implements AdapterView.OnItemSelectedListener {
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
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        if(tb != null) {
            tb.setTitle(sel.getName());
        }
        System.out.println("SELActivity: onCreate: sel is "+sel);
        System.out.println("SELActivity: onCreate: sel has "+sel.getEntryCount()+" entries.");

        lv = findViewById(R.id.courses_list_view);
        adapter = new InteractiveSELArrayAdapter(this, sel);
        ((InteractiveSELArrayAdapter) adapter).setIndex(index); //so the adapter can pass it on to the next activity
        //adapter.setNotifyOnChange(true);
        lv.setAdapter(adapter);


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


        Button createCourseButton = (Button) findViewById(R.id.createCourseButton);
        createCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), AddCourseActivity.class).putExtra("index", index), 2);

                if (getParent() == null) {
                    setResult(Activity.RESULT_OK, getIntent());
                } else {
                    getParent().setResult(Activity.RESULT_OK, getIntent());
                }
                finish();
            }
        });

        Button renameScheduleButton = (Button) findViewById(R.id.renameScheduleButton);
        renameScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompt.xml view
                LayoutInflater li = LayoutInflater.from(ScheduleEntryListActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        ScheduleEntryListActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                userInput.setText(sel.getName());

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        updateScheduleName(index, userInput.getText().toString(), sel);
                                        if (getParent() == null) {
                                            setResult(Activity.RESULT_OK, getIntent());
                                        } else {
                                            getParent().setResult(Activity.RESULT_OK, getIntent());
                                        }
                                        finish();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                System.out.println("SELActivity: alertDialog created");

                // show it
                alertDialog.show();
            }
        });

        Button deletebutton = (Button) findViewById(R.id.deleteButton);
        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteSchedule(index, sel);
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