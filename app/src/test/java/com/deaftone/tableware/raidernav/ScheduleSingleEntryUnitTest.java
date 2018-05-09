package com.deaftone.tableware.raidernav;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScheduleSingleEntryUnitTest {

    private ScheduleSingleEntry sse;
    private final String coursenumber = "RUSN 1502";
    private final String building = "Foreign Language";
    private final String starttime = "0900";
    private final String endtime = "0950";
    private boolean[] days = {false, true, true, true, true, true, false};

    @Before
    public void initialize() {

        sse = new ScheduleSingleEntry(
                coursenumber, building, starttime, endtime, days);
    }

    @Test
    public void getCourseNumber_isCorrect() {
        assertEquals(coursenumber, sse.getCourseNumber());

    }

    @Test
    public void setCourseNumber_isCorrect() {
        sse.setCourseNumber("CS 4352");
        assertEquals("CS 4352", sse.getCourseNumber());
    }

    @Test
    public void getBuilding_isCorrect() {
        assertEquals(building, sse.getBuilding());
    }

    @Test
    public void setBuilding_isCorrect() {
        sse.setBuilding("Holden Hall");
        assertEquals("Holden Hall", sse.getBuilding());
    }

    @Test
    public void getStartTime_isCorrect() {
        assertEquals(starttime, sse.getStartTime());
    }

    @Test
    public void setStartTime_isCorrect() {
        sse.setStartTime("0300");
        assertEquals("0300", sse.getStartTime());
    }

    @Test
    public void getEndTime_isCorrect() {
        assertEquals(endtime, sse.getEndTime());
    }

    @Test
    public void setEndTime_isCorrect() {
        sse.setEndTime("0400");
        assertEquals("0400", sse.getEndTime());
    }

    @Test
    public void getDays_isCorrect() {
        assertEquals(false, sse.getDays()[0]);
        assertEquals(true, sse.getDays()[1]);
    }

    @Test
    public void setDays_isCorrect() {
        boolean[] newdays = {true, false, true, true, true, true, false};
        sse.setDays(newdays);
        assertEquals(true, sse.getDays()[0]);
        assertEquals(false, sse.getDays()[1]);
    }

    @Test
    public void setActiveOnDay_isCorrect() {
        sse.setActiveOnDay(0);
        assertEquals(true, sse.getDays()[0]);
        sse.setActiveOnDay(1);
        assertEquals(true, sse.getDays()[1]);
    }

    @Test
    public void setInactiveOnDay_isCorrect() {
        sse.setInactiveOnDay(0);
        assertEquals(false, sse.getDays()[0]);
        sse.setInactiveOnDay(1);
        assertEquals(false, sse.getDays()[1]);
    }

    @Test
    public void isActiveOnDay_isCorrect() {
        sse.setActiveOnDay(0);
        sse.setInactiveOnDay(1);
        assertEquals(true, sse.isActiveOnDay(0));
        assertEquals(false, sse.isActiveOnDay(1));
    }

}