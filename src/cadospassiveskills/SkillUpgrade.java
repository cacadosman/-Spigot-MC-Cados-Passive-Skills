package cadospassiveskills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.util.LinkedList;

public class SkillUpgrade {

    public DataManager DM = new DataManager();

    public String result(){
        return "Skill has been upgraded";
    }

    public String upgrade(LinkedList e, Player p, String option){
        String result = "\nYou don't have passive skills";
        if(!e.contains(p.getName())) return result;
        int index = e.indexOf(p.getName());
        switch (option){
            case "hp":
                result = hp(e, (Player) p, index);
                break;
            case "dmg":
                result = dmg(e, (Player) p, index);
                break;
            case "def":
                result = def(e, (Player) p, index);
                break;
            case "as":
                result = as(e, (Player) p, index);
                break;
            case "ms":
                result = ms(e, (Player) p, index);
                break;
            default:
                result = "\nusage: /cps upgrade <hp | dmg | def | as | ms>";
                break;
        }
        return result;
    }

    public boolean validate(int level, int user_level){
        boolean status = false;
        if(user_level > (level+1)*10) status = true;
        return status;
    }

    public int level(LinkedList e, Player p, int index){
        String level = (String) e.get(index);
        int level_int = Integer.parseInt(level);
        return level_int;
    }

    public String hp(LinkedList e, Player p, int index){
        int user_level = p.getLevel();
        int level_int = level(e, (Player) p, index+6);
        double health_new = DM.health+(DM.health*(((level_int+1)*20.0)/100));
        if(!validate(level_int,user_level)) return "You don't meet level requirement";
        if(level_int >= 5) return "Max Level";
        p.setLevel(user_level - 10*(level_int+1));
        p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health_new);
        p.setHealth(health_new);
        e.set(index+1,Double.toString(p.getHealth()));
        e.set(index+6,Integer.toString(level_int+1));
        DM.save(e);
        return this.result();
    }

    public String dmg(LinkedList e, Player p, int index){
        int user_level = p.getLevel();
        int level_int = level(e, (Player) p, index+7);
        double damage_new = DM.damage+level_int;
        if(!validate(level_int,user_level)) return "You don't meet level requirement";
        if(level_int >= 5) return "Max Level";
        p.setLevel(user_level - 10*(level_int+2));
        p.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(damage_new);
        e.set(index+2,Double.toString(damage_new));
        e.set(index+7,Integer.toString(level_int+1));
        DM.save(e);
        return this.result();
    }

    public String def(LinkedList e, Player p, int index){
        int user_level = p.getLevel();
        int level_int = level(e, (Player) p, index+8);
        double defense_new = DM.defense+(level_int*4);
        if(!validate(level_int,user_level)) return "You don't meet level requirement";
        if(level_int >= 5) return "Max Level";
        p.setLevel(user_level - 10*(level_int+2));
        p.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(defense_new);
        e.set(index+3,Double.toString(defense_new));
        e.set(index+8,Integer.toString(level_int+1));
        DM.save(e);
        return this.result();
    }

    public String as(LinkedList e, Player p, int index){
        int user_level = p.getLevel();
        int level_int = level(e, (Player) p, index+9);
        double as_new = DM.attackspeed+(DM.attackspeed*(((level_int+1)*7.5)/100));
        if(!validate(level_int,user_level)) return "You don't meet level requirement";
        if(level_int >= 5) return "Max Level";
        p.setLevel(user_level - 10*(level_int+2));
        p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(as_new);
        e.set(index+4,Double.toString(as_new));
        e.set(index+9,Integer.toString(level_int+1));
        DM.save(e);
        return this.result();
    }

    public String ms(LinkedList e, Player p, int index){
        int user_level = p.getLevel();
        int level_int = level(e, (Player) p, index+10);
        float ms_new = DM.movespeed+((DM.movespeed*(level_int+1))/8);
        if(!validate(level_int,user_level)) return "You don't meet level requirement";
        if(level_int >= 5) return "Max Level";
        p.setLevel(user_level - 10*(level_int+2));
        p.setWalkSpeed(ms_new);
        e.set(index+5,Double.toString(ms_new));
        e.set(index+10,Integer.toString(level_int+1));
        DM.save(e);
        return this.result();
    }

}
