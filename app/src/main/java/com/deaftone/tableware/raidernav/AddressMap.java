package com.deaftone.tableware.raidernav;

import java.util.*;

public final class AddressMap
{
    private static Map<String, String> Coordinates;

    public static void initialize() {
        TreeMap<String, String> temp = new TreeMap<String, String>();
        temp.put("ADMIN","33.583427, -101.874702");
        temp.put("ADMSPT","33.592115, -101.880598");
        temp.put("AFS","33.582995, -101.888499");
        temp.put("AGED","33.582791, -101.877213");
        temp.put("AGRI","33.582891, -101.875945");
        temp.put("AQUA","33.583524, -101.884366");
        temp.put("ARCH","33.580655, -101.880654");
        temp.put("ART","33.580745, -101.879969");
        temp.put("ART3D","33.585053, -101.885019");
        temp.put("BIOL","33.585114, -101.878141");
        temp.put("BIOL LH", "33.584775, -101.877846");
        temp.put("CASNR","33.582615, -101.878359");
        temp.put("CDRCAR","33.582761, -101.873286");
        temp.put("CHEM","33.583898, -101.876273");
        temp.put("CHEME","33.587339, -101.876316");
        temp.put("CIVILE","33.586529, -101.873942");
        temp.put("CRMVST","33.588083, -101.872752");
        temp.put("DOAK","33.582733, -101.871628");
        temp.put("EDUC","33.580196, -101.879180");
        temp.put("ELECE","33.586641, -101.875401");
        temp.put("ENGCTR","33.587275, -101.875771");
        temp.put("ENGPHL","33.581556, -101.879243");
        temp.put("ETLAB","33.587246, -101.873858");
        temp.put("EXPSC","33.585494, -101.877123");
        temp.put("FDTECH","33.582616, -101.876385");
        temp.put("FSHWLD","33.582741, -101.878846");
        temp.put("FORL","33.580847, -101.878481");
        temp.put("GODDRD","33.582086, -101.878067");
        temp.put("GRNHSE" ,"33.584090, -101.886974");
        temp.put("HOLDEN","33.585557, -101.873720");
        temp.put("HUMSCI","33.583484, -101.873136");
        temp.put("ICC","33.588825, -101.886436");
        temp.put("IMSE","33.587224, -101.876308");
        temp.put("JONES","33.591052, -101.872893");
        temp.put("KSM","33.585545, -101.883860");
        temp.put("LANR","33.578925, -101.887416");
        temp.put("LAW","33.578906, -101.886683");
        temp.put("LIBR","33.581388, -101.876108");
        temp.put("LVRMRC","33.587759, -101.876065");
        temp.put("MATH","33.584941, -101.876226");
        temp.put("MCOM","33.581961, -101.880352");
        temp.put("MLAB","33.583277, -101.889390");
        temp.put("MEN","33.588382, -101.874667");
        temp.put("MES","33.587945, -101.874654");
        temp.put("MSC","33.588987, -101.874729");
        temp.put("MUSEUM","33.590335, -101.886016");
        temp.put("MUSIC","33.580847, -101.874541");
        temp.put("PAVILN","33.582324, -101.876794");
        temp.put("PLTSCI","33.581800, -101.878556");
        temp.put("PSYC","33.580502, -101.877124");
        temp.put("RCOBA","3.587839, -101.879230");
        temp.put("RGOLF","33.600525, -101.892223");
        temp.put("SCIENC","33.584463, -101.876990");
        temp.put("SRC","33.582681, -101.884666");
        temp.put("SUB","33.581384, -101.874675");
        temp.put("SWCOLL","33.581907, -101.877143");
        temp.put("THEATR","33.580176, -101.877829");
        temp.put("TTFCU","33.579381, -101.891537");
        temp.put("TPLAZA","33.577454, -101.870168");
        temp.put("TTPD","33.591777, -101.879786");
        temp.put("TTUHSC","33.589127, -101.891924");
        temp.put("TVRDST","33.581256, -101.889199");
        temp.put("UMC","33.588788, -101.891866");
        temp.put("USA","33.581289, -101.886574");
        temp.put("Administration Building","33.583427, -101.874702");
        temp.put("Administrative Support Center","33.592115, -101.880598");
        temp.put("Agricultural Education & Communication","33.582791, -101.877213");
        temp.put("Agricultural Pavilion","33.582324, -101.876794");
        temp.put("Agricultural Sciences & Natural Resources","33.582891, -101.875945");
        temp.put("Amon G. Carter Plaza","33.584479, -101.872361");
        temp.put("Animal & Food Sciences","33.582995, -101.888499");
        temp.put("Architecture","33.580655, -101.880654");
        temp.put("Art","33.580745, -101.879969");
        temp.put("Art 3D Annex","33.585053, -101.885019");
        temp.put("Athletic Offices","33.590238, -101.872923");
        temp.put("Athletic Ticket Office","33.590651, -101.871656");
        temp.put("Bayer Plant Science","33.581823, -101.878769");
        temp.put("Biology","33.585114, -101.878141");
        temp.put("Biology Greenhouse","33.584806, -101.878556");
        temp.put("Biology Lecture Hall", "33.584775, -101.877846");
        temp.put("Bledsoe Residence Hall","33.586485, -101.871752");
        temp.put("Bonfire Pit","33.581473, -101.883778");
        temp.put("Burkhardt Center for Autism Education & Research","33.580285, -101.878370");
        temp.put("CASNR Annex","33.582615, -101.878359");
        temp.put("Carpenter/Wells Complex","33.585997, -101.879968");
        temp.put("Center for the Study of Addiction & Recovery","33.583305, -101.873360");
        temp.put("Chemical Engineering","33.587339, -101.876316");
        temp.put("Chemistry","33.583898, -101.876273");
        temp.put("Child Development Research Center","33.582761, -101.873286");
        temp.put("Chitwood Residence Hall","33.580046, -101.882623");
        temp.put("City Bank Auditorium","33.590077, -101.876588");
        temp.put("City Bank Coliseum","33.590103, -101.877665");
        temp.put("Civil Engineering","33.586529, -101.873942");
        temp.put("Clement Residence Hall","33.579003, -101.879890");
        temp.put("Clock Tower","33.585987, -101.879238");
        temp.put("Coleman Residence Hall","33.578802, -101.882445");
        temp.put("Commons Dining","33.579671, -101.874066");
        temp.put("Computer Center","33.587643, -101.875316");
        temp.put("Creative Movement Studio","33.588083, -101.872752");
        temp.put("Dairy Barn & Silo","33.581366, -101.878099");
        temp.put("Dan Law Field","33.588779, -101.876793");
        temp.put("Development Office","33.581655, -101.873050");
        temp.put("Doak Conference Center","33.582733, -101.871628");
        temp.put("Doak Hall","33.582934, -101.872465");
        temp.put("Double T Bench","33.583168, -101.874667");
        temp.put("Drane Hall","33.581609, -101.872313");
        temp.put("Education","33.580196, -101.879180");
        temp.put("Electrical & Computer Engineering","33.586641, -101.875401");
        temp.put("English & Philosophy","33.581556, -101.879243");
        temp.put("Engineering Center","33.587275, -101.875771");
        temp.put("Engineering & Technology Lab","33.587246, -101.873858");
        temp.put("Equestrian Center","33.540540, -101.991810");
        temp.put("Experimental Sciences","33.585494, -101.877123");
        temp.put("Fisheries & Wildlife Research","33.582741, -101.878846");
        temp.put("Flint Parking Garage", "33.579360, -101.881023");
        temp.put("Food Technology","33.582616, -101.876385");
        temp.put("Football Training Facility","33.589791, -101.871571");
        temp.put("Foreign Language","33.580847, -101.878481");
        temp.put("Frazier Alumni Pavilion","33.589642, -101.874720");
        temp.put("Fuller Track","33.587223, -101.878041");
        temp.put("Garrison Geriatric Education & Care Center","33.593253, -101.893658");
        temp.put("Gates Residence Hall","33.579052, -101.877386");
        temp.put("Goddard Range & Wildlife Management","33.582086, -101.878067");
        temp.put("Gordon Residence Hall","33.586374, -101.871999");
        temp.put("Greenhouse & Horticultural Garden" ,"33.584090, -101.886974");
        temp.put("Helen DeVitt Jones Auditorium & Sculpture Court","33.590325, -101.886357");
        temp.put("Hemmle Recital Hall","33.580874, -101.874223");
        temp.put("High Performance Computing Center", "33.585668, -101.877120");
        temp.put("Holden Hall","33.585557, -101.873720");
        temp.put("Honors College","33.581356, -101.873196");
        temp.put("Honors Residence Hall","33.587063, -101.880098");
        temp.put("Horn Residence Hall","33.580627, -101.872402");
        temp.put("Housing Services","33.585576, -101.885528");
        temp.put("Hulen Residence Hall","33.579010, -101.879351");
        temp.put("Human Sciences","33.583484, -101.873136");
        temp.put("Industrial, Manufacturing, & Systems Engineering","33.587224, -101.876308");
        temp.put("Innovation Hub at Research Park","33.591368, -101.899685");
        temp.put("Interactive Sun Dial","33.589707, -101.875337");
        temp.put("International Cultural Center","33.588825, -101.886436");
        temp.put("IT Help Central","33.591983, -101.880955");
        temp.put("Jones AT&T Stadium","33.591052, -101.872893");
        temp.put("Kent Hance Chapel","33.579277, -101.871356");
        temp.put("Kinesiology & Sports Management","33.585545, -101.883860");
        temp.put("Knapp Residence Hall","33.580532, -101.873089");
        temp.put("KTTZ-TV","33.581256, -101.889199");
        temp.put("Lanier Professional Development Center","33.578925, -101.887416");
        temp.put("Law Building","33.578906, -101.886683");
        temp.put("Library","33.581388, -101.876108");
        temp.put("Livermore Center","33.587759, -101.876065");
        temp.put("Maddox Engineering Research Center","33.585471, -101.875367");
        temp.put("Maedgen Theatre","33.580176, -101.877829");
        temp.put("Marsha Sharp Center for Student Athletics","33.588987, -101.874729");
        temp.put("Mathematics & Statistics","33.584941, -101.876226");
        temp.put("McClellan Hall","33.581353, -101.873184");
        temp.put("McKenzie-Merket Alumni Center","33.579933, -101.872806");
        temp.put("Meat Lab & Livestock Arena","33.583277, -101.889390");
        temp.put("Mechanical Engineering North","33.588382, -101.874667");
        temp.put("Mechanical Engineering South","33.587945, -101.874654");
        temp.put("Media & Communication","33.581961, -101.880352");
        temp.put("Medical Office Plaza","33.588449, -101.890067");
        temp.put("Memorial Circle & Pfluger Fountain","33.584487, -101.874681");
        temp.put("Moody Planetarium","33.591075, -101.885384");
        temp.put("Murdough Residence Hall","33.583711, -101.880915");
        temp.put("Murray Residence Hall","33.586608, -101.878735");
        temp.put("Music","33.580847, -101.874541");
        temp.put("National Ranching Heritage Center","33.590731, -101.884048");
        temp.put("National Wind Institute","33.585573, -101.875952");
        temp.put("Natural Science Research Laboratory","33.589793, -101.886128");
        temp.put("Petroleum Engineering","33.587097, -101.876335");
        temp.put("Petroleum Engineering Research","33.587743, -101.873844");
        temp.put("Physical Plant","33.585965, -101.883225");
        temp.put("Plant Sciences","33.581800, -101.878556");
        temp.put("Preston Smith Library","33.589501, -101.894131");
        temp.put("Psychology","33.580502, -101.877124");
        temp.put("RaiderPark Parking Garage", "33.593271, -101.874212");
        temp.put("Rawls College of Business","3.587839, -101.879230");
        temp.put("Rawls Golf Course","33.600525, -101.892223");
        temp.put("Rec Aquatic Facilities","33.583524, -101.884366");
        temp.put("Rec Sports Fields","33.582994, -101.882797");
        temp.put("Rec Sports Leisure Pool","33.582294, -101.883841");
        temp.put("Rec Sports Softball Fields","33.583806, -101.894485");
        temp.put("Student Recreational Centre","33.582681, -101.884666");
        temp.put("Rocky Johnson Softball Field","33.584542, -101.891969");
        temp.put("Ronald McDonald House","33.585362, -101.889362");
        temp.put("Science-Physics & Geosciences","33.584463, -101.876990");
        temp.put("Seal of Texas Tech","33.584480, -101.871509");
        temp.put("Sneed Residence Hall","33.585382, -101.871396");
        temp.put("Student Union Building","33.581384, -101.874675");
        temp.put("Student Wellness","33.586084, -101.881745");
        temp.put("Southwest Cancer Center","33.587889, -101.892095");
        temp.put("Southwest Collection/Special Collections Library","33.581907, -101.877143");
        temp.put("Stangel Residence Hall","33.583537, -101.880102");
        temp.put("Talkington Residence Hall","33.579119, -101.874655");
        temp.put("Tennis Center","33.585303, -101.893733");
        temp.put("Texas Tech Federal Credit Union","33.579381, -101.891537");
        temp.put("Texas Tech Marquee","33.578611, -101.889199");
        temp.put("Texas Tech University Museum","33.590335, -101.886016");
        temp.put("Texas Tech Physicians Medical Pavilion","33.590140, -101.891030");
        temp.put("Texas Tech Plaza","33.577454, -101.870168");
        temp.put("Texas Tech Police Department","33.591777, -101.879786");
        temp.put("Texas Tech University Health Sciences Centre","33.589127, -101.891924");
        temp.put("Texas Tech University System Building","33.581179, -101.892888");
        temp.put("United Supermarkets Arena","33.581289, -101.886574");
        temp.put("University Career Services","33.579398, -101.883801");
        temp.put("University Medical Center","33.588788, -101.891866");
        temp.put("University Medical Center Emergency Room","33.588476, -101.893079");
        temp.put("USDA Agricultural Research Service","33.593170, -101.897627");
        temp.put("Urbanovsky Park","33.581116, -101.883539");
        temp.put("Walker Soccer Complex","33.584267, -101.898340");
        temp.put("Wall Residence Hall","33.579044, -101.876632");
        temp.put("Weeks Hall","233.583525, -101.871359");
        temp.put("West Hall","33.585308, -101.872566");
        temp.put("West Village A Residence Hall","33.578625, -101.890528");
        temp.put("West Village B Residence Hall","33.578508, -101.888422");
        temp.put("Weymouth Residence Hall","33.579606, -101.882142");
        temp.put("Wiggins Complex","33.579755, -101.883695");
        Coordinates = Collections.unmodifiableMap(temp);
    }

    public static Set<String> getKeys() {
        return Coordinates.keySet();
    }

    public static CharSequence[] getKeysAsCharSequence() {
        Object[] tempkeyset = Coordinates.keySet().toArray();
        CharSequence[] result = new CharSequence[tempkeyset.length];
        for(int i = 0; i < tempkeyset.length; i++) {
            result[i] = (CharSequence) tempkeyset[i];
        }
        return result;
    }

    public static double[] getXY(String buildingName) {
        String temp = fetch(buildingName);
        String[] temp2 = temp.split(" ");
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
  
    /* Returns ArrayList for drop down menu display */
    public static ArrayList<String> getBuildingArray()
    {
        ArrayList buildingArray = new ArrayList();
        for(String buildingName : Coordinates.keySet())
        {
            buildingArray.add(buildingName);
        }
        return buildingArray;
    }

    public static String fetch(String buildingName)
    {
        return Coordinates.get(buildingName);
    }

    /* Use this method in Maps API call to pass coordinates from appropriate key */
    public static String fetchCoordinates(String buildingName)
    {
        return Coordinates.get(buildingName);
    }
}