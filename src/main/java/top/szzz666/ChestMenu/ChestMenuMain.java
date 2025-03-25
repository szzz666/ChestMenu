package top.szzz666.ChestMenu;

import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import top.szzz666.ChestMenu.command.MyCommand;
import top.szzz666.ChestMenu.config.EasyConfig;
import top.szzz666.ChestMenu.entity.Menu;
import top.szzz666.ChestMenu.event.Listeners;
import top.szzz666.ChestMenu.panel.esay_chest_menu.CMListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import static top.szzz666.ChestMenu.config.MyConfig.initConfig;
import static top.szzz666.ChestMenu.tools.pluginUtil.checkServer;
import static top.szzz666.ChestMenu.tools.pluginUtil.nkConsole;


public class ChestMenuMain extends PluginBase {
    public static Plugin plugin;
    public static Server nkServer;
    public static CommandSender consoleObjects;
    public static String ConfigPath;
    public static EasyConfig ec;

    //插件读取
    @Override
    public void onLoad() {
        nkServer = getServer();
        plugin = this;
        consoleObjects = getServer().getConsoleSender();
        ConfigPath = getDataFolder().getPath();
        ec = new EasyConfig("config.yml", plugin);
        initConfig();
        nkConsole("&b" + plugin.getName() + "插件读取...");
    }

    //插件开启
    @Override
    public void onEnable() {
        checkServer();
        //注册监听器
        nkServer.getPluginManager().registerEvents(new Listeners(), this);
        nkServer.getPluginManager().registerEvents(new CMListener(), this);
        //注册命令
        nkServer.getCommandMap().register(this.getName(), new MyCommand());
//        pluginNameLineConsole();
        nkConsole("&b" + plugin.getName() + "插件开启");
        nkConsole("&c" + plugin.getName() + "如果遇到任何bug，请加入Q群进行反馈：894279534", 1);
    }

    //插件关闭
    @Override
    public void onDisable() {
        nkConsole("&b" + plugin.getName() + "插件关闭");
    }

}
