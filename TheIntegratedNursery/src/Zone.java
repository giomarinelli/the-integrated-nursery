/*
* Zone.java
* @author Gio Marinelli, Ryan Jones
* @version  2024-03-04
*/
import java.util.HashMap;

public class Zone {

    
    private int minTemp;
    private int maxTemp;

    private static HashMap<Integer, Zone> zones;
    static {
        zones.put(1, new Zone(-2147483648, -50));
        zones.put(2, new Zone(-50, -40));
        zones.put(3, new Zone(-40, -30));
        zones.put(4, new Zone(-30, -20));
        zones.put(5, new Zone(-20, -10));
        zones.put(6, new Zone(-10, 0));
        zones.put(7, new Zone(0, 10));
        zones.put(8, new Zone(10, 20));
        zones.put(9, new Zone(20, 30));
        zones.put(10, new Zone(30, 40));
        zones.put(11, new Zone(40, 50));
    }

    private Zone(int minTemp, int maxTemp){
    this.minTemp = minTemp;
    this.maxTemp = maxTemp;
    }

}
