/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author a22lucasmpg
 */
public class Oyente extends DefaultHandler {

    private String dato;
    private Perfil perfil = new Perfil();
    private Publicacion publi = new Publicacion(new Perfil(), null);
    private ArrayList<Perfil> perfilesXML = new ArrayList();

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Comienzo del documento");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Final del documento");
    }

    @Override
    public void startElement(String namespace, String sName, String qName, Attributes atrs) throws SAXException {
        System.out.println("Elemento: " + qName);

        if (qName.equals("perfil")) {
            perfil = new Perfil(null, null);
        } else if (qName.equals("publicacion")) {
            publi = new Publicacion(new Perfil(), null);
        }

    }

    @Override
    @SuppressWarnings("fallthrough")
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if(qName.equals("perfil")) {
            this.perfilesXML.add(perfil);
        }
        else if(qName.equals("nome")){
            System.out.println(dato);
            this.perfil.setNome(dato);
        }else if(qName.equals("contrasinal")){
            System.out.println(dato);
            this.perfil.setContrasinal(dato);
        }else if(qName.equals("estado")){
             System.out.println(dato);
                this.perfil.setEstado(dato);
        }else if(qName.equals("autor")){
             System.out.println(dato);
                this.publi.getAutor().setNome(dato);
        }else if(qName.equals("texto")){
            System.out.println(dato);
                this.publi.setTexto(dato);
        }else if(qName.equals("publicacion")){
             this.perfil.getPublicacions().add(publi);
        }   
    }

    @Override
    public void characters(char buf[], int offset, int len) {
        dato = new String(buf, offset, len);
    }

    public ArrayList<Perfil> getPerfilesXML() {
        return perfilesXML;
    }

    public void setPerfilesXML(ArrayList<Perfil> perfilesXML) {
        this.perfilesXML = perfilesXML;
    }

}
