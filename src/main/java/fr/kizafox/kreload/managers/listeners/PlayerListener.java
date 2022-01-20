package fr.kizafox.kreload.managers.listeners;

import fr.kizafox.kreload.KReload;
import fr.kizafox.kreload.utils.inventories.gui.KReloadGui;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerListener implements Listener {

    protected final KReload instance;

    public PlayerListener(KReload instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        new KReloadGui((Player) event.getWhoClicked(), this.instance).onInventoryClick(event);
    }
}
