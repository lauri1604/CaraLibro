package caralibro;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @author a20lauramrr
 */
public class Mensaxe {
    //Atributos
   LocalDateTime date = LocalDateTime.now();
    String texto;
    boolean lido;
    Perfil remitente;
    ArrayList <Mensaxe> mensaxes = new ArrayList<>();
    
    //Constructor
    public Mensaxe(String texto, Perfil remitente) {
        this.texto = texto;
        this.lido = lido;
        this.date = date;
        this.remitente = remitente;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public ArrayList<Mensaxe> getMensaxes() {
        return mensaxes;
    }

    public void setMensaxes(ArrayList<Mensaxe> mensaxes) {
        this.mensaxes = mensaxes;
    }
    
}
