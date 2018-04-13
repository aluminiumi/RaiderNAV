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
    /*@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }*/

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

    }

    @Test
    public void getBuilding_isCorrect() {
        assertEquals(building, sse.getBuilding());
    }

    @Test
    public void setBuilding_isCorrect() {

    }

    @Test
    public void getStartTime_isCorrect() {
        assertEquals(starttime, sse.getStartTime());
    }

    @Test
    public void setStartTime_isCorrect() {

    }

    @Test
    public void getEndTime_isCorrect() {
        assertEquals(endtime, sse.getEndTime());
    }

    @Test
    public void setEndTime_isCorrect() {

    }

    @Test
    public void getDays_isCorrect() {

    }

    @Test
    public void setDays_isCorrect() {

    }

    @Test
    public void setActiveOnDay_isCorrect() {


    }

    @Test
    public void setInactiveOnDay_isCorrect() {

    }

    @Test
    public void isActiveOnDay_isCorrect() {

    }

}