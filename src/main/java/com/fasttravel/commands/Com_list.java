/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.commands;

import com.fasttravel.db.Area;
import com.fasttravel.db.AreaDB;
import com.fasttravel.db.PlayerDB;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author janschon
 */
public class Com_list implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player player;
        List<Area> a;
        List<Area> all = AreaDB.getInstance().get_all_areas();
        if(cs instanceof Player){
            player = (Player) cs;
        } else {
            return false;
        }
        if(strings == null || strings.length < 1){
            if(all.isEmpty()){
                player.sendMessage("No areas available");
            }else{
                player.sendMessage(list_to_string(all));
            }
        } else {
            if(strings[0].equals("disc")){
                a = PlayerDB.getInstance().getUserByPlayer(player).areas_discovered;
                if(a.size() < 1){
                    player.sendMessage("You no discovered areas!");
                } else {
                    player.sendMessage(list_to_string(a));
                }
                
            }else if(strings[0].equals("nondisc")){
                a = PlayerDB.getInstance().getUserByPlayer(player).areas_not_discovered;
                if((a.size() < 1) && !(all.size() < 1)){
                    player.sendMessage("There are no areas to discover!");
                } else {
                    player.sendMessage(list_to_string(a));
                }
            } else {
                // Need some red color
                player.sendMessage("/ft_list " + strings[0] + " is not a known command!");
            }
        }
        return true;
    }
    
    private String list_to_string(List<Area> a){
        String ret = new String();
        for(Area e:a){
            ret += e.name + " ";
        }
        return ret;
    }
    
}
