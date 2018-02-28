package com.deaftone.tableware.raidernav;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleSingleEntry {
    private String courseNumber;
    private String building;
    private int startTime;
    private int endTime;
    private boolean activeOnDay[];

    ScheduleSingleEntry(String cn, String b, int st, int et) {
        courseNumber = cn;
        building = b;
        startTime = st;
        endTime = et;
        activeOnDay = new boolean[7];
    }

    public void setCourseNumber(String cn) {
        courseNumber = cn;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String setBuilding(String b) {
        building = b;
    }

    public String getBuilding() {
        return building;
    }

    public void setStartTime(int st) {
        startTime = st;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setEndTime(int et) {
        endTime = et;
    }

    public int getEndTime() {
        return endTime;
    }

    public boolean[] getDays() {
        return activeOnDay;
    }

    public void setDays(boolean[] days) {
        for (boolean b : activeOnDay = days) {

        }

    }

    public void setActiveOnDay(int day) {
        activeOnDay[day] = true;
    }

    public void setInactiveOnDay(int day) {
        activeOnDay[day] = false;
    }

    public boolean isActiveOnDay(int day) { return activeOnDay[day]; }

    public String toString() {
        String output = "";
        output += courseNumber+" "+building+" "+startTime+" "+endTime;
        for(int x=0; x<7; x++) {
            output += " "+activeOnDay[x];
        }
        //output += "";
        return output;
    }
}
