/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author janschon
 */
public class Com_specify_ft_point implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player player;
        if(cs instanceof Player){
            player = (Player) cs;
        } else {
            return false;
        }
        if(strings.length < 1){
            player.sendMessage("The name of the area is needed!");
            return false;
        }
        // TODO
        //Area area = AreaDB.getInstance().get_area_in_db(strings[0]);
        /*
        if(area == null){
            player.sendMessage("No area named " + strings[0] + " in the database!");
            return false;
        }
        if(!area.player_in_area(player)){
            player.sendMessage("You have to be inside of the area!");
            return false;
        }
        area.loc = player.getLocation();
        player.sendMessage("Successfully change spawn point for the area: " + area.name);
        */
        return true;
    }
    
}
