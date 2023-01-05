package fr.av3nirr;

import fr.av3nirr.commands.IslandExecutor;
import fr.av3nirr.components.Components;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UltimateSkyblock extends JavaPlugin {
    public Logger log;
    private Components storage;
    private static UltimateSkyblock INSTANCE;

    @Override
    public void onEnable() {
        INSTANCE = this;
        log = getLogger();
        log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
        log.log(Level.INFO , ChatColor.GREEN + "[SimpleSkyBlock] plugin is initializing !");
        log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
        try {
            storage = new Components();
            log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
            log.log(Level.INFO , ChatColor.GREEN + "[SimpleSkyBlock] config files are initializing !");
            log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
            getComponents().load();
        } catch (IOException e) {
            log.log(Level.INFO,ChatColor.DARK_RED + "-------------------------------------------------------------------");
            log.log(Level.SEVERE,ChatColor.RED + "Error while enabling configurations files, FATAL");
            log.log(Level.INFO,ChatColor.DARK_RED + "-------------------------------------------------------------------");
            getPluginLoader().disablePlugin(this);
            return;
        }
        try{
            log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
            log.log(Level.INFO , ChatColor.GREEN + "[SimpleSkyBlock] commands are initializing !");
            log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
            setCommands();
            setHandlers();
        }catch (Exception e){
            log.log(Level.INFO,ChatColor.DARK_RED + "-------------------------------------------------------------------");
            log.log(Level.SEVERE,ChatColor.RED + "Error while enabling commands, FATAL");
            log.log(Level.INFO,ChatColor.DARK_RED + "-------------------------------------------------------------------");
            getPluginLoader().disablePlugin(this);
            return;
        }
        log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");
        log.log(Level.INFO , ChatColor.GREEN + "[SimpleSkyBlock] plugin is successfully initialized !");
        log.log(Level.INFO,ChatColor.DARK_GREEN + "-------------------------------------------------------------------");

    }

    @Override
    public void onDisable() {
        log = getLogger();
        log.log(Level.INFO,ChatColor.YELLOW + "-------------------------------------------------------------------");
        log.log(Level.INFO , ChatColor.RED + "[SimpleSkyBlock] plugin is shutting down !");
        log.log(Level.INFO,ChatColor.YELLOW + "-------------------------------------------------------------------");
    }
    public void setHandlers(){

    }
    public void setCommands(){
        getCommand("island").setExecutor(new IslandExecutor());

    }
    public Components getComponents(){
        return storage;
    }

    public static UltimateSkyblock getInstance(){
        return INSTANCE;
    }
}
