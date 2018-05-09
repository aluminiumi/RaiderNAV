package com.deaftone.tableware.raidernav.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import com.deaftone.tableware.raidernav.AddressMap;

import com.deaftone.tableware.raidernav.OffCampusFood;
import com.deaftone.tableware.raidernav.OnCampusFood;
import com.deaftone.tableware.raidernav.R;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private String destination;
    private final int CAMPUSBUILDING = 1;
    private final int ONCAMPUSEATING = 2;
    private final int OFFCAMPUSEATING = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        //print privacy policy to screen
        if (firstStart) {
            showStartDialog();
        }
      
      
        //Request permissions on startup to prevent problems on first map load
        final int LOCATION_REQUEST = 500;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST);
        }
        else if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AddressMap.initialize();
        OffCampusFood.initialize();
        OnCampusFood.initialize();

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
                alertDialogBuilder.setView(promptsView);

                final TextView tv = (TextView) promptsView.findViewById(R.id.textView1);
                tv.setText("Where would you like to go?");

                final Spinner destSpinner = (Spinner) promptsView
                        .findViewById(R.id.destination_spinner);

                // Create an ArrayAdapter using the array and a default spinner layout
                CharSequence[] destinations = AddressMap.getKeysAsCharSequence();
                ArrayAdapter<CharSequence> adapter =
                        new ArrayAdapter<CharSequence>
                                (getApplicationContext(), R.layout.spinner_contents_layout, destinations);
                //(getApplicationContext(), android.R.layout.simple_spinner_item, destinations);

                // Specify the layout to use when the list of choices appears
                //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(R.layout.spinner_contents_layout);


                // Apply the adapter to the spinner
                destSpinner.setAdapter(adapter);

                //set spinner behavior for selections
                destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        // An item was selected. You can retrieve the selected item using
                        // parent.getItemAtPosition(pos)
                        //System.out.println(pos);
                        //System.out.println(parent.getItemAtPosition(pos));
                        setDestination((String) parent.getItemAtPosition(pos));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Another interface callback
                        setDestination((String) parent.getItemAtPosition(0));
                    }
                });

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
                                            startActivity(new Intent(MainActivity.this,MapsActivity.class)
                                                    .putExtra("isLoneDestination", true)
                                                    .putExtra("destinationName", destination)
                                                    .putExtra("destinationType", CAMPUSBUILDING));
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
                //alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        FloatingActionButton onCampusFood = (FloatingActionButton) findViewById(R.id.OnCampusButton);
        onCampusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompt.xml view
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompt_spinner, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialogBuilder.setView(promptsView);

                final TextView tv = (TextView) promptsView.findViewById(R.id.textView1);
                tv.setText("Where would you like to eat on campus?");

                final Spinner destSpinner = (Spinner) promptsView
                        .findViewById(R.id.destination_spinner);

                // Create an Array Adapter using the array and a default spinner layout
                CharSequence[] destinations = OnCampusFood.getKeysAsCharSequence();
                ArrayAdapter<CharSequence> adapter =
                        new ArrayAdapter<CharSequence>
                                (getApplicationContext(), R.layout.spinner_contents_layout, destinations);
                //(getApplicationContext(), android.R.layour.simple_spinner_item, destinations;

                //Specify the layout to use when the list of choices appears
                //adapter.setDropDownViewResource(android.R.layour.simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(R.layout.spinner_contents_layout);

                //apply the adapter to the spinner
                destSpinner.setAdapter(adapter);

                //set spinner behavior for selections
                destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        // An item was selected. You can retrieve the selected item using
                        // parent.getItemAtPosition(pos)
                        //System.out.println(pos);
                        //System.out.println(parent.getItemAtPosition(pos));
                        setDestination((String) parent.getItemAtPosition(pos));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // another interface callback
                        setDestination((String) parent.getItemAtPosition(0));
                    }

                });

                //userInput.setText();
                //userInput.setHint("Schedule Name (e.g., \"Spring 2018\")");

                //set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        if (getParent() == null) {
                                            startActivity(new Intent(MainActivity.this, MapsActivity.class)
                                                    .putExtra("isLoneDestination", true)
                                                    .putExtra("destinationName", destination)
                                                    .putExtra("destinationType", ONCAMPUSEATING));
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
                // creat alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                System.out.println("MainActivity: alertDialog created");

                //show it
                alertDialog.show();
                //alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        FloatingActionButton offCampusFood = (FloatingActionButton) findViewById(R.id.OffCampusButton);
        offCampusFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get prompt.xml view
                LayoutInflater li = LayoutInflater.from(MainActivity.this);
                View promptsView = li.inflate(R.layout.prompt_spinner, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        MainActivity.this);
                alertDialogBuilder.setView(promptsView);

                final TextView tv = (TextView) promptsView.findViewById(R.id.textView1);
                tv.setText("Where would you like to eat off campus?");

                final Spinner destSpinner = (Spinner) promptsView
                        .findViewById(R.id.destination_spinner);

                // Create an Array Adapter using the array and a default spinner layout
                CharSequence[] destinations = OffCampusFood.getKeysAsCharSequence();
                ArrayAdapter<CharSequence> adapter =
                        new ArrayAdapter<CharSequence>
                                (getApplicationContext(), R.layout.spinner_contents_layout, destinations);
                //(getApplicationContext(), android.R.layour.simple_spinner_item, destinations;

                //Specify the layout to use when the list of choices appears
                //adapter.setDropDownViewResource(android.R.layour.simple_spinner_dropdown_item);
                adapter.setDropDownViewResource(R.layout.spinner_contents_layout);

                //apply the adapter to the spinner
                destSpinner.setAdapter(adapter);

                //set spinner behavior for selections
                destSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                        // An item was selected. You can retrieve the selected item using
                        // parent.getItemAtPosition(pos)
                        //System.out.println(pos);
                        //System.out.println(parent.getItemAtPosition(pos));
                        setDestination((String) parent.getItemAtPosition(pos));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // another interface callback
                        setDestination((String) parent.getItemAtPosition(0));
                    }

                });

                //userInput.setText();
                //userInput.setHint("Schedule Name (e.g., \"Spring 2018\")");

                //set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        if (getParent() == null) {
                                            startActivity(new Intent(MainActivity.this, MapsActivity.class)
                                                    .putExtra("isLoneDestination", true)
                                                    .putExtra("destinationName", destination)
                                                    .putExtra("destinationType", OFFCAMPUSEATING));
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
                // creat alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                System.out.println("MainActivity: alertDialog created");

                //show it
                alertDialog.show();
                //alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                //alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });






        //the button to view scheduled maps
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,MapsActivity.class)
                        .putExtra("isLoneDestination", false));
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
    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Privacy and Permissions")
                .setMessage("\t\n" +
                        "This dialog serves to notify you of our data collection practices and explain the permissions required by this app.\n\n" +
                        "This app requests the following permissions: Identification, Internet, Location, Phone, Shell, Storage, Contacts, Email.\n\n\n" +

                        "The Internet permission is used by this app to download navigation directions to your device.\n\n\n" +

                        "The Location permission is used by this app to determine your starting location to generate accurate directions to destinations.\n\n" +
                        "This permission involves both coarse (network) location and fine (GPS) location. The most accurate available location source is used.\n\n\n" +

                        "If you will not be using the navigation features, you do not need to allow the Location or Internet permissions.\n\n\n" +

                        "The storage permission is used to save and restore schedules locally on your device.\n\n" +
                        "If you will not be using the schedules feature, you do not need to allow the storage permission.\n\n" +

                        "The following permissions are not requested in the code of this app and appear to be permissions required by underlying libraries used by this app (Google Maps and Google Directions):\n\n\n" +

                        "1. Identification. Access is requested for /proc/meminfo and SERIAL. The purpose of this permission is unknown. It is presumed safe to disallow this permission.\n\n" +
                        "2. Phone. Access is requested for Configuration.MCC, Configuration.MNC, and getSimCountryIso. The purpose of this permission is unknown. It is presumed safe to disallow this permission.\n\n" +
                        "3. Shell. Access is requested for exec. This is used by Google Maps to access cached maps. If you will not be using navigation features, you do not need to allow this permission.\n\n" +
                        "4. Contacts. Access is requested for both READ_CONTACTS and WRITE_CONTACTS. The purpose of this permission is unknown, though appears to associated with keyboard entry fields and may be related to text-completion. It is presumed safe to disallow this permission.\n\n" +
                        "5. Email. Access is requested to determine your GMail provider. This appears to be a consequence of Android's handling of custom URIs (such as \"content://com.google...\"). It may be necessary to enable this permission if you intend to generate navigation directions.\n\n\n" +

                        "For more information about the above five permissions and how they are used, it is advisable to refer to the privacy policies for the Google Maps API and Google Directions.\n\n" +

                        "\n" +
                        "\n" +
                        "\n")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        //SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("prefs", MODE_PRIVATE).edit();
        editor.putBoolean("firstStart", false).apply();
        //editor.apply();
    }




    /*public void openCreateScheduleScreen(){
        Intent intent = new Intent(this,AddCourseActivity.class);
        startActivity(intent);
    }*/

    private void setDestination(String dest) {
        destination = dest;
    }

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
