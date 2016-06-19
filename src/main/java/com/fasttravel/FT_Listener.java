/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        //if(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE)){
        //    return;
        //}
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
                }
            }
        }
    }
    
        public static void refreshAreas(){
        area = StorePoints.getInstance().getAllAreas();
    }
}
