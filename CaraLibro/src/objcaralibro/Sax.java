/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


/**
 *
 * @author a22lucasmpg
 */
public final class Sax {

    
    public Sax() {
       
    }
    
    
   public ArrayList parsearPerfilesXML()  {
        File archivoXML = new File("perfiles.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser parser= null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException ex) {
            Logger.getLogger(Sax.class.getName()).log(Level.SEVERE, null, ex);
        }

        Oyente oyente = new Oyente();
        try {   
            parser.parse(archivoXML, oyente);
        } catch (SAXException | IOException ex) {
            Logger.getLogger(Sax.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for (int i = 0; i < oyente.getPerfilesXML().size(); i++) {
//            System.out.println(oyente.getPerfilesXML().get(i).getNome() + " " + oyente.getPerfilesXML().get(i).getContrasinal());    
//       }
       
        return oyente.getPerfilesXML();
    }
}
