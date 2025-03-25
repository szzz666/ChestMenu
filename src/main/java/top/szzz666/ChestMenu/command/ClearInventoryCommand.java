package top.szzz666.ChestMenu.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.ChestMenu.ChestMenuMain.ec;
import static top.szzz666.ChestMenu.event.Listeners.giveFormItem;


public class ClearInventoryCommand extends Command {
    public ClearInventoryCommand() {
        super(ec.getString("clearInventoryCommand"), "清空背包并且给予快捷菜单");
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Player player = (Player) sender;
            player.getInventory().clearAll();
            giveFormItem(player);
            return true;
        }
        return true;
    }
}
