package com.deaftone.tableware.raidernav;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreateScheduleScreen extends AppCompatActivity {
    private Button addClassButton;
    private Button finishCreatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_schedule_screen);

        addClassButton =(Button) findViewById(R.id.addAnotherClass);
        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement
                Snackbar.make(view, "Not yet implemented!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        finishCreatingButton =(Button) findViewById(R.id.finishCreatingSchedule);
        finishCreatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: implement
                Snackbar.make(view, "Not yet implemented!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
