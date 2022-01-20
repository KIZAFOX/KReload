package fr.kizafox.kreload.utils.inventories.gui;

import fr.kizafox.kreload.KReload;
import fr.kizafox.kreload.utils.ItemBuilder;
import fr.kizafox.kreload.utils.inventories.AbstractInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KReloadGui extends AbstractInventory {

    protected final KReload instance;

    public KReloadGui(Player player, KReload instance) {
        super(player);

        this.instance = instance;
    }

    @Override
    public void display() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, this.instance.getConfig().getString("messages.inventory_name").replace("&", "§"));

        inventory.setItem(2, new ItemBuilder(Material.JUKEBOX).setName(ChatColor.GOLD + "" +  ChatColor.BOLD + "KReload").setLore(" ", ChatColor.GRAY + "Click to start reload !").toItemStack());

        this.instance.getServer().getScheduler().runTask(this.instance, () -> this.getPlayer().openInventory(inventory));
    }

    @Override
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();

        if(itemStack == null || itemStack.getType() == null || itemStack.getItemMeta() == null) return;

        if(inventory.getName().equalsIgnoreCase(this.instance.getConfig().getString("messages.inventory_name").replace("&", "§"))){
            event.setCancelled(true);

            if(itemStack.getType().equals(Material.JUKEBOX)){
                player.closeInventory();
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if(players.isOp()) players.sendMessage(this.instance.getConfig().getString("messages.before_reload").replace("&", "§").replace("%countdown%", String.valueOf(this.instance.getConfig().getInt("countdown"))));

                    this.instance.getServer().getScheduler().runTaskLater(this.instance, () -> {
                        this.instance.getServer().reload();
                        players.sendMessage(this.instance.getConfig().getString("messages.after_reload").replace("&", "§"));
                    }, this.instance.getConfig().getInt("countdown") * 20L);
                });
            }
        }
    }
}
