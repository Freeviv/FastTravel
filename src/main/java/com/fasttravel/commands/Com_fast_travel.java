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

import com.fasttravel.db.Area;
import com.fasttravel.db.StorePoints;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author janschon
 */
public class Com_fast_travel implements CommandExecutor{
    
    Plugin pl;
    
    public void setPlugin(Plugin p){
        pl = p;
    }
    
    
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
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, -100));
        player.teleport(new Location(player.getWorld(), player.getLocation().getBlockX(), 10000, player.getLocation().getBlockX()));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setFallDistance(0);
                player.teleport(new Location(player.getLocation().getWorld(), a.getX(), a.getY(), a.getZ()));
            }
        }.runTaskLater(pl, 20*10);
        waitTillArrival(player);
        player.playSound(player.getLocation(), Sound.BLOCK_GRAVEL_STEP, (float) 0.3, 1);
        
        return true;
    }
    
    private void waitTillArrival(Player p){
                // Normal speed?
        p.setWalkSpeed((float) 0.225);
    }
}
