/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import com.mongodb.MongoClient;
import java.util.List;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author janschon
 */
public class AreaDB {
    
    private static AreaDB instance;
    
    private MongoClient mc;
    private Morphia morphia;
    private Datastore datastore;
    
    private AreaDB(){
        mc = new MongoClient();
        morphia = new Morphia();
        morphia.map(User.class);
        datastore = morphia.createDatastore(mc, "players");
        datastore.ensureIndexes();
        //uDAO = new UserDAO(User.class, datastore);
    }
    
    public static AreaDB getInstance(){
        if(instance == null){
            instance = new AreaDB();
        }
        return instance;
    }
    
    public List<Area> get_all_areas(){
        return null;
    }
}
