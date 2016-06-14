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
    private boolean check_for_updates;
    private double travel_time_factor;
    private boolean do_backups;
    private int backup_cycle;
    
    public Config(FileConfiguration conf){
        if(!conf.getBoolean("init")){
            System.out.println("[FastTravel] No Config file found! Creating...");
            init_config(conf);
        }
        conf.options().copyDefaults(true);
        load_config(conf);
    }
    
    private boolean init_config(FileConfiguration c){
        c.addDefault("init", true);
        c.addDefault("check_for_updates", true);
        c.addDefault("travel_time_factor", (double)0.5);
        c.addDefault("do_backups", true);
        // In sec
        c.addDefault("backup_cycle", true);
        return true;
    }
    
    private boolean load_config(FileConfiguration c){
        check_for_updates = c.getBoolean("check_for_updates");
        do_backups = c.getBoolean("do_backups");
        return false;
    }
}
