/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;

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
        if(player.getGameMode().equals(GameMode.SPECTATOR) || player.getGameMode().equals(GameMode.CREATIVE)){
            return;
        }
        // Only fire if the player changed position
        Location from = event.getFrom();
        Location to = event.getTo();
        if((from.getBlockX() == to.getBlockX()) && (from.getBlockY() == to.getBlockY()) && (from.getBlockZ() == to.getBlockZ())){
            return;
        }/*
        User user = PlayerDB.getInstance().getUserByPlayer(player);
        List<Area> area = user.areas_not_discovered;
        if(!area.isEmpty()){
            for(Area a:area){
                if(a.player_in_area(player)){
                    user.areas_discovered.add(a);
                    user.areas_not_discovered.remove(a);
                    player.sendMessage("You just discovered " + a.name + "!");
                    player.sendMessage("A new fast travel point is now available.");
                }
            }
        }*/
    }
}
