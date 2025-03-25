package top.szzz666.ChestMenu.panel.esay_chest_menu;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.inventory.InventoryTransactionEvent;
import cn.nukkit.inventory.transaction.action.SlotChangeAction;
import top.szzz666.ChestMenu.panel.esay_chest_menu.lib.ChestFakeInventory;

import static top.szzz666.ChestMenu.tools.taskUtil.Async;

public class CMListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryTransaction(InventoryTransactionEvent event) {
        event.getTransaction().getActions().forEach(action -> {
            if (action instanceof SlotChangeAction slotChange) {
                if (slotChange.getInventory() instanceof ChestFakeInventory inventory) {
                    inventory.setEvent(event);
                    int slot = slotChange.getSlot();
                    if (inventory.getSlots().contains(slot)) {
                        if (inventory.isAsync()) {
                            Async(() -> inventory.getRs().get(slot).run());
                        } else {
                            inventory.getRs().get(slot).run();
                        }
                        if (inventory.isAutoClose()) {
                            inventory.close(inventory.getPlayer());
                        }
                    }
                    event.setCancelled(true);
                }
            }
        });
    }
}
