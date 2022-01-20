package fr.kizafox.kreload.managers.command;

import fr.kizafox.kreload.KReload;
import fr.kizafox.kreload.utils.inventories.gui.KReloadGui;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandKReload implements CommandExecutor {

    protected final KReload instance;

    public CommandKReload(KReload instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Error..");
            return true;
        }

        Player player = (Player) sender;

        if(!player.isOp()){
            player.sendMessage(this.instance.getConfig().getString("messages.no-permission").replace("&", "§"));
            return true;
        }

        if(args.length > 0){
            player.sendMessage(ChatColor.RED + "/kreload");
            return true;
        }

        new KReloadGui(player, this.instance).display();

        return false;
    }
}
