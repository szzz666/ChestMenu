package top.szzz666.ChestMenu.command;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import static top.szzz666.ChestMenu.ChestMenuMain.ec;
import static top.szzz666.ChestMenu.tools.pluginUtil.openMenu;


public class MyCommand extends Command {
    public MyCommand() {
        super(ec.getString("command"), "打开菜单");
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (sender.isPlayer()) {
            Player player = (Player) sender;
            openMenu(sender, args, player);
        }
        return true;
    }


}
