package top.szzz666.ChestMenu.entity;

import cn.nukkit.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemStr {
    private int id;
    private int damage;
    private int count;
    private int pos;
    private String name;
    public ItemStr(String item){
        String[] itemArr = item.split(":");
        id = Integer.parseInt(itemArr[0]);
        damage = Integer.parseInt(itemArr[1]);
        count = Integer.parseInt(itemArr[2]);
        pos = Integer.parseInt(itemArr[3]);
        name = itemArr[4];
    }
    public Item toItem(){
        Item item = Item.get(id, damage, count);
        item.setCustomName(name);
        return item;
    }
    public boolean equals(Item item){
        return id == item.getId() && damage == item.getDamage() && name.equals(item.getCustomName());
    }
}
