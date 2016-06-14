/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;

import com.fasttravel.commands.Com_Travel;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author janschon
 */
public class FastTravel extends JavaPlugin {

    private Config conf;
    
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
        conf = new Config(this.getConfig());
        saveConfig();
        
        boolean updateAvailable = false;
        System.out.println("[FastTravel] Welcome to FastTravel!");
        System.out.println("[FastTravel] Checking for updates...");
        if(updateAvailable){
            System.out.println("[FastTravel] New update available!");
        } else {
            System.out.println("[FastTravel] No update available");
        }
        
        //=========================================
        
        this.getCommand("ft").setExecutor(new Com_Travel());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        
    }
    
}
