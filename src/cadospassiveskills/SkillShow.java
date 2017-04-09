package cadospassiveskills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class SkillShow {

    public double attribute;
    public String level,caption;

    public String result(){
        String result = "\nPassive skill info: \n" +
                "1. " + this.caption + ": " + this.attribute + "\n" +
                "2. Level: " + this.level + "\n" +
                "Upgrade Cost: " + 10 * (Integer.parseInt(this.level)+1) + " player level";
        return result;
    }

    public String show(LinkedList e, Player p, String option){
        String result = "\nYou don't have passive skills";
        if(!e.contains(p.getName())) return result;
        int index = e.indexOf(p.getName());
        switch (option){
            case "hp":
                result = hp(e,(Player) p,index+6);
                break;
            case "dmg":
                result = dmg(e,(Player) p,index+7);
                break;
            case "def":
                result = def(e,(Player) p,index+8);
                break;
            case "as":
                result = as(e,(Player) p,index+9);
                break;
            case "ms":
                result = ms(e,(Player) p,index+10);
                break;
            default:
                result = "\nusage: /cps show <hp|dmg|def|as|ms>";
                break;
        }
        return result;
    }

    public String hp(LinkedList e, Player p, int index){
        this.attribute = p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        this.level = (String) e.get(index);
        this.caption = "Max Health";
        return this.result();
    }

    public String dmg(LinkedList e, Player p, int index){
        this.attribute = p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        this.level = (String) e.get(index);
        this.caption = "Base Damage";
        return this.result();
    }

    public String def(LinkedList e, Player p, int index){
        this.attribute = p.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue();
        this.level = (String) e.get(index);
        this.caption = "Base Defense";
        return this.result();
    }

    public String as(LinkedList e, Player p, int index){
        this.attribute = p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue();
        this.level = (String) e.get(index);
        this.caption = "Attack Speed";
        return this.result();
    }

    public String ms(LinkedList e, Player p, int index){
        this.attribute = p.getWalkSpeed();
        this.level = (String) e.get(index);
        this.caption = "Move Speed";
        return this.result();
    }
}
