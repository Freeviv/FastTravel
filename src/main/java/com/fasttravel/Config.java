/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;

import org.bukkit.configuration.file.FileConfiguration;

/**
 *
 * @author janschon
 */
public class Config {
    boolean check_for_updates;
    
    public Config(FileConfiguration conf){
        if(!conf.getBoolean("init")){
            System.out.println("[FastTravel] No Config file found! Creating...");
            init_config(conf);
        }
        conf.options().copyDefaults(true);
        
    }
    
    private boolean init_config(FileConfiguration c){
        c.addDefault("init", true);
        c.addDefault("check_for_updates", true);
        c.addDefault("travel_time_factor", (double)0.5);
        return true;
    }
}
