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
 *
 */
public class Perfil {

    private String nome;
    private String contrasinal;
    private String estado;
    private List<Perfil> solicitudes;
    private List<Perfil> amigos;
    private List<Mensaxe> mensajes;
    private List<Publicacion> publicacions;
    private List<Evento> eventos;
    private List<Evento> solicitudesEventos;

    public Perfil(String nome, String contrasinal) {

        this.nome = nome;
        this.contrasinal = contrasinal;
        this.estado = "Nuevo en CaraLibro";
        publicacions = new ArrayList<>();
        solicitudes = new ArrayList<>();
        amigos = new ArrayList<>();
        mensajes = new ArrayList<>();
        eventos = new ArrayList<>();
        solicitudesEventos = new ArrayList<>();
    }
    public Perfil(){
        this.estado = "Nuevo en CaraLibro";
        publicacions = new ArrayList<>();
        solicitudes = new ArrayList<>();
        amigos = new ArrayList<>();
        mensajes = new ArrayList<>();
        eventos = new ArrayList<>();
        solicitudesEventos = new ArrayList<>();
    }

    public List<Perfil> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<Perfil> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<Perfil> getAmigos() {
        return amigos;
    }

    public void setAmigos(List<Perfil> amigos) {
        this.amigos = amigos;
    }

    public List<Mensaxe> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaxe> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Publicacion> getPublicacions() {
        return publicacions;
    }

    public void setPublicacions(List<Publicacion> publicacions) {
        this.publicacions = publicacions;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public List<Evento> getSolicitudesEventos() {
        return solicitudesEventos;
    }

    public void setSolicitudesEventos(List<Evento> solicitudesEventos) {
        this.solicitudesEventos = solicitudesEventos;
    }

    public String getNome() {
        return nome;
    }

    public String getContrasinal() {
        return contrasinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setContrasinal(String contrasinal) {
        this.contrasinal = contrasinal;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void engadirPublicacion(Publicacion pub) {
        publicacions.add(pub);
    }

    public void engadirAmigo(Perfil perfil) {
        amigos.add(perfil);

    }

    public void engadirSolicitudDeAmizade(Perfil perfilsolicitante) {
        if (solicitudes.contains(perfilsolicitante) || amigos.contains(perfilsolicitante) 
                || perfilsolicitante.nome.equals(nome)) {

        } else {

            solicitudes.add(perfilsolicitante);
        }

    }

    public void aceptarSolicitudDeAmizade(Perfil perfilsolicitante) {

        engadirAmigo(perfilsolicitante);
        solicitudes.remove(perfilsolicitante);

    }

    public void rexeitarSolicitudDeAmizade(Perfil perfilsolicitante) {

        solicitudes.remove(perfilsolicitante);

    }

    public void engadirMensaxePrivada(Mensaxe m) {
        mensajes.add(m);

    }

    public void eliminarMensaxe(Perfil p, Mensaxe m) {
        mensajes.remove(m);

    }

    // codigo para crear eventos (opcional)
    public void engadirEvento(Evento evento) {
        eventos.add(evento);

    }

    public void eliminarEvento(Evento evento) {
        eventos.remove(evento);

    }

    public void engadirSolicitudEvento(Evento evento) {
        solicitudesEventos.add(evento);

    }

    public void eliminarSolicitudEvento(Evento evento) {
        solicitudesEventos.remove(evento);

    }

}
