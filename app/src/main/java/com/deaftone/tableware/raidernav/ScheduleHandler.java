package com.deaftone.tableware.raidernav;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleHandler {
    GsonBuilder builder;
    Gson gson;
    List<ScheduleEntryList> masterList;
    ScheduleFileHandler filehandler;

    ScheduleHandler(Context c) {
        builder = new GsonBuilder();
        gson = builder.create();
        filehandler = new ScheduleFileHandler(c);
        loadMasterListFromFile();
    }

    public void initializeSchedule() {
        //String [] time= {"BOI","SBI","HDFC","PNB","OBC"};
        ScheduleSingleEntry se = new ScheduleSingleEntry("CS3365", "LIVERMORE", "1000", "1050");
        ScheduleSingleEntry se2 = new ScheduleSingleEntry("CS4352", "ENGCTR", "1100", "1150");
        ScheduleSingleEntry se3 = new ScheduleSingleEntry("CS3383", "CIVENG", "1200", "1250");
        ScheduleEntryList sel = new ScheduleEntryList("tuethur-spring18");
        sel.addEntry(se);
        sel.addEntry(se2);
        sel.addEntry(se3);
        masterList = new ArrayList<ScheduleEntryList>();
        masterList.add(sel);
        List<ScheduleEntryList> l = new ArrayList<ScheduleEntryList>();
        l.add(sel);
        System.out.println("initsched: "+gson.toJson(se));
        System.out.println("initsched: "+gson.toJson(sel));
        System.out.println("initsched: "+gson.toJson(l));
        filehandler.writeFile(gson.toJson(l));
    }

    public void generateAnotherRandomSchedule() {
        String [] time= {"BOI","SBI","HDFC","PNB","OBC"};
        ScheduleSingleEntry se = new ScheduleSingleEntry("CS3365", "LIVERMORE", "1000", "1050");
        ScheduleSingleEntry se2 = new ScheduleSingleEntry("CS4352", "ENGCTR", "1100", "1150");
        ScheduleSingleEntry se3 = new ScheduleSingleEntry("CS3383", "CIVENG", "1200", "1250");
        se.setActiveOnDay(2);
        se.setActiveOnDay(4);
        ScheduleEntryList sel = new ScheduleEntryList("tuethur-spring18");
        sel.addEntry(se);
        addScheduleToMasterList(sel);
    }

    private void saveMasterListToFile() {
        filehandler.writeFile(gson.toJson(masterList));
    }

    public void addScheduleToMasterList(ScheduleEntryList sel) {
        masterList.add(sel);
        saveMasterListToFile();
    }

    public void removeScheduleFromMasterList(ScheduleEntryList sel) {
        masterList.remove(sel);
        saveMasterListToFile();
    }

    public void removeScheduleFromMasterList(int i) {
        masterList.remove(i);
        saveMasterListToFile();
    }

    public void replaceScheduleInMasterList(int i, ScheduleEntryList sel) {
        masterList.set(i, sel);
        //masterList.remove(i);
        //masterList.add(sel);
        saveMasterListToFile();
    }

    public ScheduleEntryList getScheduleFromMasterList(int i) {
        return masterList.get(i);
    }

    private void loadMasterListFromFile() {
        if(!filehandler.fileExists()) {
            initializeSchedule();
        }
        String filecontents = filehandler.readFile();
        if(filecontents.equals("[]") || filecontents.charAt(0) != '[') {
            initializeSchedule();
        }
        //System.out.println("loadmaster: File contents: "+filecontents);
        try {
            Type collectionType = new TypeToken<List<ScheduleEntryList>>() {
            }.getType();
            masterList = gson.fromJson(filecontents, collectionType);
            //System.out.println("loadmaster: " + masterList.size() + " schedule loaded.");
            //System.out.println("loadmaster: masterlist: " + masterList.toString());
        } catch (JsonSyntaxException e) {
            initializeSchedule();
        } catch (IllegalStateException e) {
            initializeSchedule();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ScheduleEntryList> getMasterList() {
        return masterList;
    }
}
