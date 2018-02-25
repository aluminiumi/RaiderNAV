package com.deaftone.tableware.raidernav;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleSingleEntry {
    public String courseNumber;
    public String building;
    int startTime;
    int endTime;
    boolean activeOnDay[];

    ScheduleSingleEntry(String cn, String b, int st, int et) {
        courseNumber = cn;
        building = b;
        startTime = st;
        endTime = et;
        activeOnDay = new boolean[7];
    }

    public void setActiveOnDay(int day) {
        activeOnDay[day] = true;
    }

    public void setInactiveOnDay(int day) {
        activeOnDay[day] = false;
    }
}
