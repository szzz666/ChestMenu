package top.szzz666.ChestMenu.entity;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String title;
    private int type;
    private LinkedHashMap<String, ArrayList<String>> buttons;

    public String toJson() {
        return new Gson().toJson(this);
    }


}
