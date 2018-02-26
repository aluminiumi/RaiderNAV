package com.deaftone.tableware.raidernav;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleHandler {
    GsonBuilder builder;
    Gson gson;
    //ScheduleEntryListList masterList;
    List<ScheduleEntryList> masterList;
    ScheduleFileHandler filehandler;

    ScheduleHandler(Context c) {
        builder = new GsonBuilder();
        gson = builder.create();
        filehandler = new ScheduleFileHandler(c);
        loadMasterListFromFile();
    }

    public void initializeSchedule(boolean blind) {
        ScheduleSingleEntry se = new ScheduleSingleEntry("CS3365", "LIVERMORE", 930, 1050);
        ScheduleEntryList sel = new ScheduleEntryList("tuethur-spring18");
        sel.addEntry(se);
        //masterList = new ScheduleEntryListList();
        masterList = new ArrayList<ScheduleEntryList>();
        masterList.add(sel);
        List<ScheduleEntryList> l = new ArrayList<ScheduleEntryList>();
        l.add(sel);
        System.out.println("initsched: "+gson.toJson(se));
        System.out.println("initsched: "+gson.toJson(sel));
        System.out.println("initsched: "+gson.toJson(l));
        filehandler.writeFile(gson.toJson(l));
        if(blind)
            masterList = null;
    }

    public void generateAnotherRandomSchedule() {
        ScheduleSingleEntry se = new ScheduleSingleEntry("CS3365", "LIVERMORE", 930, 1050);
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

    public void reloadMaster() {
        loadMasterListFromFile();
    }

    private void loadMasterListFromFile() {
        if(!filehandler.fileExists()) {
            System.out.println("loadmaster: Schedule file nonexistent. Initializing.");
            initializeSchedule(false);
        }
        String filecontents = filehandler.readFile();
        if(filecontents.equals("[]")) {
            System.out.println("loadmaster: invalid contents found. Initializing.");
            initializeSchedule(true);
            filecontents = filehandler.readFile();
        }
        if(filecontents.charAt(0) != '[') {
            System.out.println("loadmaster: invalid contents found. Initializing.");
            initializeSchedule(true);
            filecontents = filehandler.readFile();
        }
        System.out.println("loadmaster: File contents: "+filecontents);
        try {
            //Type collectionType = new TypeToken<Collection<ScheduleEntryListList>>(){}.getType();
            //Collection<ScheduleEntryListList> enums = gson.fromJson(filecontents, collectionType);
            Type collectionType = new TypeToken<List<ScheduleEntryList>>() {
            }.getType();
            //System.out.println("loadmaster: JSON contents: "+gson.fromJson(filecontents, collectionType));
            masterList = gson.fromJson(filecontents, collectionType);
            System.out.println("loadmaster: " + masterList.size() + " schedule loaded.");
            System.out.println("loadmaster: masterlist: " + masterList.toString());
            //for(ScheduleEntryList s : masterList) {
            //    System.out.println(s.toString());
            //}
            //System.out.println(e.toString());
            //masterList = (ScheduleEntryListList) e;
            //masterList = gson.fromJson(filecontents, collectionType);
            //masterList = gson.fromJson(filecontents, ScheduleEntryListList.class);
        } catch (IllegalStateException e) {
            initializeSchedule(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<ScheduleEntryList> getMasterList() {
        return masterList;
    }
}
