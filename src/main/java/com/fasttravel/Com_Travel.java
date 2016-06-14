/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author janschon
 */
public class Com_Travel implements CommandExecutor{
    /**
     *
     * @param sender
     * @param com
     * @param arg0
     * @param arg1
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command com, String arg0, String[] arg1) {
        Player player;
        if(sender instanceof Player){
            player = (Player) sender;
        }
        return false;
    }
}
