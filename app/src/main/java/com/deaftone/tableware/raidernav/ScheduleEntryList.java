package com.deaftone.tableware.raidernav;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleEntryList {
    public boolean enabled;
    public String name;
    List<ScheduleSingleEntry> classEntries;

    ScheduleEntryList(String n) {
        name = n;
        enabled = false;
        classEntries = new ArrayList<ScheduleSingleEntry>();
    }

    public void addEntry(ScheduleSingleEntry sse) {
        classEntries.add(sse);
    }

    public void removeEntry(ScheduleSingleEntry sse) {
        classEntries.remove(sse);
    }

    public ScheduleSingleEntry getEntry(int index) {
        return classEntries.get(index);
    }

    public int getEntryCount() {
        return classEntries.size();
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }
}
