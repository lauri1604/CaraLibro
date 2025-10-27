/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author [ a22danielas ] [ a14carlosfd ]
 */
public class Publicacion {

    private String texto;
    private LocalDateTime data;
    private LocalTime time;
    private Perfil autor;
    private List<Perfil> gustalleA;
    private List<Comentario> comentarios;

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Publicacion(Perfil autor, String texto) {

        this.texto = texto;
        this.autor = autor;
        time = LocalTime.now();
        data = LocalDateTime.now();

        comentarios = new ArrayList<>();
        gustalleA = new ArrayList<>();

    }

    public List<Perfil> getGustalleA() {
        return gustalleA;
    }

    public void setGustalleA(List<Perfil> gustalleA) {
        this.gustalleA = gustalleA;
    }

    public Publicacion() {

    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Perfil getAutor() {
        return autor;
    }

    public void setAutor(Perfil autor) {
        this.autor = autor;
    }

    public void engadirComentario(Perfil autor, String texto) {
        Comentario comentario = new Comentario(autor, texto);
        comentarios.add(comentario);
    }

    public void engadirMeGusta(Perfil autor) {
        gustalleA.add(autor);
    }

}
