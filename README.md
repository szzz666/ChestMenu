### nukkit上的箱子菜单插件

#### 配置文件
```yml
#  (时钟)物品id:物品特殊值:物品数量:物品槽位:物品名称
item: 347:0:1:8:星空菜单
# 打开菜单命令
command: openmenu
# 清空背包命令，但是不包括菜单
clearInventoryCommand: cleargfi
```
json菜单文件，如menus/main.json
```json5
{
  "title": "测试菜单",
  "type": 0,//  0:普通箱子菜单 1:大箱子菜单
  "buttons": {
    //"槽位:物品id:物品特殊值:物品数量:物品名称": ["命令"],
    "0:1:0:1:test": ["test"],
    "1:1:1:1:test1": ["test1"],
    "2:1:2:1:test2": ["test2"],
    "3:1:3:1:test3": ["test3"]
  }
}
```
通过`/openmenu 文件名`命令打开对应菜单（文件名不包含.json后缀）

本插件可以作为EsayChestMenuApi使用，以下是示例代码：
```java
public static void test1(Player player) {
    BigChestMenu menu = new BigChestMenu("test", true, true);
    menu.add(0, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 1"));
    menu.add(1, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 2"));
    menu.add(2, Item.get(Item.STONE_SWORD), () -> player.sendMessage("clicked 3"));
    menu.show(player);
}
```