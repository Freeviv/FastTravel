package com.fasttravel.db;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;

@Entity(value = "Users", noClassnameStored = true)
public class User
{

    @Id
    public int id;

    @Indexed(options = @IndexOptions(unique = true))
    public String uuid;
    

    @Indexed
    public String username;

    public int ip;

    public long connectionTime;

    @Property("ip_history")
    public List<Integer> ipHistory = new ArrayList<>();

    @Property("name_history")
    public List<String> nameHistory = new ArrayList<>();
    
    public List<Area> areas_discovered = new ArrayList<>();
    
    public List<Area> areas_not_discovered = new ArrayList<>();
    
    void setUUID(final String uuid) {
        if(uuid.trim().isEmpty() || uuid == null){
            throw new NullPointerException("No UUID for new Player!");
        } else {
            this.uuid = uuid;
        }
    }

}