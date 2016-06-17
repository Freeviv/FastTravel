package com.fasttravel.commands;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
            return false;
        }
        if(strings.length != 7){
            // TODO Better help msg
            player.sendMessage("This command expects 7 arguments!");
            return false;
        }
        int[] coords = new int[6];
        try{
            for(int i = 1; i < 7; i++){
                coords[i - 1] = Integer.parseInt(strings[i]);
            }
        } catch (NumberFormatException e){
            player.sendMessage("An error accured during processing this command (NumberFormatException)");
            return false;
        }
        /*
        Area a = new Area();
        a.set_area_dimensions(coords[0], coords[1], coords[2], coords[3], coords[4], coords[5]);
        a.name = strings[0];
        AreaDB.getInstance().add_area_to_db(a);
        PlayerDB.getInstance().add_area_to_all_player(a);
        */
        player.sendMessage("Succesfully created new area!");
        return true;
    }
    
}
