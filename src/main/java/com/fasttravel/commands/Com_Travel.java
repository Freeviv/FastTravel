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

import org.bukkit.Bukkit;
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
     * Implements the Command "/ft" thich allows the player to travel to different
     * places on the server
     * @param sender CommandSender (should be a human player)
     * @param com Command
     * @param arg0
     * @param arg1 List of arguments, only first entry will be recognized
     * @return true if the player was teleported else false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command com, String arg0, String[] arg1) {
        Player player;
        if(sender instanceof Player){
            player = (Player) sender;
        } else {
            return false;
        }
        /*
        if(arg1.length < 1){
            player.sendMessage("You have to specify the area!");
            return false;
        }
        Area to_tp = AreaDB.getInstance().get_area_in_db(arg1[1]);
        if(to_tp == null){
            player.sendMessage("There is no area named " + arg1[1] + "!");
            return false;
        }
        if(PlayerDB.getInstance().getUserByPlayer(player).areas_discovered.contains(to_tp)){
            player.teleport(to_tp.loc);
        } else {
            player.sendMessage("You have not discovered " + arg1[0] + " yet!");
        }
        
        //player.teleport();
        Bukkit.broadcastMessage(Bukkit.getWorlds().toString());
*/
        return false;
    }
}
