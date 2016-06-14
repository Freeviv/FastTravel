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

import com.fasttravel.commands.Com_Travel;
import com.fasttravel.commands.Com_create_location;
import com.fasttravel.commands.Com_specify_ft_point;
import com.mongodb.MongoClient;
import org.bukkit.plugin.java.JavaPlugin;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author janschon
 */
public class FastTravel extends JavaPlugin {

    private Config conf;
    private MongoClient mc;
    private Morphia morphia;
    
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
        // Register all new Commands
        this.getCommand("ft").setExecutor(new Com_Travel());
        this.getCommand("create_location").setExecutor(new Com_create_location());
        this.getCommand("specify_ft_point").setExecutor(new Com_specify_ft_point());
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {
        
    }
    
}
