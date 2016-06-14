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
