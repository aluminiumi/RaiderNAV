package com.deaftone.tableware.raidernav;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AddressMapUnitTest {
    /*@Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }*/

    @Before
    public void initialize() {
        AddressMap.initialize();
    }

    @Test
    public void getKeys_isCorrect() {
        //assertEquals("ADMIN", AddressMap.getKeys().iterator().next());
        assertEquals("hop", AddressMap.getKeys().iterator().next());

    }

    @Test
    public void getKeysAsCharSeouence_isCorrect() {

    }

    @Test
    public void getXY_isCorrect() {

    }

    @Test
    public void parseXY_isCorrect() {

    }

    @Test
    public void getLatLng_isCorrect() {

    }

    @Test
    public void getBuildingArray_isCorrect() {

    }

    @Test
    public void fetch_isCorrect() {
        assertEquals("33.587275, -101.875771", AddressMap.fetch("ENGCTR"));
    }

    @Test
    public void fetchCoordinates_isCorrect() {

    }


}