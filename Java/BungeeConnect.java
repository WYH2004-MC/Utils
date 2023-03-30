package top.wyh2004.baka.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.wyh2004.baka.Main;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author WYH2004
 * @date 2021/10/23
 **/
public class BungeeConnect {
    public static void connectServer(Player p, String server){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        } catch (IOException ex) {}
        p.sendPluginMessage(Main.getInstance(), "BungeeCord",b.toByteArray());
    }

    public static void connectServer(String p, String server){
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("ConnectOther");
            out.writeUTF(p);
            out.writeUTF(server);
        } catch (IOException ex) {}
        Bukkit.getServer().sendPluginMessage(Main.getInstance(), "BungeeCord",b.toByteArray());
    }
}
