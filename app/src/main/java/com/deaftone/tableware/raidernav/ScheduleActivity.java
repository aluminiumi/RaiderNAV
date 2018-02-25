package com.deaftone.tableware.raidernav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ScheduleActivity extends AppCompatActivity {
    final String schedulefile = "schedulefile";
    String fileContents = "Initial text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final TextView tv = findViewById(R.id.savedText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button savebutton = (Button) findViewById(R.id.saveButton);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                writeFile(tv.getText().toString());
            }
        });

        Button loadbutton = (Button) findViewById(R.id.loadButton);
        loadbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this,ScheduleActivity.class));
                readFile();
                //tv.setText(fileContents);
            }
        });
    }

    private void createFile() {
        FileOutputStream outputStream;
        TextView tv = (TextView) findViewById(R.id.savedText);

        try {
            outputStream = openFileOutput(schedulefile, Context.MODE_PRIVATE);
            outputStream.write(("Uninitialized file. "+fileContents).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
        }
    }

    private void writeFile(String input) {
        FileOutputStream outputStream;
        TextView tv = (TextView) findViewById(R.id.savedText);

        try {
            outputStream = openFileOutput(schedulefile, Context.MODE_PRIVATE);
            outputStream.write(input.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
        }
    }

    private void readFile() {
        TextView tv = (TextView) findViewById(R.id.savedText);
        File file = getBaseContext().getFileStreamPath(schedulefile);
        if(!file.exists()) {
            createFile();
        }
        try {
            FileInputStream fis = openFileInput(schedulefile);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n = fis.read(buffer);
            while(n != -1) {
                fileContent.append(new String(buffer, 0, n));
                n = fis.read(buffer);
            }
            tv.setText(fileContent.toString());
            fis.close();
            fileContents = fileContent.toString();
        } catch (Exception e) {
            e.printStackTrace();
            tv.setText(e.toString());
        }
    }
}
