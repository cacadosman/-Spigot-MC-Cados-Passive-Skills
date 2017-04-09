//Minecraft Plugin (Cados Passive Skills, MC Spigot 1.11.2)

package cadospassiveskills;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    //Passive Skills : Health, Damage, Defense, Attack Speed, Movement Speed,

    public DataManager DM = new DataManager();
    public SkillList SL = new SkillList();
    public SkillShow SS = new SkillShow();
    public SkillUpgrade SU = new SkillUpgrade();

    public void onEnable(){
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(cmd.getName().equalsIgnoreCase("cps")){
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("show")){
                    if(args.length > 1){
                        String skill_show = SS.show(DM.data, (Player) sender, args[1]);
                        sender.sendMessage(skill_show);
                    }else{
                        sender.sendMessage("\nusage: /cps show <skill>");
                    }
                }else if(args[0].equalsIgnoreCase("upgrade")){
                    if(args.length > 1){
                        String skill_upgrade = SU.upgrade(DM.data, (Player) sender, args[1]);
                        sender.sendMessage(skill_upgrade);
                    }else{
                        sender.sendMessage("\nusage: /cps upgrade <skill>");
                    }
                }else if(args[0].equalsIgnoreCase("list")){
                    String skill_list = SL.list(DM.data, (Player) sender);
                    sender.sendMessage(skill_list);
                }else{
                    sender.sendMessage("u\nsage: /help CadosPS");
                }
            }else{
                sender.sendMessage("\nusage: /help CadosPS");
            }
        }
        return false;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        DM.statQuit((PlayerQuitEvent) e);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        DM.statJoin((PlayerJoinEvent) e);
    }

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent e){

    }

}