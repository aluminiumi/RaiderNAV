package com.deaftone.tableware.raidernav.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.deaftone.tableware.raidernav.R;
import com.deaftone.tableware.raidernav.ScheduleEntryList;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //the button for single destinations
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompt.xml view
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompt_spinner, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final TextView tv = (TextView) promptsView.findViewById(R.id.textView1);
                tv.setText("Where would you like to go?");

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                //userInput.setText();
                //userInput.setHint("Schedule Name (e.g., \"Spring 2018\")");

                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        if (getParent() == null) {
                                            startActivity(new Intent(MainActivity.this,MapsActivity.class));
                                            //setResult(Activity.RESULT_OK, getIntent());
                                        } else {
                                            //getParent().setResult(Activity.RESULT_OK, getIntent());
                                        }
                                        //finish();
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

                System.out.println("MainActivity: alertDialog created");

                // show it
                alertDialog.show();
            }
        });

        //the button to view scheduled maps
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class));
            }
        });

        //the settings button
        FloatingActionButton fab3 = (FloatingActionButton) findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));
            }
        });

        //the view schedule button
        Button vsb = (Button) findViewById(R.id.viewschedulesbutton);
        vsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,ScheduleActivity.class), 1);
            }
        });

        //the today's classes button
        Button todaysClassesButton = (Button) findViewById(R.id.todaysclassesbutton);
        todaysClassesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented yet!", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
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
