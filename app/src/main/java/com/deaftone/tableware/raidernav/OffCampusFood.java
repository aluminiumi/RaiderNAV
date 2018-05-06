package com.deaftone.tableware.raidernav;

import java.util.*;
import com.google.android.gms.maps.model.LatLng;

//TODO: This entire class should be refactored back into AddressMap
public final class OffCampusFood {

    private static Map<String, String> Coords;

    public static void initialize(){
        TreeMap<String, String> temp = new TreeMap<String, String>();

        temp.put("Domino's Pizza", "3.593453, -101.871765");
        temp.put("Chick-fil-A", "33.593432, -101.870948");
        temp.put("Popeyes Louisiana Kitchen", "33.594418, -101.870841");
        temp.put("KFC", "33.594975, -101.869934");
        temp.put("Wienerschnitzel", "33.594007, -101.870096");
        temp.put("Panda Express", "33.593510, -101.869998");
        temp.put("Chili's Grill & Bar", "33.589340, -101.870400");
        temp.put("Starbucks", "33.587991, -101.870305");
        temp.put("Spanky's", "33.587690, -101.870348");
        temp.put("Chipotle Mexican Grill", "33.588077, -101.869398");
        temp.put("Whataburger", "33.587552, -101.869432");
        temp.put("Heff's Burgers Texas Tech", "33.588027, -101.868683");
        temp.put("Potbelly Sandwich Shop", "33.587524, -101.868616");
        temp.put("Raising Cane's Chicken Fingers", "33.586772, -101.870102");
        temp.put("Torchy's Tacos", "33.586755, -101.868884");
        temp.put("One Guys Pizza", "33.585309, -101.870331");
        temp.put("Subway", "33.585106, -101.870336");
        temp.put("Local Bar and Grill", "33.584805, -101.870148");
        temp.put("Cricket's Draft House + Grill", "33.584780, -101.869693");
        temp.put("Chimy's", "33.584241, -101.869778");
        temp.put("Jimmy John's", "33.584283, -101.869416");
        temp.put("Fuzzy's Taco Shop", "33.584841, -101.862133");
        temp.put("Skooners Grill & Bar", "33.579205, -101.870298");
        temp.put("IHOP", "33.578531, -101.870316");
        temp.put("Lubbock Breakfast House And Grill", "3.578218, -101.869860");
        temp.put("McAlister's Deli", "33.577427, -101.868687");
        temp.put("Wendy's", "33.577485, -101.867781");
        temp.put("Skyview's", "33.577423, -101.870221");
        Coords = Collections.unmodifiableMap(temp);
    }
    public static Set<String> getKeys() {
        return Coords.keySet();
    }

    public static CharSequence[] getKeysAsCharSequence() {
        Object[] tempkeyset = Coords.keySet().toArray();
        CharSequence[] result = new CharSequence[tempkeyset.length];
        for(int i = 0; i < tempkeyset.length; i++) {
            result[i] = (CharSequence) tempkeyset[i];
        }
        return result;
    }

    public static double[] getXY(String buildingName) {
        String temp = fetch(buildingName);
        String[] temp2 = temp.split(", ");
        double xy[] = new double[2];
        xy[0] = Double.parseDouble(temp2[0]);
        xy[1] = Double.parseDouble(temp2[1]);
        return xy;
    }

    public static double[] parseXY(String destination) {
        String[] temp2 = destination.split(", ");
        double xy[] = new double[2];
        xy[0] = Double.parseDouble(temp2[0]);
        xy[1] = Double.parseDouble(temp2[1]);
        return xy;
    }

    public static LatLng getLatLng(String buildingName) {
        String[] temp2 = fetch(buildingName).split(", ");
        return new LatLng(Double.parseDouble(temp2[0]), Double.parseDouble(temp2[1]));
    }

    /* Returns ArrayList for drop down menu display */
    public static ArrayList<String> getBuildingArray()
    {
        ArrayList buildingArray = new ArrayList();
        for(String buildingName : Coords.keySet())
        {
            buildingArray.add(buildingName);
        }
        return buildingArray;
    }

    public static String fetch(String buildingName)
    {
        return Coords.get(buildingName);
    }

    /* Use this method in Maps API call to pass coordinates from appropriate key */
    public static String fetchCoordinates(String buildingName)
    {
        return Coords.get(buildingName);
    }
}
