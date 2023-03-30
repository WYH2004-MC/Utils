package top.wyh2004.baka.utils;

import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author WYH2004
 * @date 2021/10/15
 **/
public class randomLocation {
    private static Location location;

    public static Location randomLocation(World world, double y){
        location = new Location(world, randomInt(0, 100000), y, randomInt(0, 100000));
        return location;
    }

    public static Location randomLocation(World world, double y, double xPos1, double xPos2, double zPos1, double zPos2){
        int x1 = (int) xPos1;
        int x2 = (int) xPos2;
        int z1 = (int) zPos1;
        int z2 = (int) zPos2;
        location = new Location(world, randomInt(x1, x2), y, randomInt(z1, z2));
        return location;
    }

    public static int randomInt(int min, int max){
        return (int) min + (int) (Math.random() * (max - min));
    }

}
