/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;

/**
 *
 * @author janschon
 */
@Entity(value = "Area", noClassnameStored = true)
public class Area {
    @Id
    public int id;
    
    // Area coords
    public double x1;
    public double y1;
    public double z1;
    public double x2;
    public double y2;
    public double z2;
    
    @Indexed(options = @IndexOptions(unique = true))
    public String name;
    
    public Location loc;
    
    // TODO add dimension
    
    /**
     * Sets the dimension of the box-shaped area
     */
    public void set_area_dimensions(double x1, double y1, double z1, double x2, double y2, double z2){
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }
    
    /**
     * Helper methode to test weather a player is in a area or not
     * @param p Player to be tested
     * @return true if the player is in the area else false
     */
    public boolean player_in_area(Player p){
        boolean f = true;
        f = point_between_two(x1, x2, p.getLocation().getX());
        if(f){
            f = point_between_two(y1, y2, p.getLocation().getY());
        }else {
            return f;
        }
        if (f){
            f = point_between_two(z1, z2, p.getLocation().getZ());
        } else {
            return f;
        }
        return f;
    }
    
    /**
     * Helper Methode for the player_in_area-Methode. Tests if a point lays between
     * two other (one dimensional points)
     * @param p0 Point 1 
     * @param p1 Point 2
     * @param test Point to be tested
     * @return true if the testpoint lays between the two other given points 
     */
    private boolean point_between_two(double p0,double p1, double test){
        double t0 = p0-test;
        double t1 = p1-test;
        return ((t0*t1) < 0);
    }
    
}
