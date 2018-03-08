package com.deaftone.tableware.raidernav;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleEntryList {
    private boolean enabled;
    private String name;
    private List<ScheduleSingleEntry> classEntries;
    //private List<ScheduleEntryList> masterList;
    //private ScheduleHandler mySH;

    public ScheduleEntryList() {
        classEntries = new ArrayList<ScheduleSingleEntry>();
    }

    public ScheduleEntryList(String n) {
        name = n;
        enabled = false;
        classEntries = new ArrayList<ScheduleSingleEntry>();
    }

    /*public void setScheduleHandler(ScheduleHandler sh) {
        mySH = sh;
    }

    public ScheduleHandler getScheduleHandler() {
        return mySH;
    }*/

    public void addEntry(ScheduleSingleEntry sse) {
        classEntries.add(sse);
    }

    public void removeEntry(ScheduleSingleEntry sse) {
        classEntries.remove(sse);
    }

    /*public void replaceEntry(ScheduleSingleEntry sse, ScheduleEntryList sse) {
        classEntries.set
    }*/

    public void replaceEntry(int index, ScheduleSingleEntry sse) {
        classEntries.set(index, sse);
    }

    public ScheduleSingleEntry getEntry(int index) {
        return classEntries.get(index);
    }

    public int getEntryCount() {
        return classEntries.size();
    }

    public String getName() {
        return name;
    }

    public void setName(String n) { name = n; }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void setEnabled(boolean e) {
        enabled = e;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /*public void setMasterList(List<ScheduleEntryList> list) {
        masterList = list;
    }

    public List<ScheduleEntryList> getMasterList() {
        return masterList;
    }*/

    public ScheduleSingleEntry[] toArray() {
        return (ScheduleSingleEntry[]) classEntries.toArray();
    }

    public List<ScheduleSingleEntry> toArrayList() {
        return classEntries;
    }

    public String toString() {
        String output = "";
        int x;
        for(x=0; x < classEntries.size()-1; x++) {
            output += classEntries.get(x).toString()+"\n";
        }
        if(classEntries.size() > 1)
            output+=classEntries.get(x).toString();
        return output;
    }
}
