/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
