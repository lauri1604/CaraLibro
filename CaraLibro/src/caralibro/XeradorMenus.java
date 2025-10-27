package caralibro;
import java.util.Iterator;
import java.util.Scanner;
/**
 * @author a20lauramrr
 */
public class XeradorMenus {
    //Atributos
    Perfil perfilActual = null;
    public void mostrarMenuInicial() {
        Scanner leer = new Scanner(System.in);
        String respuesta;
        System.out.println("Que quere facer?");
        System.out.println("1: Rexistrarse");
        System.out.println("2: Iniciar sesion");
        respuesta = leer.nextLine();
        if ("1".equals(respuesta)) {
            rexistrar();
        }
        if ("2".equals(respuesta)) {
            iniciarSesion();
        }
    }

    public void mostrarMenuPincipal() {
        Scanner leer = new Scanner(System.in);
        String respuesta;
        System.out.println("\n" + "(Pulse 1) Estado");
        System.out.println("(Pulse 2) Biografia");
        System.out.println("(Pulse 3) Solicitudes de amizade");
        System.out.println("(Pulse 4) Lista de amigos");
        System.out.println("(Pulse 5) Mensaxes");
        System.out.println("(Pulse 6) Cerrar sesion");
        respuesta = leer.nextLine();
        if ("1".equals(respuesta)) {
            cambiarEstado(perfilActual);
        }
        if ("2".equals(respuesta)) {
            mostrarBiografia(perfilActual);
        }
        if ("3".equals(respuesta)) {
            mostrarSolicitudesDeAmizade(perfilActual);
        }
        if ("4".equals(respuesta)) {
            mostrarListaDeAamigos(perfilActual);
        }
        if ("5".equals(respuesta)) {
            mostrarMensaxe(perfilActual);
        }

        if ("6".equals(respuesta)) {
            pecharSesion();
        }
    }

    private void rexistrar() {
        Scanner leer = new Scanner(System.in);
        String nome;
        String contrasinal;
        System.out.println("\n" + "NOVO PERFIL");
        System.out.println("Nome de usuario");
        nome = leer.nextLine();
        System.out.println("Contrasinal");
        contrasinal = leer.nextLine();
        System.out.println();
        Perfil perfil = new Perfil(nome, contrasinal);
        CaraLibroBD.engadirPerfil(perfil);
        mostrarMenuInicial();
    }

    private void iniciarSesion() {
        Scanner leer = new Scanner(System.in);
        String nome;
        String contrasinal;
        boolean inicioExitoso;
        String respuesta;
        do {
            inicioExitoso = true;
            System.out.println("\n" + "NOVO PERFIL");
            System.out.println("Nome de usuario");
            nome = leer.nextLine();
            System.out.println("Contrasinal");
            contrasinal = leer.nextLine();
            if (CaraLibroBD.obterPerfil(nome, contrasinal) == null) {
                System.out.println("Non te rexistraches ou o nome ou o Contrasinal son incorrectos" +
                        "\n");
                inicioExitoso = false;
                System.out.println("(Pulse 0) Para volver");
                respuesta = leer.nextLine();
                mostrarMenuInicial();
            } else {
                perfilActual = CaraLibroBD.obterPerfil(nome, contrasinal);
                mostrarMenuPincipal();
            }
        } while (!inicioExitoso);
    }

    private void cambiarEstado(Perfil perfilActual) {
        Scanner leer = new Scanner(System.in);
        String estado;
        String respuesta;
        System.out.println("Estado actual: " + perfilActual.getEstado() + "\n");
        System.out.println("Pulsa 1, para cambiar o estado");
        estado = leer.nextLine();
        if ("1".equals(estado)) {
            System.out.println("Introduza un novo estado");
            estado = leer.nextLine();
            perfilActual.setEstado(estado);
            System.out.println("O novo estado e " + estado + "\n");
        }
        Scanner leer2 = new Scanner(System.in);
        System.out.println("Pulsa 2, para volver ao menu principal");
        respuesta = leer2.nextLine();
        if ("2".equals(respuesta)) {
            mostrarMenuPincipal();
        }
    }

