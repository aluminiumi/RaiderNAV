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

    @Before
    public void initialize() {
        AddressMap.initialize();
    }

    @Test
    public void getKeys_isCorrect() {
        assertEquals("ADMIN", AddressMap.getKeys().iterator().next());

    }

    @Test
    public void getKeysAsCharSeouence_isCorrect() {
        assertEquals("AGRI",AddressMap.getKeysAsCharSequence()[4]);

    }

    @Test
    public void getXY_isCorrect() {
        assertEquals(-101.874702,AddressMap.getXY("ADMIN")[1],.1);

    }

    @Test
    public void parseXY_isCorrect() {
        assertEquals(33.583427,AddressMap.parseXY("33.583427, -101.874702")[0],.1);

    }

    @Test
    public void getLatLng_isCorrect() {
        assertEquals(33.587275,AddressMap.getLatLng("ENGCTR").latitude,.1);

    }

    @Test
    public void getBuildingArray_isCorrect() {
        assertEquals("ADMIN",AddressMap.getBuildingArray().iterator().next());


    }

    @Test
    public void fetch_isCorrect() {
        assertEquals("33.587275, -101.875771", AddressMap.fetch("ENGCTR"));
    }

    @Test
    public void fetchCoordinates_isCorrect() {
        assertEquals("33.583427, -101.874702",AddressMap.fetchCoordinates("ADMIN"));
        assertEquals(null,AddressMap.fetchCoordinates("GARBAGE HALL"));

    }


}