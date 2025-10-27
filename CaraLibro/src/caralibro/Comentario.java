package caralibro;
import java.time.LocalDateTime;
/**
 * @author Laura
 */
public class Comentario {
   //Atributos
    LocalDateTime date = LocalDateTime.now();
    String texto;
    Perfil autor;
    //Constructor
    public Comentario(String texto) {
        this.texto = texto;
    }
    
    //Métodos
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

    public Perfil getAutor() {
        return autor;
    }

    public void setAutor(Perfil autor) {
        this.autor = autor;
    }
    
}
