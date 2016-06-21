/*
 * Copyright (C) 2016  Jan Schoneberg
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either version
 * 3 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */
package com.fasttravel.commands;

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
        StorePoints.getInstance().addArea(strings[0].trim(), player.getLocation().getBlockX(),player.getLocation().getBlockY(),player.getLocation().getBlockZ());
        player.sendMessage("Succesfully created new area!");
        return true;
    }
    
}
