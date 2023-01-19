package fr.luclyoko.osufrlivelogs;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private LogManager logManager;
    public LogManager getLogManager() {return logManager;}

    @Override
    public void onEnable() {
        this.getLogger().info("osufrlivelogs initialization...");
        this.getServer().getPluginManager().registerEvents(new GameEvents(this), this);
        this.logManager = new LogManager(this);
    }
}
