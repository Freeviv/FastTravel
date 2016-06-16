/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

/**
 *
 * @author janschon
 */
public class AreaDAO extends BasicDAO<Area, String>{
    
    public AreaDAO(Class<Area> entityClass, Datastore ds) {
        super(entityClass, ds);
    }
    
}
