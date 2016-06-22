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

import com.fasttravel.Config;
import com.fasttravel.Utils;
import com.fasttravel.db.Area;
import com.fasttravel.db.StorePoints;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * CommandExecutor for the /ft command
 * @author Jan Schoneberg(Freeviv)
 */
public class Com_fast_travel implements CommandExecutor{
    
    public static final double BLOCKS_PER_SECOND = 5;
    
    private static Plugin pl;
    
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
        if(arg1.length < 1){
             showPlayerAllPoints(player);
             return true;
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
        teleportPlayer(player, a);
        
        
        return true;
    }
    
    public static void teleportPlayer(Player p, Area a){
        GameMode g = p.getGameMode();
        
        Location loc0 = new Location(p.getWorld(), a.getX(), a.getY(), a.getZ());
        double dis = Utils.getDistance(loc0, p.getLocation());
        double travel_time = Config.travel_time_factor * (dis/BLOCKS_PER_SECOND);
        int travel_time_in_sec = (int)Math.ceil(travel_time);
        p.sendMessage("You will arrive " + a.getName() + " in " + String.valueOf(travel_time_in_sec) + " seconds.");
        
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20*travel_time_in_sec, -100));
        p.setGameMode(GameMode.SPECTATOR);
        p.teleport(new Location(p.getWorld(), p.getLocation().getBlockX(), 10000, p.getLocation().getBlockX()));
        new BukkitRunnable() {
            @Override
            public void run() {
                p.teleport(new Location(p.getLocation().getWorld(), a.getX(), a.getY(), a.getZ()));
                p.setGameMode(g);
            }
        }.runTaskLater(pl, 20*travel_time_in_sec);
        p.playSound(p.getLocation(), Sound.BLOCK_GRAVEL_STEP, (float) 0.3, 1);
    }
    
    private void showPlayerAllPoints(Player p){
         List<Area> a = StorePoints.getInstance().getAllAreas();
         int inv_cols = (int) Math.ceil((double)a.size()/9); 
         ArrayList<String> names = new ArrayList<>();
         names.add("Server");
         Inventory inv = Bukkit.createInventory(null, inv_cols * 9,"FastTravel Points");
         for(Area area:a){
            ItemStack b = new ItemStack(Material.MAP);
            ItemMeta meta = b.getItemMeta();
            if(area.getAllPlayer().contains(p.getUniqueId().toString())){
                meta.setDisplayName(ChatColor.GREEN + area.getName());
            }else {
                meta.setDisplayName(ChatColor.RED + area.getName());
            }
            meta.setLore(names);
            b.setItemMeta(meta);
            inv.addItem(b);
         }
         //inv.
         
         p.openInventory(inv);
     }
}
