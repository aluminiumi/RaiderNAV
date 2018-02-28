package com.deaftone.tableware.raidernav;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleFileHandler {
    private final String schedulefile = "schedulefile";
    private String fileContents = "Initial text";
    private Context ctx;

    ScheduleFileHandler(Context c) {
        ctx = c;
    }

    /*private void createFile() {
        FileOutputStream outputStream;
        try {
            outputStream = ctx.openFileOutput(schedulefile, Context.MODE_PRIVATE);
            outputStream.write(" ".getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void writeFile(String input) {
        FileOutputStream outputStream;

        try {
            outputStream = ctx.openFileOutput(schedulefile, Context.MODE_PRIVATE);
            System.out.println("filehandler: Writing to file: "+input);
            outputStream.write(input.getBytes());
            //outputStream.write(gson.toJson(sell).getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFile() {
        //File file = ctx.getFileStreamPath(schedulefile);
        try {
            FileInputStream fis = ctx.openFileInput(schedulefile);
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n = fis.read(buffer);
            while(n != -1) {
                fileContent.append(new String(buffer, 0, n));
                n = fis.read(buffer);
            }
            fis.close();
            fileContents = fileContent.toString();
            System.out.println("filehandler: read from file: "+fileContents);
            return fileContents;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean fileExists() {
        return ctx.getFileStreamPath(schedulefile).exists();
    }
}