    public void mostrarBiografia(Perfil perfil) {
        Scanner leer = new Scanner(System.in);
        String respuesta;
        System.out.println("\n" + perfil.getNome().toUpperCase() + "'S" + " BIO" + "\n");
        System.out.println("Estado actual: " + perfil.getEstado());
        System.out.println("\n" + "PUBLICACIONS: " + "\n");
        System.out.println("(pulse 1) Publicar publicacion");
        System.out.println("(pulse 2) Mostrar publicacion");
        respuesta = leer.nextLine();
        if ("1".equals(respuesta)) {
            engadirPublicacion();
            mostrarMenuPincipal();
        }
        if ("2".equals(respuesta)) {
            Iterator<Publicacion> it = perfilActual.getPublicaciones().iterator();
            int publicacion = 1;
            while (it.hasNext()) {
                Publicacion pub = it.next();
                if (pub.getAutor().getNome().equals(pub.getAutor().getNome())) {
                    System.out.println(publicacion++ + " -> " + "Ti escribiches: " + pub.getTexto() +
                            " " + pub.getDate() + "\n");
                } else {
                    System.out.println(publicacion++ + " -> " + pub.getAutor() + " escribiu: " + pub.
                            getTexto() + " " + pub.getDate() + "\n");
                }
            }
        }
        int respuesta2;
        System.out.println("Elxe unha publicacion, na queiras facer un comentario");
        respuesta2 = leer.nextInt();
        Publicacion pub = perfilActual.getPublicaciones().get(respuesta2 - 1);
        menuComentario(pub, perfilActual);
        mostrarMenuPincipal();
    }

    private void engadirPublicacion() {
        Scanner leer = new Scanner(System.in);
        String texto;
        System.out.println("Introduza o texto que queiera escribir na publicacion:");
        texto = leer.nextLine();
        Publicacion publicacion = new Publicacion(perfilActual, texto);
        perfilActual.getPublicaciones().add(publicacion);
    }

    public void pecharSesion() {
        mostrarMenuInicial();
    }

    private void escribirComentario(Publicacion publicacionActual, Perfil perfilActual) {
        Scanner leer = new Scanner(System.in);
        String texto;
        System.out.println("Escribe o teu comentario");
        texto = leer.nextLine();
        Comentario comentario = new Comentario(texto);
        comentario.setAutor(perfilActual);
        publicacionActual.getComentarios().add(comentario);
    }

    private void menuComentario(Publicacion publicacionActual, Perfil perfil) {
        Scanner leer1 = new Scanner(System.in);
        String respuesta;
        System.out.println("(pulse 1) Escribir un comentario");
        System.out.println("(pulse 2) Ver comentario");
        System.out.println("(pulse 3) Ver num de usuarios que fixeron me gusta");
        System.out.println("(pulse 4) Volver menu principal");
        respuesta = leer1.nextLine();
        if ("1".equals(respuesta)) {
            escribirComentario(publicacionActual, perfilActual);
        }
        if ("2".equals(respuesta)) {
            Iterator<Comentario> it = publicacionActual.getComentarios().iterator();
            while (it.hasNext()) {
                Comentario comentarioActual = it.next();
                if (comentarioActual.getAutor().getNome().equals(comentarioActual
                        .getAutor().getNome())) {
                    System.out.println("Tu escribiches: " + comentarioActual.getTexto() + " " +
                            comentarioActual.getDate() + "\n");
                } else {
                    System.out.println(comentarioActual.getAutor()
                            + " escribiu: " + comentarioActual.getTexto() + " " + comentarioActual
                            .getDate() + "\n");
                }
            }
        }
        if ("3".equals(respuesta)) {
            facerMeGusta(publicacionActual, perfil);
        }
        if ("4".equals(respuesta)) {
            mostrarMenuPincipal();
        }
    }

