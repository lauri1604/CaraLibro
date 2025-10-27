/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author [ a22danielas ] [ a14carlosfd ]
 */
public class CaraLibroBD {
    Sax sax = new Sax();
    private List<Perfil> perfiles= sax.parsearPerfilesXML();

    public CaraLibroBD() {    
        
             
        for (int i = 0; i < perfiles.size(); i++) {
            System.out.println(perfiles.get(i).getNome() + " " + perfiles.get(i).getContrasinal());    
       }
    }

    public void engadirPerfil(Perfil novoPerfil) {

        perfiles.add(novoPerfil);
    }

    public Perfil obterPerfil(String nombre, String contraseña) {
        Perfil n = null;
        int cont;

        for (cont = 0; cont < perfiles.size(); cont++) {

            if (perfiles.get(cont).getNome().contentEquals(nombre) && perfiles.get(cont).getContrasinal().contentEquals(contraseña)) {

                n = perfiles.get(cont);

            }
        }

        return n;
    }

    public Perfil buscarPerfil(String nome) {
        //Crea perfil 
        Perfil n = null;

        for (int i = 0; i < perfiles.size(); i++) {
            //Si no encuentra el nombre dado devolvera null
            if (nome.equals(perfiles.get(i).getNome())) {

                n = perfiles.get(i);
            }

        }
        return n;

    }
}
