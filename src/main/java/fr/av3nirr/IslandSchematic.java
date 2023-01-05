package fr.av3nirr;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import org.bukkit.Location;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IslandSchematic {
    UltimateSkyblock main = UltimateSkyblock.getInstance();
    private Location location;
    private String fileName;
    private World world;


    public IslandSchematic(Location location, String fileName, World world){
        this.location = location;
        this.fileName = fileName;
        this.world = world;
    }

    public void spawnIsland() {
        File file = new File(main.getDataFolder(), "/schematics/" + fileName);
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            Clipboard clipboard = reader.read();
            EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(this.world, -1);
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(location.getX(), location.getY(), location.getZ()))
                    .ignoreAirBlocks(false)
                    .build();
            Operations.complete(operation);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | WorldEditException e) {
            throw new RuntimeException(e);
        }
    }
}
