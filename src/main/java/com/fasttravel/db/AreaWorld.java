/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


/**
 *
 * @author janschon
 */
public class AreaWorld {

    private final static String file_name = "xml_file.xml";
    private static List<Area> area = new ArrayList<>();
    
    private XMLInputFactory factory = XMLInputFactory.newFactory();
    private XMLEventReader reader = null;
    
    public AreaWorld(){
        try {
            reader = factory.createXMLEventReader(new FileReader(file_name));
        } catch (XMLStreamException | FileNotFoundException ex) {
            Logger.getLogger(AreaWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                        //System.out.println("Name: " + chars.getData());
                        name = false;
                    }
                    if(x_pos) {
                        a.setX(Integer.parseInt(chars.getData()));
                        //System.out.println("x_pos: " + chars.getData());
                        x_pos = false;
                    }
                    if(y_pos) {
                        a.setY(Integer.parseInt(chars.getData()));
                        //System.out.println("y_pos: " + chars.getData());
                        y_pos = false;
                    }
                    if(z_pos) {
                        a.setZ(Integer.parseInt(chars.getData()));
                        //System.out.println("z_pos: " + chars.getData());
                        z_pos = false;
                    }
                    if(player) {
                        //System.out.println("Player: " + chars.getData());
                        String[] players = chars.getData().split(" ");
                        for(String s:players){
                            a.addPlayer(s);
                        }
                        a.playerInit();
                        player = false;
                    }
                    break;
            }
            // No need for null pointer check
            if(a.isCorrect()){
                area.add(a);
                needsNewArea = true;
            }
        }
    }
    
    public void addArea(String name, int x,int y, int z){
    
    }
    
    public void writeAll() {
        String start = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n" +
                        "<class>";
        String end = "<\\class>";
        try {
            File f = new File("out_tmp.xml");
            if(!f.exists()){
                f.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AreaWorld.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AreaWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void main(String[] args){
        AreaWorld p = new AreaWorld();
        try {
            p.readFile();
        } catch (XMLStreamException ex) {
            Logger.getLogger(AreaWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(area.size());
        for(int i = 0; i < area.size(); i++){
            System.out.println(area.get(i).getAllPlayer().size());
        }
    }
}
