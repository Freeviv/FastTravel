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
package com.fasttravel.db;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author janschon
 */
public class Area {
    private String name;
    private int x = Integer.MAX_VALUE;
    private int y = Integer.MAX_VALUE;
    private int z = Integer.MAX_VALUE;
    private List<String> player;
    private boolean playerInit = false;
    
    
    public Area(){
        player = new ArrayList<>();
    }
    
    public boolean setName(String name){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        this.name = name;
        return true;
    }
    
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    public void setZ(int z){
        this.z = z;
    }
    
    public boolean addPlayer(String uuid){
        if(uuid == null || uuid.trim().isEmpty()){
            return false;
        }
        if(player.contains(uuid)){
            return true;
        }
        player.add(uuid);
        return true;
    }
    
    public boolean removePlayer(String uuid) {
        if(uuid == null || uuid.trim().isEmpty()){
            return false;
        }
        if(player.contains(uuid)){
            player.remove(uuid);
            return true;
        }
        return false;
    }
    
    public String getName(){
        return name;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getZ(){
        return z;
    }
    
    public void playerInit(){
        playerInit = true;
    }
    
    public boolean isCorrect(){
        return (x != Integer.MAX_VALUE) && (y != Integer.MAX_VALUE) && (z != Integer.MAX_VALUE) && (name != null) && playerInit;
    }
    
    public List<String> getAllPlayer(){
        return player;
    }
}
