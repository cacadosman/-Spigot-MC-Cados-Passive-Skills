package cadospassiveskills;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.*;
import java.util.LinkedList;
import java.util.Objects;

public class DataManager{

    public double health = 20.0;
    public double damage = 1.0;
    public double defense = 0.0;
    public double attackspeed = 4.0;
    public float movespeed = 0.2f;

    public LinkedList data = new LinkedList();

    public String FilePath = "plugins/CadosPassiveSkills.txt";

    public DataManager(){
        if(new File(FilePath).exists()){
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(FilePath), "UTF-16");
                BufferedReader bufReader = new BufferedReader(reader);
                String line;
                while ((line = bufReader.readLine()) != null) {
                    data.add(line);
                }
                reader.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }else{
            try {
                PrintWriter writer = new PrintWriter(FilePath, "UTF-16");
                writer.println("[Cados Passive Skills]");
                writer.close();

                InputStreamReader reader = new InputStreamReader(new FileInputStream(FilePath), "UTF-16");
                BufferedReader bufReader = new BufferedReader(reader);
                String line;
                while ((line = bufReader.readLine()) != null) {
                    data.add(line);
                }
                reader.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public void save(LinkedList list){
        try {
            PrintWriter writer = new PrintWriter(FilePath, "UTF-16");
            for(Object ll:list){
                writer.println(Objects.toString(ll));
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void addData(Player player){
        String username = player.getDisplayName();
        double health = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
        double damage = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getBaseValue();
        double defense = player.getAttribute(Attribute.GENERIC_ARMOR).getBaseValue();
        double attackspeed = player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getBaseValue();
        float movespeed = player.getWalkSpeed();
        int lvlHealth = 0; int lvlDamage = 0; int lvlDefense =0; int lvlAs = 0; int lvlMs = 0;
        data.add(username);data.add(Double.toString(health));
        data.add(Double.toString(damage));data.add(Double.toString(defense));
        data.add(Double.toString(attackspeed));data.add(Float.toString(movespeed));
        data.add(Integer.toString(lvlHealth));data.add(Integer.toString(lvlDamage));
        data.add(Integer.toString(lvlDefense));
        data.add(Integer.toString(lvlAs));data.add(Integer.toString(lvlMs));

        try {
            PrintWriter writer = new PrintWriter(FilePath, "UTF-16");
            for(Object ll:data){
                writer.println(Objects.toString(ll));
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void statJoin(PlayerJoinEvent p){
        Player player = p.getPlayer();
        if(data.contains(player.getName())){
            int index = data.indexOf(player.getName());
            String health = (String) data.get(index+1);
            String damage = (String) data.get(index+2);
            String defense = (String) data.get(index+3);
            String attack_speed = (String) data.get(index+4);
            String move_speed = (String) data.get(index+5);
            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Double.parseDouble(health));
            player.setHealth(Double.parseDouble(health));
            player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(Double.parseDouble(damage));
            player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(Double.parseDouble(defense));
            player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(Double.parseDouble(attack_speed));
            player.setWalkSpeed(Float.parseFloat(move_speed));
        }else{
            addData((Player) player);
        }
    }

    public void statQuit(PlayerQuitEvent p){
        Player player = p.getPlayer();
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.health);
        player.setHealth(this.health);
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(this.damage);
        player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(this.defense);
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(this.attackspeed);
        player.setWalkSpeed(this.movespeed);
    }

}
