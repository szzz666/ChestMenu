package top.szzz666.ChestMenu.tools;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import io.leego.banana.BananaUtils;
import io.leego.banana.Font;
import lombok.SneakyThrows;
import top.szzz666.ChestMenu.entity.Menu;
import top.szzz666.ChestMenu.panel.esay_chest_menu.BigChestMenu;
import top.szzz666.ChestMenu.panel.esay_chest_menu.ChestMenu;
import top.szzz666.ChestMenu.panel.esay_chest_menu.lib.AbstractFakeInventory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static top.szzz666.ChestMenu.ChestMenuMain.*;
import static top.szzz666.ChestMenu.tools.taskUtil.Async;
import static top.szzz666.ChestMenu.tools.taskUtil.Delayed;

public class pluginUtil {
    public static void openMenu(CommandSender sender, String[] args, Player player) {
        Async(() -> {
            String arg = args.length == 1 ? args[0] : "main";
            String path = ConfigPath + "/menus/" + arg + ".json";
            String content;
            try {
                content = new String(Files.readAllBytes(Paths.get(path)));
            } catch (IOException e) {
                sender.sendMessage("§c" + path + " 文件不存在");
                return;
            }
            Gson gson = new Gson();
            Menu menu = gson.fromJson(content, Menu.class);
            if (menu.getType() == 0) {
                ChestMenu cmenu = new ChestMenu(menu.getTitle(), true);
                for (String eitem : menu.getButtons().keySet()) {
                    ArrayList<String> commands = menu.getButtons().get(eitem);
                    String[] eitemArr = eitem.split(":");
                    int slot = Integer.parseInt(eitemArr[0]);
                    int id = Integer.parseInt(eitemArr[1]);
                    int damage = Integer.parseInt(eitemArr[2]);
                    int count = Integer.parseInt(eitemArr[3]);
                    String name = eitemArr[4];
                    Item item = Item.get(id, damage, count);
                    item.setCustomName(name);
                    cmenu.add(slot, item, () -> multCmds(sender, commands));
                }
                cmenu.show(player);
                return;
            }
            if (menu.getType() == 1){
                BigChestMenu cmenu = new BigChestMenu(menu.getTitle(), true, true);
                for (String eitem : menu.getButtons().keySet()) {
                    ArrayList<String> commands = menu.getButtons().get(eitem);
                    String[] eitemArr = eitem.split(":");
                    int slot = Integer.parseInt(eitemArr[0]);
                    int id = Integer.parseInt(eitemArr[1]);
                    int damage = Integer.parseInt(eitemArr[2]);
                    int count = Integer.parseInt(eitemArr[3]);
                    String name = eitemArr[4];
                    Item item = Item.get(id, damage, count);
                    item.setCustomName(name);
                    cmenu.add(slot, item, () -> multCmds(sender, commands));
                }
                cmenu.show(player);
            }
        });
    }

    public static void multCmds(CommandSender sender, ArrayList<String> commands) {
        Delayed(() -> {
            for (String command : commands) {
                nkServer.getCommandMap().dispatch(sender, command);
            }
        }, 10, true);

    }

    public static void multCmd(CommandSender sender, String command) {
        Delayed(() -> {
            nkServer.getCommandMap().dispatch(sender, command);
        }, 10, true);

    }

    public static void checkServer() {
        boolean ver = false;
        //双核心兼容
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            c.getField("NUKKIT_PM1E");
            ver = true;

        } catch (ClassNotFoundException | NoSuchFieldException ignore) {
        }
        try {
            Class<?> c = Class.forName("cn.nukkit.Nukkit");
            "Nukkit PetteriM1 Edition".equalsIgnoreCase(c.getField("NUKKIT").get(c).toString());
            ver = true;
        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException ignore) {
        }

        AbstractFakeInventory.IS_PM1E = ver;
        if (ver) {
            nkConsole("当前插件运行在: Nukkit PM1E 核心上");
        } else {
            nkConsole("当前插件运行在: Nukkit 核心上");
        }
    }

    //Banana
    @SneakyThrows
    public static void pluginNameLineConsole() {
        lineConsole(BananaUtils.bananaify(plugin.getName(), Font.SMALL));
    }

    //将输入的字符串按行打印到控制台。
    public static void lineConsole(String s) {
        String[] lines = s.split("\n");
        for (String line : lines) {
            nkConsole(line);
        }
    }

    //使用nk插件的控制台输出
    public static void nkConsole(String msg) {
        plugin.getLogger().info(TextFormat.colorize('&', msg));
    }

    public static void nkConsole(String msg, int typeNum) {
        if (typeNum == 1) {
            plugin.getLogger().warning(TextFormat.colorize('&', msg));
        } else if (typeNum == 2) {
            plugin.getLogger().error(TextFormat.colorize('&', msg));
        } else {
            plugin.getLogger().info(TextFormat.colorize('&', msg));
        }
    }
}