    private void facerMeGusta(Publicacion pub, Perfil perfil) {
        Iterator <Perfil> it = pub.gustalleA.iterator();
        while (it.hasNext()) {
            Perfil publicacionActual = it.next();
            if (pub.getGustalleA().contains(perfil)) {
                System.out.println("Xa diches me gusta a esta publicacion");
            } else
                pub.engadirMeGusta(perfil);
        }
    }

    private void mostrarSolicitudesDeAmizade(Perfil perfil) {
        Scanner leer = new Scanner(System.in);
        String respuesta;
        if (perfil.getSolicitudes().isEmpty()) {
            System.out.println("SOLICITUDES DE AMIZADE" + "\n");
            System.out.println("Sin solicitudes pendentes" + "\n");
        } else {
            System.out.println(perfil.getSolicitudes().size());
        }
        System.out.println("(Pulsa 1) Facer solicitudes");
        System.out.println("(Pulsa 2) Ver solicitudes pendentes");
        System.out.println("(Pulsa 3) Volver ao menu principal");
        respuesta = leer.nextLine();
        if ("1".equals(respuesta)) {
            facerSolicitudes(perfil);
        }
        if ("2".equals(respuesta)) {
            boolean haisolicitudes = verSolicitudesPendentes(perfil);
            if (haisolicitudes) {
                menuSolicitudes(perfil);
                mostrarMenuPincipal();
            }
        }
        if ("3".equals(respuesta)) {
            mostrarMenuPincipal();
        }
    }

    private boolean verSolicitudesPendentes(Perfil perfil) {
        Iterator <Perfil> it = perfil.getSolicitudes().iterator();
        int solicitude = 1;
        boolean haisolicitudes = false;
        while (it.hasNext()) {
            Perfil solicitudesPendentes = it.next();
            haisolicitudes = true;
            if (solicitudesPendentes.getNome().equals(solicitudesPendentes.getNome())) {
                System.out.println(solicitude++ + " -> " + "Ti fixeches unha solicitude de amizade"
                        + "\n");
            } else {
                System.out.println(solicitude++ + " -> " + solicitudesPendentes.getNome() +
                        "Te fixo unha solicitude de amizade");
            }
        }
        return haisolicitudes;
    }

    private void facerSolicitudes(Perfil perfil) {
        Scanner leer = new Scanner(System.in);
        String respuesta;
        System.out.println("Escriba un nome ao que queiras facer unha solicitude: ");
        respuesta = leer.nextLine();
        Perfil novoPerfil = CaraLibroBD.buscarPerfil(respuesta);
        if (novoPerfil != null) {           
           novoPerfil.engadirSolicitudesDeAmizade(perfil);
           System.out.println("Solicitude rexistrada");
           mostrarMenuPincipal();
        } else {
            System.out.println("Non existe este perfi \n");
            String respuesta2;
            System.out.println("(Pulse 0) Para volver");
            respuesta2 = leer.nextLine();
            if ("0".equals(respuesta2)) {
                mostrarSolicitudesDeAmizade(perfil);
            }
        }
    }

    private void menuSolicitudes(Perfil perfil) {
        Scanner leer = new Scanner(System.in);
        int respuesta;
        String respuesta2;
        System.out.println("Escolle una solicitude");
        respuesta = leer.nextInt();
        respuesta2 = leer.nextLine();
        Perfil solicitude = perfil.getSolicitudes().get(respuesta - 1);
            System.out.println("Que desea facer coa solicitude");
            System.out.println("(Pulse 1) Aceptar solicitude");
            System.out.println("(Pulse 2) Rexeitar solicitude");
            respuesta2 = leer.nextLine();        
        if ("1".equals(respuesta2)) {
            System.out.println("Queres aceptar a solicitude");
            perfil.aceptarSolicitudAmizade(solicitude);
            System.out.println("Solicitude aceptada");
        }
        if ("2".equals(respuesta2)) {
            perfil.rexeitarSolicitudeAmizade(solicitude);
            System.out.println("Solicitude rexeitada");
        }
    }

