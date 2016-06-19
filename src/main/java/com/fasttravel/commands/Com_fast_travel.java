package com.fasttravel.commands;

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



import com.fasttravel.db.Area;
import com.fasttravel.db.StorePoints;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author janschon
 */
public class Com_fast_travel implements CommandExecutor{
    
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
            sender.sendMessage("This command can only be executed by a player!");
            return false;
        }
        if(arg1[0].trim().isEmpty()){
            return false;
        }
        if(!StorePoints.getInstance().getAllNames().contains(arg1[0])){
            return false;
        }
        Area a = StorePoints.getInstance().getAreaByName(arg1[0]);
        if(a == null){
            return false;
        }
        if(!a.getAllPlayer().contains(player.getUniqueId().toString())){
            player.sendMessage("You have not discovered this point yet!");
            return false;
        }
        waitTillArrival(player);
        player.teleport(new Location(player.getLocation().getWorld(), a.getX(), a.getY(), a.getZ()));
        return true;
    }
    
    private void waitTillArrival(Player p){
        
    }
}
