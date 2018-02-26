package com.deaftone.tableware.raidernav;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
Note to self: Don't use this class anymore.
 */

public class ScheduleEntryListList extends ArrayList<ScheduleEntryList> {
    public List<ScheduleEntryList> masterScheduleList;

    ScheduleEntryListList() {
        masterScheduleList = new ArrayList<ScheduleEntryList>();
    }

    public void addEntry(ScheduleEntryList sel) {
        masterScheduleList.add(sel);
    }

    //public void add(ScheduleEntryList sel) {
    //    addEntry(sel);
    //}

    public void add(int i, ScheduleEntryList sel) {
        masterScheduleList.add(i, sel);
    }

    public void addAll(int i, ArrayList<ScheduleEntryList> c) {
        masterScheduleList.addAll(i, c);
        //super.addAll(i, c);
    }

    public void removeEntry(ScheduleEntryList sel) {
        masterScheduleList.remove(sel);
    }

    public ScheduleEntryList getEntry(int index) {
        return masterScheduleList.get(index);
    }

    public int getEntryCount() {
        return masterScheduleList.size();
    }

    public ScheduleEntryList get(int index) {
        return getEntry(index);
    }

    public ScheduleEntryList[] toArray() {
        return (ScheduleEntryList[]) masterScheduleList.toArray();
    }

    public String toString() {
        String output = "";
        for(ScheduleEntryList s : masterScheduleList) {
            output += s.toString();
        }
        output += "\n";
        return output;
    }
}
