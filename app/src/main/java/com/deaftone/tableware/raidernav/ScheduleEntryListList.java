package com.deaftone.tableware.raidernav;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleEntryListList {
    List<ScheduleEntryList> masterScheduleList;

    ScheduleEntryListList() {
        masterScheduleList = new ArrayList<ScheduleEntryList>();
    }

    public void addEntry(ScheduleEntryList sel) {
        masterScheduleList.add(sel);
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
}
