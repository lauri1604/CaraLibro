package caralibro;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author a20lauramrr
 */
public class CaraLibroBD {
   //Atributos
    private static ArrayList <Perfil> perfiles = new ArrayList<>();

    //Constructor
    public CaraLibroBD() {
    }

    //Métodos
    public static ArrayList<Perfil> getPerfiles() {
        return perfiles;
    }

    public static void setPerfiles(ArrayList<Perfil> perfiles) {
        CaraLibroBD.perfiles = perfiles;
    }

    public static Perfil obterPerfil(String nome, String contrasinal) {
        //Iterator
        Perfil actual = null;
        Iterator <Perfil> it = perfiles.iterator();        
        while (it.hasNext()) {
            actual = it.next();
            if (nome.equals(actual.getNome()) && 
                contrasinal.equals(actual.getContrasinal())) {
               return actual;
            }
        }
        return null;
    }

    public static Perfil buscarPerfil(String nome) {
        Iterator <Perfil> it = perfiles.iterator();
        Perfil estePerfil = null;
        while (it.hasNext()) {
            estePerfil = it.next();
            if (nome.equals(estePerfil.getNome())) {
                return estePerfil;
            }
        }
        return null;
    }

    public static void engadirPerfil(Perfil novoPerfil) {
        perfiles.add(novoPerfil);
    } 
}
