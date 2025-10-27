
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author [ a22danielas ] [ a14carlosfd ]
 */
public class Mensaxe {

    private String texto;
    private LocalDateTime data;
    private LocalTime time;
    private boolean lido;
    private Perfil remitente;
    private List<Mensaxe> chat;

    public LocalDateTime getData() {
        return data;
    }

    public LocalTime getTime() {
        return time;
    }

    public Mensaxe(String texto, Perfil remitente) {
        this.remitente = remitente;
        this.texto = texto;
        time = LocalTime.now();
        data = LocalDateTime.now();
        lido = false;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isLido() {
        return lido;
    }

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public Perfil getRemitente() {
        return remitente;
    }

    public void setRemitente(Perfil remitente) {
        this.remitente = remitente;
    }

    public List<Mensaxe> getChat() {
        return chat;
    }

    public void setChat(List<Mensaxe> chat) {
        this.chat = chat;
    }

}
