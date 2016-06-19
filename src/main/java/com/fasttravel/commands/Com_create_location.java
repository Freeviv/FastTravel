package com.fasttravel.commands;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.fasttravel.db.StorePoints;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author janschon
 */
public class Com_create_location implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        Player player;
        if(cs instanceof Player){
            player = (Player) cs;
        } else {
            cs.sendMessage("This command should be executed by a player!");
            return false;
        }
        if(strings.length != 1){
            // TODO Better help msg
            player.sendMessage("This command expects 1 argument!");
            return false;
        }
        if(StorePoints.getInstance().getAllNames().contains(strings[0])){
            player.sendMessage("The given name already exists!");
            return false;
        }
        StorePoints.getInstance().addArea(strings[0], player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
        player.sendMessage("Succesfully created new area!");
        return true;
    }
    
}
