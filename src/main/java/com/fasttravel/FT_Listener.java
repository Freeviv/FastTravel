/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;
import com.fasttravel.db.Area;
import com.fasttravel.db.AreaDB;
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
    
    /**
     * Eventlistener for Playerjoining
     * @param event 
     */
    @EventHandler
     public void onPlayerJoin(PlayerJoinEvent event)
     {
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
     }
}
