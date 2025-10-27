package caralibro;
import java.util.ArrayList;
/**
 * @author a20lauramrr
 */
public class Perfil {
     //Atributos
    public String nome;
    public String contrasinal;
    public String estado = "en linea";
    private ArrayList <Publicacion> publicaciones = new ArrayList<>();
    private ArrayList <Perfil> solicitudes = new ArrayList<>();
    private ArrayList <Perfil> amigos = new ArrayList<>();
    private ArrayList <Mensaxe> mensaxes = new ArrayList<>();
            
    //Constructor
    public Perfil(String nome, String contrasinal) {
        this.nome = nome;
        this.contrasinal = contrasinal;        
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContrasinal() {
        return contrasinal;
    }

    public void setContrasinal(String contrasinal) {
        this.contrasinal = contrasinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(ArrayList<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public ArrayList<Perfil> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(ArrayList<Perfil> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public ArrayList<Perfil> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Perfil> amigos) {
        this.amigos = amigos;
    }

    public ArrayList<Mensaxe> getMensaxes() {
        return mensaxes;
    }

    public void setMensaxes(ArrayList<Mensaxe> mensaxes) {
        this.mensaxes = mensaxes;
    }
    
    //Métodos
    public void engadirPublicacion(Publicacion pub){
        publicaciones.add(pub);        
    }
    
    public void engadirAmigo(Perfil perfil){
        amigos.add(perfil);
    }
    public void engadirSolicitudesDeAmizade(Perfil perfilSolicitante){
        if(solicitudes.contains(perfilSolicitante)|| amigos
                .contains(perfilSolicitante) || perfilSolicitante.nome
                        .equals(nome)){            
        }else
            solicitudes.add(perfilSolicitante);
    }
    
    public void aceptarSolicitudAmizade(Perfil perfilSolicitante){
        engadirAmigo(perfilSolicitante);
        solicitudes.remove(perfilSolicitante);
    }
    
    public void rexeitarSolicitudeAmizade(Perfil perfilSolicitante){
        solicitudes.remove(perfilSolicitante);
    }
    public void engadirMensaxePrivada(Mensaxe m){
        mensaxes.add(m);
    }
    public void eliminarMensaxe(Mensaxe m){
        mensaxes.remove(m);
    }
}