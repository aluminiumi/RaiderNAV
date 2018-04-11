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


}