    private void mostrarListaDeAamigos(Perfil perfil) {
        Scanner leer = new Scanner(System.in);
        int amigos = 1;
        if (perfil.getAmigos().isEmpty()) {
            System.out.println("Non tes amig@s \n");
        } else {
            Iterator <Perfil> it = perfil.getAmigos().iterator();
            while (it.hasNext()) {
                Perfil amigo = it.next();
                if (perfil.getNome().equals(perfil.getNome())) {
                    System.out.println(amigos++ + " -> " + perfil.getNome() + perfil.getEstado());
                }
            }
            int respuesta;
            System.out.println("Selecciona ao amigo que queiras enviarlle unha mensaxe");
            respuesta = leer.nextInt();
            Perfil amigo = perfil.getAmigos().get(respuesta - 1);
            escribirMensaxe(perfil, amigo);
        }
        mostrarMenuPincipal();
    }

    private void mostrarMensaxe (Perfil perfil){
        Scanner leer = new Scanner(System.in);
        int mensaxe = 1;
        if (perfilActual.getMensaxes().isEmpty()){
            System.out.println("Non tes mensaxes");
        } else {
            Iterator <Mensaxe> it = perfilActual.getMensaxes().iterator();
            while (it.hasNext()){
                Mensaxe mensaxes = it.next();
                if (perfilActual.getMensaxes().get(0).getRemitente().equals(perfilActual.getMensaxes()
                        .get(0).getRemitente())){
                    System.out.println(mensaxe++ + "-> " + perfilActual.getMensaxes().get(0).getTexto());
                }
            }
            int respuesta;
            String respuesta2;
            System.out.println("Elixe unha mensaxe");
            respuesta = leer.nextInt();
            Mensaxe mensaxeSeleccionado = perfilActual.getMensaxes().get(respuesta -1);
            System.out.println("Que desea facer coa mensaxe?");
            System.out.println("(Pulse 1) Marcar como lida");
            System.out.println("(Pulse 2) Eliminar mensaxe");
            System.out.println("(Pulse 3) Responder mensaxe");
            respuesta2 = leer.nextLine();
            if ("1".equals(respuesta2)){
                marcarMensaxeComoLida(mensaxeSeleccionado);
            }
            if ("2".equals(respuesta2)){
               perfilActual.eliminarMensaxe(mensaxeSeleccionado);
            }
        }
    }

    private void menuMensaxeAmigo(Perfil perfil, Perfil amigo) {
        Scanner leer = new Scanner(System.in);
        String respuesta = null;
        System.out.println("(Pulse 1) Enviar mensaxe");
        System.out.println("(Pulse 2) Mostrar biografia");
        System.out.println("(Pulse 1) Volver ao menu principal");
        if ("1".equals(respuesta)) {
            escribirMensaxe(perfil, amigo);
        }
        if ("2".equals(respuesta)){
            mostrarBiografia(amigo);
        }
        if ("3".equals(respuesta)){
            mostrarMenuPincipal();
        }
    }

    private void escribirMensaxe(Perfil remitente, Perfil destinatario) {
        Scanner leer = new Scanner(System.in);
        String texto;
        System.out.println("Escribe a tua mensaxe");
        texto = leer.nextLine();
        Mensaxe mensaxe = new Mensaxe(texto, remitente);
        destinatario.engadirMensaxePrivada(mensaxe);
        System.out.println("Mensaxe enviada");
    }

    private void marcarMensaxeComoLida(Mensaxe m){
        Iterator <Mensaxe> it = perfilActual.getMensaxes().iterator();
        while (it.hasNext()){
            Mensaxe mensaxeSeleccionado = it.next();
            if (mensaxeSeleccionado.isLido() == true){
                System.out.println("Mensaxe lida");
            }
        }
    }
}