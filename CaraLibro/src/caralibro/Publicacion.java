package caralibro;
import java.time.LocalDateTime;
import java.util.ArrayList;
/**
 * @author a20lauramrr
 */
public class Publicacion {
    //Atributos
    LocalDateTime date = LocalDateTime.now();
    String texto;
    Perfil autor;     
    ArrayList <Comentario> comentarios = new ArrayList<>();
    ArrayList <Perfil> gustalleA = new ArrayList<>();
    
    //Constructor
    public Publicacion(Perfil autor, String texto) {
        this.texto = texto;
        this.autor = autor;
    }    

    public LocalDateTime getDate() {
        return date;
    }

    public String getTexto() {
        return texto;
    }

    public Perfil getAutor() {
        return autor;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setAutor(Perfil autor) {
        this.autor = autor;
    }

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public ArrayList<Perfil> getGustalleA() {
        return gustalleA;
    }

    public void setGustalleA(ArrayList<Perfil> gustalleA) {
        this.gustalleA = gustalleA;
    }
    
    //Métodos
    public void engadirComentario(Comentario comentario){
        comentarios.add(comentario);
    }
    public void engadirMeGusta(Perfil autor){
        gustalleA.add(autor);
    }
}
