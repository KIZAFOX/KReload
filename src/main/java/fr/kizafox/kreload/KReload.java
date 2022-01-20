package fr.kizafox.kreload;

import fr.kizafox.kreload.managers.command.CommandKReload;
import fr.kizafox.kreload.managers.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class KReload extends JavaPlugin {

    private static KReload instance;

    @Override
    public void onEnable() {
        long current = System.currentTimeMillis();
        log(ChatColor.DARK_GRAY + "****************************** " + ChatColor.GOLD + "KRELOAD BY KIZAFOX" + ChatColor.DARK_GRAY + " ******************************");
        instance = this;
        log(ChatColor.GRAY + "*************** Global ***************");
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        log(ChatColor.GREEN + "Configuration file... OK");
        this.getCommand("kreload").setExecutor(new CommandKReload(this));
        Bukkit.getPluginManager().registerEvents(new PlayerListener(this), this);
        log(ChatColor.GREEN + "Plugin " + this.getDescription().getName() + " made by " + this.getDescription().getAuthors());
        log(ChatColor.GREEN + "Version: " + this.getDescription().getVersion());
        log(ChatColor.GREEN + "Site: " + this.getDescription().getWebsite());
        log(ChatColor.GREEN + this.getDescription().getDescription());
        log(ChatColor.GRAY + "******************************");
        log(ChatColor.GREEN + "Plugin loaded in " + ChatColor.BOLD + (System.currentTimeMillis() - current) + ChatColor.RESET + "" + ChatColor.GREEN + "s");
        log(ChatColor.DARK_GRAY + "****************************** " + ChatColor.GOLD + "KRELOAD BY KIZAFOX" + ChatColor.DARK_GRAY + " ******************************");
    }

    public static KReload get() {
        return instance;
    }

    public void log(String value) {
        this.getServer().getConsoleSender().sendMessage(value);
    }
}
