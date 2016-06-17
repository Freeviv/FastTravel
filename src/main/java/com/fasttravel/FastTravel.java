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

import com.fasttravel.commands.Com_fast_travel;
import com.fasttravel.commands.Com_create_location;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Mainclass of this Plugin
 * @author janschon
 */
public class FastTravel extends JavaPlugin {

    private Config conf;

    /**
     * Fired when plugin is first enabled
     */
    @Override
    public void onEnable() {
        conf = new Config(this.getConfig());
        saveConfig();
        
        System.out.println("[FastTravel] Welcome to FastTravel!");
        StorePoints.getInstance();
        //=========================================
        // Register all new Commands
        this.getCommand("ft").setExecutor(new Com_fast_travel());
        this.getCommand("create_location").setExecutor(new Com_create_location());
        
        getServer().getPluginManager().registerEvents(new FT_Listener(), this);
    }
    /**
     * Fired when plugin is disabled
     */
    @Override
    public void onDisable() {
        HandlerList.unregisterAll();
    }
    
}
