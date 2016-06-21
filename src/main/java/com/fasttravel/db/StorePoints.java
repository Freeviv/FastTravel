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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


/**
 *
 * @author janschon
 */
public class StorePoints {

    // Maybe a bett path
    private final static String path = "plugins/FastTravel-1.0/";
    private final static String file_name = "areas.xml";
    private static List<Area> area = new ArrayList<>();
    private static List<String> names = new ArrayList<>();
    
    private static StorePoints instance;
    
    private XMLInputFactory factory = XMLInputFactory.newFactory();
    private XMLEventReader reader = null;
    
    private StorePoints(){
        File tmp = new File(path + file_name);
        if(!tmp.exists()){
            writeAll();
        }
        try {
            reader = factory.createXMLEventReader(new FileReader(path + file_name));
            readFile();
        } catch (XMLStreamException | FileNotFoundException ex) {
            Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("[FastTravel] This error can may occur if this plugin is started for the first time or there are no waypoints.");
            System.out.println("[FastTravel] If this happens even with areas added, please open an issue on GitHub!");
        }
    }
    
    public static StorePoints getInstance(){
        if(instance == null){
            instance = new StorePoints();
        }
        return instance;
    }
    
    private void readFile() throws XMLStreamException{
        boolean name = false;
        boolean x_pos = false;
        boolean y_pos = false;
        boolean z_pos = false;
        boolean player = false;
        boolean needsNewArea = true;
        Area a = null;
        while(reader.hasNext()){
            if(needsNewArea){
                a = new Area();
                needsNewArea = false;
            }
            if(a == null){
                throw new NullPointerException("Area was null. This should not happen!");
            }
            XMLEvent event = reader.nextEvent();
            switch(event.getEventType()){
                case XMLStreamConstants.START_ELEMENT:
                    StartElement start = event.asStartElement();
                    String seName = start.getName().toString();
                    if(seName.equalsIgnoreCase("name")){
                        name = true;
                    } else if(seName.equalsIgnoreCase("x_pos")){
                        x_pos = true;
                    } else if(seName.equalsIgnoreCase("y_pos")){
                        y_pos = true;
                    } else if(seName.equalsIgnoreCase("z_pos")){
                        z_pos = true;
                    } else if(seName.equalsIgnoreCase("players")){
                        player = true;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    Characters chars = event.asCharacters();
                    if(name) {
                        a.setName(chars.getData());
                        names.add(chars.getData());
                        name = false;
                    }
                    if(x_pos) {
                        a.setX(Integer.parseInt(chars.getData()));
                        x_pos = false;
                    }
                    if(y_pos) {
                        a.setY(Integer.parseInt(chars.getData()));
                        y_pos = false;
                    }
                    if(z_pos) {
                        a.setZ(Integer.parseInt(chars.getData()));
                        z_pos = false;
                    }
                    if(player) {
                        String[] players = chars.getData().split(" ");
                        for(String s:players){
                            a.addPlayer(s);
                        }
                        a.playerInit();
                        player = false;
                    }
                    break;
            }
            if(a.isCorrect()){
                area.add(a);
                needsNewArea = true;
            }
        }
    }
    
    public boolean addArea(String name, int x,int y, int z){
        if(name == null || name.trim().isEmpty()){
            return false;
        }
        Area a = new Area();
        a.setName(name);
        a.setX(x);
        a.setY(y);
        a.setZ(z);
        area.add(a);
        writeAll();
        return true;
    }
    
    public void writeAll() {
        String start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                        "<class>";
        String end = "</class>";
        File f = new File(path + "out_tmp.xml");
        try {
            if(!f.exists()){
                f.createNewFile();
            }
            FileWriter fw = new FileWriter(f);
            try (BufferedWriter writer = new BufferedWriter(fw)) {
                writer.append(start);
                writer.newLine();
                for(Area a:area){
                    writer.append("  <area>");
                    writer.newLine();
                    writer.append("      <name>" + a.getName() + "</name>");
                    writer.newLine();
                    writer.append("      <x_pos>" + a.getX() + "</x_pos>");
                    writer.newLine();
                    writer.append("      <y_pos>" + a.getY() + "</y_pos>");
                    writer.newLine();
                    writer.append("      <z_pos>" + a.getZ() + "</z_pos>");
                    writer.newLine();
                    String all_player = new String();
                    for(String s:a.getAllPlayer()){
                        all_player += s + " ";
                    }
                    all_player = all_player.trim();
                    writer.append("      <players>" + all_player + "</players>");
                    writer.newLine();
                    writer.append("  </area>");
                    writer.newLine();
                    writer.flush();
                }
                writer.append(end);
                writer.flush();
                fw.close();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
        }
        File old_file = new File(path + file_name);
        f.renameTo(old_file);
    }
    
    public List<String> getAllNames(){
        return names;
    }
    
    /**
     * Returns the area with the given name or null
     * @param name Name of the area
     * @return the area or null if this area does not exists
     */
    public Area getAreaByName(String name){
        for(Area a:area){
            if(a.getName().equals(name)){
                return a;
            }
        }
        return null;
    }
    
    public List<Area> getAllAreas(){
        return area;
    }
}
