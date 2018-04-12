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

    ScheduleSingleEntry sse;

    @Before
    public void initialize() {
        boolean[] days = {false, true, true, true, true, true, false};
        sse = new ScheduleSingleEntry(
                "RUSN 1502", "Foreign Language", "0900", "0950", days);
    }

    @Test
    public void getCourseNumber_isCorrect() {
        assertEquals("RUSN 1502", sse.getCourseNumber());

    }

    @Test
    public void setCourseNumber_isCorrect() {

    }

    @Test
    public void getBuilding_isCorrect() {

    }

    @Test
    public void setBuilding_isCorrect() {

    }

    @Test
    public void getStartTime_isCorrect() {

    }

    @Test
    public void setStartTime_isCorrect() {

    }

    @Test
    public void getEndTime_isCorrect() {

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