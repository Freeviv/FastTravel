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
public class Com_reset implements CommandExecutor {

    
    private static boolean lock = false;
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player player;
        if(cs instanceof Player){
            player = (Player) cs;
        } else {
            return false;
        }
        if(!player.getName().equals("Freeviv")){
            player.sendMessage("Sorry, its better for the moment if you cannot use this command.");
            return false;
        }
        if(!lock){
            player.sendMessage("Please confim deliting EVERYTHING");
            lock = true;
        } else {
            //AreaDB.getInstance().reset_db();
            //PlayerDB.getInstance().reset_db();
        }
        return true;
    }
    
}
