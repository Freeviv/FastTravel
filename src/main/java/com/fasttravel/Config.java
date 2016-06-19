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
    private double travel_time_factor;
    private String name;
    private int adr;
    
    private static Config instance;
    
    /**
     * Constructor for the Config-Object
     * This should enable a faster access to the different settings
     * @param conf ConfigurationFile from the main-class
     */
    private Config(FileConfiguration conf){
        if(!conf.getBoolean("init")){
            System.out.println("[FastTravel] No Config file found! Creating...");
            init_config(conf);
        }
        conf.options().copyDefaults(true);
        load_config(conf);
    }
    
    public static Config getInstance(FileConfiguration c){
        if(instance == null && c != null){
            instance = new Config(c);
        }
        return instance;
    }

    
    /**
     * Initialize the FileConfiguration
     * @param c FileConfiguration wich should be initialized
     * @return true if everythings initialized
     */
    private boolean init_config(FileConfiguration c){
        c.addDefault("init", true);
        c.addDefault("travel_time_factor", (double)0.5);
        c.addDefault("file_name", "areas");
        c.addDefault("area_discover_radius", (int)10);
        return true;
    }
    
    /**
     * Load the different settings from FileConfiguration
     * @param c FileConfiguration from wich the settings will be loaded from
     * @return true if everything is loaded or false if not
     */
    private boolean load_config(FileConfiguration c){
        travel_time_factor = c.getDouble("travel_time_factor");
        name = c.getString("file_name");
        adr = c.getInt("area_discover_radius");
        return false;
    }
    
    public String getFileName(){
        return name;
    }
    
    public int getAreaDiscoverRadius(){
        return adr;
    }
}
