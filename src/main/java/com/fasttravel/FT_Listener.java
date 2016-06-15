/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;
import com.fasttravel.db.Area;
import com.fasttravel.db.PlayerDB;
import com.fasttravel.db.User;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerEvent;


/**
 *
 * @author janschon
 */
public class FT_Listener implements Listener{
    
    private static List<Area> areas;
    
    @EventHandler
     public void onPlayerJoin(PlayerJoinEvent event)
     {
         Player evPlayer = event.getPlayer();
         // This will ensure that the player exists in the DB
         User a = PlayerDB.getInstance().getUserByPlayer(evPlayer);
         if(evPlayer.isOp()){
             if(Utils.check_for_update()){
                 evPlayer.sendMessage("There is a new Update available for FastTravel!");
             }
         }
         // Sync available areas with the player modell
         if(!areas_sync(a)){
             sync_player_areas(a);
         }
     }
     
     @EventHandler
     public void onListenerStart(ServerEvent e){
         
     }
     
     private boolean areas_sync(User u){
         if(areas.size() == (u.areas_discovered.size() + u.areas_not_discovered.size())){
             return false;
         }
         if(areas.containsAll(u.areas_discovered) && areas.containsAll(u.areas_not_discovered)){
             return true;
         } else {
             return false;
        }
     }
     
     private void sync_player_areas(User u){
         List<Area> a = areas;
         a.removeAll(u.areas_discovered);
         a.removeAll(u.areas_not_discovered);
         if(!a.isEmpty()){
             u.areas_not_discovered.addAll(a);
         }
     }
}
