package me.forsy.genjaplugin;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public ConsoleCommandSender console;

    public Main() {
    }

    public void onEnable() {
        this.console = Bukkit.getServer().getConsoleSender();
        System.out.println("Plugin Genjaserver Started");
        this.getServer().getPluginManager().registerEvents(new Genjaserver(this), this);
    }

    public Logger getSLF4JLogger() {
        return null;
    }

    public void onDisable() {
        this.console.sendMessage("Plugin Genjaserver disabled!");
    }
}
