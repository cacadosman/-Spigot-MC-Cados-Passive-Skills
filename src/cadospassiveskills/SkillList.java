package cadospassiveskills;

import org.bukkit.entity.Player;

import java.util.LinkedList;


public class SkillList {
    String str = "\nYou don't have passive skills";

    public String list(LinkedList e, Player p){
        if(e.contains(p.getName())){
            int index = e.indexOf(p.getName());
            String lvl_health = (String) e.get(index+6);
            String lvl_damage = (String) e.get(index+7);
            String lvl_defense = (String) e.get(index+8);
            String lvl_as = (String) e.get(index+9);
            String lvl_ms = (String) e.get(index+10);
            this.str = "\nPassive Skill List:\n" +
                         "1. Health (hp) = Level " + lvl_health + " (+" + 20 * Integer.parseInt(lvl_health) + "%)\n" +
                         "2. Damage (dmg) = Level " + lvl_damage + " (+" + Integer.parseInt(lvl_damage) + " dmg)\n" +
                         "3. Defense (def) = Level " + lvl_defense + " (+" + 4 * Integer.parseInt(lvl_defense) + " def)\n" +
                         "4. Attack Speed (as) = Level " + lvl_as + " (+" + 7.5 * Double.parseDouble(lvl_as) + "%)\n" +
                         "5. Movement Speed (ms) = Level " + lvl_ms + " (+" + 25 * Integer.parseInt(lvl_ms) + "%)";
        }
        return this.str;
    }
}
