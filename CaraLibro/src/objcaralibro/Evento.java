/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a14carlosfd
 */
public class Evento {

    private String titulo;
    private String descripcion;
    private String fecha;
    private String hora;
    private Perfil autor;
    private List<Perfil> asistentes;
    private List<Perfil> pendientes;

    public Evento(Perfil autor, String titulo, String descripcion, String fecha, String hora) {

        this.autor = autor;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        asistentes = new ArrayList<>();
        pendientes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Perfil getAutor() {
        return autor;
    }

    public void setAutor(Perfil autor) {
        this.autor = autor;
    }

    public List<Perfil> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(List<Perfil> asistentes) {
        this.asistentes = asistentes;
    }

    public List<Perfil> getPendientes() {
        return pendientes;
    }

    public void setPendientes(List<Perfil> asistentes) {
        this.pendientes = asistentes;
    }

    public void anadirPendientes(Perfil p) {
        pendientes.add(p);

    }

    public void eliminarPendientes(Perfil p) {
        pendientes.remove(p);

    }

    public void anadirAsistente(Perfil p) {
        asistentes.add(p);

    }

    public void eliminarAsistente(Perfil p) {
        asistentes.remove(p);

    }
}
