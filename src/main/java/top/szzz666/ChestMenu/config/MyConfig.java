package top.szzz666.ChestMenu.config;


import static top.szzz666.ChestMenu.ChestMenuMain.plugin;
import static top.szzz666.ChestMenu.ChestMenuMain.ec;


public class MyConfig {
    public static void initConfig() {
        plugin.saveResource("menus/main.json");
        ec = new EasyConfig("config.yml", plugin);
        ec.add("command", "openmenu");
        ec.add("clearInventoryCommand", "cleargfi");
        ec.add("item", "347:0:1:8:星空菜单");
        ec.load();
    }

}
