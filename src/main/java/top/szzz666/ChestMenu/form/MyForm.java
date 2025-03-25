package top.szzz666.ChestMenu.form;

import cn.nukkit.Player;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementInput;
import top.szzz666.ChestMenu.form.easy_form.Custom;
import top.szzz666.ChestMenu.form.easy_form.Simple;

import java.util.Arrays;

import static top.szzz666.ChestMenu.form.easy_form.Modal.tipsModal;


public class MyForm {
    public static void mainForm(Player player) {
        Simple form = new Simple("MyForm", "This is a simple form.", true);
        form.add("Button1", () -> player.sendMessage("Button1 clicked!"));
        form.add("Button2", () -> player.sendMessage("Button2 clicked!"));
        form.add("Button3", "textures/items/wood_axe.png", () -> player.sendMessage("Button3 clicked!"));
        form.add("Button4", "https://avatars.githubusercontent.com/u/60167194?v=4", () -> player.sendMessage("Button4 clicked!"));
        form.show(player);
    }

    public static void Form(Player player) {
        Custom form = new Custom("MyForm", true);
        form.add("text", new ElementInput("Text", "Enter text here"));
        form.add("下拉框", new ElementDropdown("下拉框", Arrays.asList("选项1", "选项2", "选项3")));
        form.setSubmit(() -> {
            String text = form.getRes("text");
            player.sendMessage("Text entered: " + text);
            String dropdown = form.getRes("下拉框");
            player.sendMessage("Dropdown selected: " + dropdown);
            tipsModal(player, "Form submitted!", form.getForm());
        });
        form.show(player);
    }


}
