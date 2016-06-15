/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import org.bukkit.entity.Player;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 *
 * @author janschon
 */
@Entity(value = "Area", noClassnameStored = true)
public class Area {
    @Id
    public int id;
    double x1;
    double y1;
    double z1;
    double x2;
    double y2;
    double z2;
    
    public void set_area_dimensions(double x1, double y1, double z1, double x2, double y2, double z2){
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }
    
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
    
    private boolean point_between_two(double p0,double p1, double test){
        double t0 = p0-test;
        double t1 = p1-test;
        return ((t0*t1) < 0);
    }
    
}
