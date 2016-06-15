/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import com.mongodb.MongoClient;
import org.bukkit.entity.Player;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 *
 * @author janschon
 */
public class PlayerDB {
    private MongoClient mc;
    private Morphia morphia;
    private Datastore datastore;
    private UserDAO uDAO;
    
    private static PlayerDB instance;
    
    private PlayerDB(){
        mc = new MongoClient();
        morphia = new Morphia();
        morphia.map(User.class);
        datastore = morphia.createDatastore(mc, "players");
        datastore.ensureIndexes();
        uDAO = new UserDAO(User.class, datastore);
    }
    
    public static PlayerDB getInstance(){
        if(instance == null){
            instance = new PlayerDB();
        }
        return instance;
    }
    
    public boolean player_in_db(Player p){
        return (uDAO.findOne("uuid", p.getUniqueId().toString()) != null);
    }
    
    public User getUserByPlayer(Player player)
    {
        User du = uDAO.findOne("uuid", player.getUniqueId().toString());
        if (du == null)
        {
            du = new User();
            du.setUUID(player.getUniqueId().toString());
            uDAO.save(du);
            System.out.println("[FastTravel] New player registed: " + player.getDisplayName());
        }
        return du;
    }
    
    public void saveUser(User user)
    {
        uDAO.save(user);
    }
}
