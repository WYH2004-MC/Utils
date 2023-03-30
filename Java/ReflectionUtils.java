package top.wyh2004.baka.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author WYH2004
 * @date 2023/1/21
 **/
public class ReflectionUtils {
    private static String version;

    static {
        version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }

    public static Class<?> getNMSClass(String className) {
        try {
            return Class.forName("net.minecraft.server." + version + "." + className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object getHandle(World world) {
        try {
            Method getHandle = world.getClass().getMethod("getHandle");
            return getHandle.invoke(world);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }
}