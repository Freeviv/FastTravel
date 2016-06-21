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
package com.fasttravel;

import com.fasttravel.db.Area;
import com.fasttravel.db.StorePoints;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 *
 * @author janschon
 */
public class FT_Listener implements Listener{
    
    private static List<Area> area = StorePoints.getInstance().getAllAreas();
    
    /**
     * Eventlistener for Playerjoining
     * @param event 
     */
    @EventHandler
     public void onPlayerJoin(PlayerJoinEvent event)
     {
         /*
         if(areas == null){
             areas = AreaDB.getInstance().get_all_areas();
         }
         Player evPlayer = event.getPlayer();
         // This will ensure that the player exists in the DB
         User a = PlayerDB.getInstance().getUserByPlayer(evPlayer);
         if(evPlayer.isOp()){
             if(Utils.check_for_update()){
                 evPlayer.sendMessage("There is a new Update available for FastTravel!");
             }
         }
*/
     }
     
     @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Aviod too many checks
        if(player.getGameMode().equals(GameMode.SPECTATOR)){
            return;
        }
        // Only fire if the player changed position
        Location from = event.getFrom();
        Location to = event.getTo();
        if((from.getBlockX() == to.getBlockX()) && (from.getBlockY() == to.getBlockY()) && (from.getBlockZ() == to.getBlockZ())){
            return;
        }
        // Config file needs some fixes
        int r = 10;
        for(Area a:area){
            int ax = a.getX();
            int ay = a.getY();
            int az = a.getZ();
            if((ax-r < to.getBlockX()) && (ax+r > to.getBlockX()) &&
                    (ay-r < to.getBlockY()) && (ay+r > to.getBlockY()) &&
                    (az-r < to.getBlockZ()) && (az+r > to.getBlockZ())){
                if(!a.getAllPlayer().contains(player.getUniqueId().toString())){
                    player.sendMessage("You just discovered " + a.getName() + "!");
                    a.addPlayer(player.getUniqueId().toString());
                    StorePoints.getInstance().writeAll();
                    Utils.fireRocket(player);
                }
            }
        }
    }
    
        public static void refreshAreas(){
        area = StorePoints.getInstance().getAllAreas();
    }
}
