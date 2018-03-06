package com.deaftone.tableware.raidernav;

import java.sql.Array;
import java.sql.Time;
import java.util.Arrays;

/**
 * Created by via on 2/25/18.
 */

public class ScheduleSingleEntry {
    private String courseNumber;
    private String building;
    private String [] TimeDrop;
    private String startTime;
    private String endTime;
    private boolean activeOnDay[];

    ScheduleSingleEntry(String cn, String b, String st, String et) {
        courseNumber = cn;
        building = b;
        //System.out.println("Initializing ScheduleSingleEntry");
        /*System.out.println(Arrays.toString(time));
        TimeDrop=new String[5];
        for (int x=0;x<time.length; x++){
            TimeDrop[x]=time[x];
        }
        System.out.println(Arrays.toString(TimeDrop));*/
        //System.out.println("Finished Initilizing!!!");
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

    public void setBuilding(String b) {
        building = b;
    }

    public String getBuilding() {
        return building;
    }

    public String [] getTimeDrop() {
        System.out.println("INSIDE GET TIMEDROP");
        System.out.println(Arrays.toString(TimeDrop));
        return TimeDrop;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String et) {
        endTime = et;
    }

    public String getEndTime() {
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
