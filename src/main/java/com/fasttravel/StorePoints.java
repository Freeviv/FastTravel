/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fasttravel;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author janschon
 */
public class StorePoints {
    
    // should be in config file
    private String tmp_file = "areas.xml";
    
    private static StorePoints instance;
    
    private File file;
    private DocumentBuilder builder;
    private Document document;
    
    private StorePoints(){
        loadFile();
    }
    
    private void loadFile(){
        File f = new File(tmp_file);
        if(builder == null){
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                builder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            document = builder.parse(f);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(StorePoints.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static StorePoints getInstance(){
        if(instance == null){
            instance = new StorePoints();
        }
        return instance;
    }
    
    
}
