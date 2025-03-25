package top.szzz666.ChestMenu.event;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import cn.nukkit.item.Item;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.CompoundTag;
import top.szzz666.ChestMenu.entity.ItemStr;


import java.util.HashMap;

import static top.szzz666.ChestMenu.ChestMenuMain.ec;
import static top.szzz666.ChestMenu.ChestMenuMain.nkServer;
import static top.szzz666.ChestMenu.tools.pluginUtil.openMenu;
import static top.szzz666.ChestMenu.tools.taskUtil.Async;



public class Listeners implements Listener {
    static ItemStr itemStr = new ItemStr(ec.getString("item"));
    HashMap<String, Integer> cooldown = new HashMap<>();

    public static void giveFormItem(Player player) {
        Item item = player.getInventory().getItem(itemStr.getPos());
        if (itemStr.equals(item)) {
            return;
        }
        Item i = item.clone();
        Item cd = itemStr.toItem();
        cd.addEnchantment(Enchantment.get(Enchantment.ID_PROTECTION_ALL).setLevel(1));
        CompoundTag itemTag = cd.getNamedTag();
        itemTag.putBoolean("minecraft:item_lock", true);
        cd.setNamedTag(itemTag);
        player.getInventory().setItem(itemStr.getPos(), cd);
        player.giveItem(i);
        player.sendMessage("§a成功给予快捷菜单~");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Async(() -> {
            Player player = event.getPlayer();
            giveFormItem(player);
        });
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        cooldown.remove(event.getPlayer().getName());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Item item = player.getInventory().getItemInHand();
        if (!itemStr.equals(item)) {
            return;
        }
        int tick = nkServer.getTick();
        Integer cooldownTick = cooldown.get(player.getName());
        if (cooldownTick != null && tick - cooldown.get(player.getName()) < 20) {
            return;
        }
        openMenu(player, new String[]{"main"}, player);
        cooldown.put(player.getName(), tick);
    }
}
