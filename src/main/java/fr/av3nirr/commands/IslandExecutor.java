package fr.av3nirr.commands;

import com.sk89q.worldedit.world.World;
import fr.av3nirr.IslandSchematic;
import fr.av3nirr.UltimateSkyblock;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class IslandExecutor implements CommandExecutor {
    UltimateSkyblock main = UltimateSkyblock.getInstance();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        if (!(p instanceof Player)) return true;
        String prefix = main.getComponents().getString("simpleskyblock.prefix");
        if (args.length != 1){
            //faire le tp à l'île
            p.sendMessage(prefix + main.getComponents().getString("simpleskyblock.island.teleport.message"));
            return true;
        }
        if (args[0] == "create"){
            IslandSchematic is = new IslandSchematic(p.getLocation(), "house.schematic", (World) p.getWorld());
            is.spawnIsland();
            System.out.println("Ca marche");
            return true;
        }
        return false;
    }
}
