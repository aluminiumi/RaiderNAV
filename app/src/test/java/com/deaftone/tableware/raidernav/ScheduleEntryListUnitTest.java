package com.deaftone.tableware.raidernav;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ScheduleEntryListUnitTest {
    /*@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }*/
    ScheduleEntryList sel;
    private ScheduleSingleEntry sse;
    private final String coursenumber = "RUSN 1502";
    private final String building = "Foreign Language";
    private final String starttime = "0900";
    private final String endtime = "0950";
    private boolean[] days = {false, true, true, true, true, true, false};

    @Before
    public void initialize() {
        sel = new ScheduleEntryList();
        sse= new ScheduleSingleEntry(coursenumber, building, starttime, endtime, days);
    }


    @Test
    public void addEntry_isCorrect() {

        sel.addEntry(sse);
        assertEquals(building,sel.getEntry(0).getBuilding());

    }

    @Test
    public void removeEntry_isCorrect() {

    }

    @Test
    public void getEntry_isCorrect() {
       // assertEquals(0,sel.getEntry(5));
    }

    @Test
    public void getEntryCount_isCorrect() {
        assertEquals(0,sel.getEntryCount());

    }

    @Test
    public void getName_isCorrect() {
        assertEquals(null,sel.getName());
        sel.setName("nomad");
        assertEquals("nomad",sel.getName());

    }

    @Test
    public void setName_isCorrect() {
        sel.setName("House");
        assertEquals("House",sel.getName());

    }

    @Test
    public void enable_isCorrect() {
        sel.enable();
        assertEquals(true,sel.isEnabled());
    }

    @Test
    public void disable_isCorrect() {
        sel.disable();
        assertEquals(false,sel.isEnabled());

    }

    @Test
    public void setEnabled_isCorrect() {
        sel.setEnabled(false);
        assertEquals(false,sel.isEnabled());

    }

    @Test
    public void isEnabled_isCorrect() {

        assertEquals(false,sel.isEnabled());
        sel.setEnabled(true);
        assertEquals(true,sel.isEnabled());

    }

    @Test
    public void toArray_isCorrect() {

    }


}