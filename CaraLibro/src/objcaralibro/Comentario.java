/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.util.Date;

/**
 *
 * @author [ a22danielas ] [ a14carlosfd ]
 */
public class Comentario {

    private String texto;
    private Date data;
    private Perfil autor;

    public Comentario(Perfil autor, String texto) {

        this.texto = texto;
        this.autor = autor;
        data = new Date();

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Perfil getAutor() {
        return autor;
    }

    public void setAutor(Perfil autor) {
        this.autor = autor;
    }

}
