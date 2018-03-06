package com.deaftone.tableware.raidernav;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateScheduleScreen extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button finishCreatingSchedule = (Button) findViewById(R.id.finishCreatingSchedule);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule_screen);

        button =(Button) findViewById(R.id.addAnotherClass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement
                System.out.println("Do stuff");
            }
        });
       /* finishCreatingSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   EditText scheduleName= (EditText) findViewById(R.id.scheduleName);
              //  String sN= scheduleName.getText().toString();
                EditText courseName= (EditText) findViewById(R.id.courseName);
                EditText buildingName= (EditText) findViewById(R.id.buildingName);
                EditText startTime= (EditText) findViewById(R.id.startTime);
                EditText endTime= (EditText) findViewById(R.id.endTime);
              //  courseName.setText(sN);

            }
        });*/
    }
}
