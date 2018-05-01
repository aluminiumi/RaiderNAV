package com.deaftone.tableware.raidernav;

import java.util.*;
import com.google.android.gms.maps.model.LatLng;

public final class OnCampusFood {
    private static Map<String, String> Coords;

    public static void initialize(){
        TreeMap<String, String> temp = new TreeMap<String, String>();
        temp.put("All-You-Care-To-Eat @ Hulen/Clement", "33.579155, -101.879688");
        temp.put("Chef's Corner", "33.579641, -101.874057");
        temp.put("Einstein Bros Bagels", "33.579641, -101.874057");
        temp.put("Grillz", "33.579641, -101.874057");
        temp.put("Greens & Things","33.579641, -101.874057");
        temp.put("Just Say Cheez","33.579641, -101.874057");
        temp.put("Khan's Mongolian Grill","33.579641, -101.874057");
        temp.put("Kluck'rs", "33.579641, -101.874057");
        temp.put("Parillas", "33.579641, -101.874057");
        temp.put("Pi Pizza & Pasta", "33.579641, -101.874057");
        temp.put("The Fresh Plate", "33.585944, -101.871722");
        temp.put("The Carvery", "33.583796, -101.880393");
        temp.put("Chopstix", "33.583796, -101.880393");
        temp.put("Daybreak Coffee Roasters & More", "33.583796, -101.880393");
        temp.put("Fazoli's", "33.583796, -101.880393");
        temp.put("Flav'r", "33.583796, -101.880393");
        temp.put("Ramen", "33.583796, -101.880393");
        temp.put("Red & Black Grill", "33.583796, -101.880393");
        temp.put("Spuds & Toppings", "33.583796, -101.880393");
        temp.put("Subs & Wraps", "33.583796, -101.880393");
        temp.put("Tacos y Mas", "33.583796, -101.880393");
        temp.put("The Corner Market", "33.583796, -101.880393");
        temp.put("Toss'd Salads | Made-to-Order", "33.583796, -101.880393");
        temp.put("Wings", "33.583796, -101.880393");
        temp.put("Yo2Go", "33.583796, -101.880393");
        temp.put("On-The-Go @ Horn/Knapp", "33.580547, -101.872758");
        temp.put("Quiznos", "33.580320, -101.878508");
        temp.put("Chick-fil-A Express @ Rawls", "33.587843, -101.879231");
        temp.put("Einstein Bros Bagels @ Rawls", "33.587843, -101.879231");
        temp.put("Sam's Express @ Livermore", "3.587667, -101.876091");
        temp.put("Sam's Express @ Health Sciences Center", "33.589107, -101.891977");
        temp.put("Sam's Express @ Holden Hall", "33.585699, -101.873603");
        temp.put("Sam's Express @ Human Sciences", "33.583337, -101.873346");
        temp.put("Sam's Express @ Law School", "33.578800, -101.886819");
        temp.put("Sam's Express @ Library", "33.581496, -101.875790");
        temp.put("Sam's Express @ Media & Communications", "33.581954, -101.880355");
        temp.put("Sam's Express @ Petroleum", "33.587754, -101.873866");
        temp.put("Sam's Place @ Murray", "33.586634, -101.878756");
        temp.put("Sam's Place @ Sneed", "33.585424, -101.871431");
        temp.put("Sam's Place @ Student Union Building", "33.581661, -101.874579");
        temp.put("Sam's Place @ Wall | Gates", "3.579104, -101.876957");
        Coords = Collections.unmodifiableMap(temp);
    }

    public static Set<String> getKeys(){
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
