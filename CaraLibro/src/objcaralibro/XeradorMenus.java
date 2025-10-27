/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objcaralibro;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author [ a22danielas ] [ a14carlosfd ]
 *
 * TODOS LOS METODOS SON PRIVADOS EXCEPTO el contructor y el menu Inicial ya que
 * una vez construido el objeto menu y llamado el menu incial, accedemos a todas
 * las demas secciones a traves del menu inicial.
 *
 * Metodos Mostrar menu principales ID=mainmenu("numero al que se referencia")
 *
 */
public class XeradorMenus {

    Scanner keyboardInt = new Scanner(System.in);
    Scanner keyboardString = new Scanner(System.in);
    CaraLibroBD bd = new CaraLibroBD();
    Perfil login;

    public XeradorMenus() {
        // limpiar consola por estetica
        limpiarConsola();
        // Creamos usuarios 'carlos' 'dani' 'user',
//        crearPerfilPredeterminado();
    }

    /**
     * Muestra el menu para seleccionar si iniciar sesion o crear perfiles
     */
    public void mostrarMenuInicial() {
        //Realizamos este limpiar consola por estetica y muestre
        //todo en la parte inferior de la consola.

        //Cada vez que se llama al metodo el perfil login pasa a null
        login = null;

        System.out.println("""                            
                             ============================================
                                  ===== Bienvenido a CaraLibro =====
                             ============================================""");

        System.out.println("(1) Iniciar Sesion");
        System.out.println("(2) Crear Perfil");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
        }
        switch (menuOption) {

            case "1" -> {
                iniciarSesion();

            }
            default -> {
                crearPerfil();

            }

        }
        limpiarConsola();

    }

    /**
     * Muestra el menu creacion de Perfil, la entrada de datos y añadirlo en
     * BBDD
     */
    private void crearPerfil() {

        Scanner keyboard = new Scanner(System.in);
        String nombre;
        String contrasena = "";
        String contrasena2;
        boolean repiteContrasena = false;

        System.out.println("""
                                 ============================================
                                           ===== Crear Perfil =====
                                 ============================================""");
        System.out.print("Usuario: ");
        nombre = keyboard.nextLine().toLowerCase();
        while (!repiteContrasena) {
            System.out.print("Password: ");
            contrasena = keyboard.nextLine();
            // Comprobacion de que la contraseña es la misma en las introducciones
            System.out.print("Repite Password: ");
            contrasena2 = keyboard.nextLine();
            if (contrasena.equals(contrasena2)) {
                repiteContrasena = true;
            } else {
                System.out.println("""
                                   
                                    * Contrasena no coincide *
                                   """);
            }
        }
        //Si usuario no existe en BBDD se añade.
        if (bd.buscarPerfil(nombre) == null) {
            Perfil perf = new Perfil(nombre, contrasena);
            bd.engadirPerfil(perf);
            limpiarConsola();
            mostrarMenuInicial();

        }//Si no muestra mensaje y retorna a crear perfil.
        else {
            limpiarConsola();
            System.out.println("Ya existe un usuario con ese nombre");
            crearPerfil();
        }

    }

    /**
     * Creacion de Perfiles de pruebas
     */
    private void crearPerfilPredeterminado() {

        //crear perfiles
        Perfil perf1 = new Perfil("dani", "123");
        Perfil perf2 = new Perfil("carlos", "123");
        Perfil perf3 = new Perfil("user", "123");
        Perfil perf6 = new Perfil("ruben", "123");
        Perfil perf7 = new Perfil("david", "123");
        Perfil perf8 = new Perfil("sergio", "123");

        bd.engadirPerfil(perf1);
        bd.engadirPerfil(perf2);
        bd.engadirPerfil(perf3);

        //añadir amigos
        perf1.engadirAmigo(perf2);
        perf2.engadirAmigo(perf1);

        perf1.engadirAmigo(perf3);
        perf3.engadirAmigo(perf1);
        perf1.engadirAmigo(perf6);
        perf1.engadirAmigo(perf7);
        perf1.engadirAmigo(perf8);

    }

    // Limpieza de consola 
    /**
     * Realiza saltos de linea
     */
    private void limpiarConsola() {

        for (int i = 0; i < 50; i++) {

            System.out.println();
        }

    }

    /**
     * Muestra el menu inicio sesion de Perfil, la entrada de datos y acceder al
     * menu principal
     */
    private void iniciarSesion() {
        String nombre;
        String contrasena;
        login = null;
        boolean flag = false;
        while (!flag) {

            System.out.println("""
                                 ============================================
                                          ===== Inicio  Sesion =====
                                 ============================================""");

            System.out.print("Usuario: ");
            nombre = keyboardString.nextLine();
            System.out.print("Password: ");
            contrasena = keyboardString.nextLine();
            //Si el nombre no existe 
            if (bd.buscarPerfil(nombre) == null) {
                System.out.println("- * Este usuario no existe * -");
                mostrarMenuInicial();
            } else {
                //Si el nombre existe el usuario login es nombre+contrasena
                login = bd.obterPerfil(nombre, contrasena);
                //Si la contrasena es correcta
                if (login != null) {
                    mostrarMenuPrincipal(login);
                    flag = true;
                } else {
                    limpiarConsola();
                    System.out.println("* - Contrasena incorrecta - *");
                }
            }
        }
    }

    //ID=mainmenu(0)
    /**
     * Muestra el menu principal del Perfil que se le pasa y nos permite acceder
     * un submenu que nos ofrece
     */
    private void mostrarMenuPrincipal(Perfil p) {

        limpiarConsola();
        System.out.println("""
                                 ============================================
                                          ===== Menu Principal =====
                                 ============================================""");

        System.out.println("Bienvenido " + p.getNome());
        System.out.println("(1) [Estado Actual]: " + p.getEstado());
        System.out.println("(2) A tua Biografia");
        System.out.println("(3) Solicitudes de Amistad: " + p.getSolicitudes().size());
        System.out.println("(4) Lista de amig@s");
        System.out.println("(5) Mensaxes: " + p.getMensajes().size());
        System.out.println("(6) Eventos");
        System.out.println("(7) Recomendaciones");
        System.out.println("(8) Pechar a sesion");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)
                && !"4".equals(menuOption) && !"5".equals(menuOption) && !"6".equals(menuOption)
                && !"7".equals(menuOption) && !"8".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();

            if (menuOption.equals("4") && p.getAmigos().isEmpty()) {
                System.out.println("* - No tienes amig@s - *");
                menuOption = "";
            }

        }

        switch (menuOption) {

            case "1" -> {
                limpiarConsola();
                cambiarEstado(p);
            }
            case "2" -> {
                limpiarConsola();
                mostrarBiografia(p);
            }
            case "3" -> {
                limpiarConsola();
                mostrarSolicitudesDeAmizade(p);
            }
            case "4" -> {
                limpiarConsola();
                mostrarListaAmigos(p);
            }

            case "5" -> {
                limpiarConsola();
                mostrarMensaxes(p);
            }

            case "6" -> {
                limpiarConsola();
                mostrarEventos(p);
            }
            case "7" -> {
                limpiarConsola();
                recomendaciones(p);
            }

            default -> {
                limpiarConsola();
                mostrarMenuInicial();
            }
        }

    }

    //ID=mainmenu(1)
    /**
     * Muestra el menu para actualizar estado de un Perfil
     */
    private void cambiarEstado(Perfil p) {
        String menuOption = "";

        System.out.println("""                            
                             ============================================
                                          ===== Estado =====
                             ============================================""");
        System.out.println("\n Estado Actual: " + p.getEstado() + "\n");

        System.out.println("(1) Actualizar Estado");
        System.out.println("(2) Salir");

        while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
        }

        switch (menuOption) {

            case "1" -> {
                System.out.println("Nuevo estado:");
                p.setEstado(keyboardString.nextLine());

                limpiarConsola();
                cambiarEstado(p);
            }

            default -> {
                mostrarMenuPrincipal(p);

            }
        }
    }

    // ID=mainmenu(2)
    /**
     * Muestra las publicaciones de un Perfil dado las muestra de manera inversa
     * para mostrarlas por orden de creacion descendente y nos permite crear una
     * publicacion
     */
    private void mostrarBiografia(Perfil p) {

        System.out.println("""                            
                             ============================================
                                        ===== Biografia =====
                             ============================================""");

        mostrarPublicacionesPerfil(p);
        if (login == p) {
            System.out.println(" TU BIOGRAFIA");
        } else {
            System.out.println("BIOGRAFIA DE " + p.getNome().toUpperCase());
        }

        System.out.println("(1) Anadir Publicacion");
        System.out.println("(2) Seleccionar Publicacion");
        System.out.println("(3) Atras");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
            if (p.getPublicacions().isEmpty() && menuOption.equals("2")) {

                System.out.println("* - No tienes publicaciones - *");
                menuOption = "0";

            }
        }

        switch (menuOption) {
            case "1" -> {
                System.out.println("Escribe la publicacion");
                Publicacion pub = new Publicacion(login, keyboardString.nextLine());
                p.engadirPublicacion(pub);
                limpiarConsola();
                mostrarBiografia(p);
            }
            case "2" -> {
                limpiarConsola();
                InteraccionPublicacion(p);
            }

            default -> {
                mostrarMenuPrincipal(login);
            }
        }

    }

    /**
     * Entrada de datos para añadir un comentario
     */
    private void escribirComentario(Publicacion pub, Perfil p) {

        System.out.println("Anade comentario");

        pub.engadirComentario(p, keyboardString.nextLine());

    }

    /**
     * Control si el perfil ya ha dado me gusta
     */
    private void facerMeGusta(Publicacion pub, Perfil p) {

        if (pub.getGustalleA().contains(p)) {
            System.out.println("Ya has dado me gusta a esta publicación");

        } else {
            pub.engadirMeGusta(p);
        }

    }

    /**
     * Recorre el array de publicaciones de un Perfil dado.
     */
    private void mostrarPublicacionesPerfil(Perfil usuarioSeleccionado) {

        for (int i = (usuarioSeleccionado.getPublicacions().size() - 1); i >= 0; i--) {

            //posicion del array invirtiendo
            System.out.println(("        - - - (" + (usuarioSeleccionado.getPublicacions().size() - i) + ") - - -"));

            //fecha y hora publicacion
            System.out.print(" - " + usuarioSeleccionado.getPublicacions().get(i).getData().format(DateTimeFormatter.ISO_DATE));
            System.out.print(" a las ");
            System.out.println(usuarioSeleccionado.getPublicacions().get(i).getTime().truncatedTo(ChronoUnit.SECONDS) + " - ");

            //autor publicacion
            if (usuarioSeleccionado.getPublicacions().get(i).getAutor() == login) {
                System.out.println("        Escrito por ti");
            } else {
                System.out.println("      Escrito por: " + usuarioSeleccionado.getPublicacions().get(i).getAutor().getNome());
            }

            //texto publicacion
            System.out.println(usuarioSeleccionado.getPublicacions().get(i).getTexto());
            //me gusta publicacion
            System.out.println("Me Gusta(s): " + usuarioSeleccionado.getPublicacions().get(i).getGustalleA().size());
            //mostrar comentarios
            if (usuarioSeleccionado.getPublicacions().get(i).getComentarios().isEmpty()) {
                System.out.println("Sin comentarios");
            } else {

                System.out.println(" - Comentarios: - \n");

                for (int j = 0; j < usuarioSeleccionado.getPublicacions().get(i).getComentarios().size(); j++) {

                    System.out.println(usuarioSeleccionado.getPublicacions().get(i).getComentarios().get(j).getTexto());

                    //fecha y hora publicacion
                    System.out.print(" - " + usuarioSeleccionado.getPublicacions().get(i).getData().format(DateTimeFormatter.ISO_DATE));
                    System.out.print(" a las ");
                    System.out.println(usuarioSeleccionado.getPublicacions().get(i).getTime().truncatedTo(ChronoUnit.SECONDS) + " - ");

                    if (usuarioSeleccionado.getPublicacions().get(i).getComentarios().get(j).getAutor() == login) {
                        System.out.println("Escrito por ti");
                    } else {
                        System.out.println("Escrito por: " + usuarioSeleccionado.getPublicacions().get(i).getComentarios().get(j).getAutor().getNome());
                    }

                }
            }
            //dejar espacions Entre publicaciones
            System.out.println();

        }

    }

    /**
     * Interactua con una publicacion y permite añadir comentario o me gusta.
     */
    private void InteraccionPublicacion(Perfil p) {

        mostrarPublicacionesPerfil(p);

        String menuOption = "";
        System.out.println("Con que publicacion quieres interactuar?");
        //posicionPublicacion nos sirve para invertir la lista y ahorrar trabajo de ordenar continuamente
        int seleccionPublicacion = keyboardInt.nextInt();
        while (seleccionPublicacion < 0 || seleccionPublicacion > p.getPublicacions().size()) {
            System.out.println("Selecciona una publicacion correcta");
            seleccionPublicacion = keyboardInt.nextInt();
        }
        Publicacion pub = p.getPublicacions().get(p.getPublicacions().size() - seleccionPublicacion);

        System.out.println("\n(1)Anadir Comentario");
        System.out.println("(2)Me gusta");
        System.out.println("(3)Atras");

        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
            if (p.equals(pub.getAutor()) && menuOption.equals("2")) {
                System.out.println("No puedes dar me gusta a tu publicacion");
                menuOption = "";
            }
        }

        //COMENTAR PUBLICACIÓN
        switch (menuOption) {
            case "1" -> {

                escribirComentario(pub, login);
                mostrarBiografia(p);
            }
            case "2" -> {

                facerMeGusta(pub, p);
                mostrarBiografia(p);
            }

            default -> {
                limpiarConsola();
                mostrarMenuPrincipal(login);
            }
        }

    }

    //ID=mainmenu(3)
    /**
     * Muestra, si las tiene, las solicitudes de amistad de un Perfil
     */
    private void mostrarSolicitudesDeAmizade(Perfil p) {

        String menuOption = "";

        //Ensena Solicitudes
        //Si la lista  desolicitudes esta vacia
        if (p.getSolicitudes().isEmpty()) {

            System.out.println("""
                                 ============================================
                                       ===== Solicitudes Amistad =====
                                 ============================================""");
            System.out.println();
            System.out.println("* - Sin Solicitudes Pendientes - * \n");
        } //Sino ensena cada solicitud junto su numero id 
        else {

            for (int i = 0; i < p.getSolicitudes().size(); i++) {
                System.out.println((i + 1) + " " + p.getSolicitudes().get(i).getNome() + " Quiere ser tu amig@");

            }

        }

        System.out.println("(1) Anadir Amigo");
        System.out.println("(2) Interactuar con solicitud");
        System.out.println("(3) Menu Principal");

        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
            if (menuOption.equals("2") && p.getSolicitudes().isEmpty()) {
                System.out.println("No hay solicitudes pendientes");
                menuOption = "-5";

            }
        }

        switch (menuOption) {
            case "1" ->
                anadirAmigos(p);
            case "2" ->
                mostrarSeleccionSolicitudAmigo(p);
            default ->
                mostrarMenuPrincipal(p);

        }
    }

    /**
     * Muestra si un Perfil dato tiene solicitudes pendientes las muestra y
     * permite interactuar con ellas para aceptarla rechazarla.
     */
    private void mostrarSeleccionSolicitudAmigo(Perfil p) {

        System.out.println("Marca el numero de la solicitud deseada");
        int solicitud = keyboardInt.nextInt() - 1;
        //Para interactuar con cada publicacion
        while (solicitud > p.getSolicitudes().size()) {
            System.out.println("Marca el numero de la solicitud deseada");
            solicitud = keyboardInt.nextInt() - 1;
        }

        Perfil solicitudSeleccionada = p.getSolicitudes().get(solicitud);

        String validacion = "";
        while (!validacion.equals("1") && !validacion.equals("2")) {
            System.out.println("(1)Aceptar");
            System.out.println("(2)Rechazar");
            validacion = keyboardString.nextLine();

        }

        if ("1".equals(validacion)) {
            p.aceptarSolicitudDeAmizade(solicitudSeleccionada);
            solicitudSeleccionada.engadirAmigo(p);
            System.out.println(solicitudSeleccionada.getNome() + "Ya sois amig@s");

        } else {
            p.rexeitarSolicitudDeAmizade(solicitudSeleccionada);
            System.out.println("Solicitud rechazada");

        }

        mostrarSolicitudesDeAmizade(p);
    }

    /**
     * Pide por pantalla un nombre de un Perfil y lo valida, si todo es correcto
     * lo añade.
     */
    private void anadirAmigos(Perfil p) {

        boolean amigoEncontrado = false;
        String amigo;

        System.out.println("""
                                 ============================================
                                          ===== Anadir  Amig@s =====
                                 ============================================""");
        while (!amigoEncontrado) {
            //Crea una solicitud
            //pide usuario al que anadir
            System.out.print("(0)Salir - Nombre: ");
            amigo = keyboardString.nextLine().toLowerCase();
            //si el usuario no esta en la base 
            if (amigo.equals("0")) {
                limpiarConsola();
                mostrarSolicitudesDeAmizade(p);
            } else {
                if (bd.buscarPerfil(amigo) == null) {
                    System.out.println("Nombre no encontrado");
                } else if (p.getSolicitudes().contains(bd.buscarPerfil(amigo))) {
                    System.out.println("Tienes una solicitud penidente de este perfil");

                } else if (p.getAmigos().contains(bd.buscarPerfil(amigo))) {
                    System.out.println("Ya has anadido a " + amigo);

                } else if (p.getNome().equals(amigo)) {
                    System.out.println("No es posible anadirte a ti mismo");

                } else {
                    System.out.println("Solicitud enviada");
                    Perfil friend = bd.buscarPerfil(amigo);
                    friend.engadirSolicitudDeAmizade(p);
                    amigoEncontrado = true;

                }

            }
        }
        limpiarConsola();
        if (amigoEncontrado == true) {
            System.out.println("Solicitud enviada");
        }
        mostrarSolicitudesDeAmizade(p);
    }

    //ID=mainmenu(4)
    /**
     * Menu que muestra una lista de amigos y nos permite interactuar con ellos
     */
    private void mostrarListaAmigos(Perfil p) {

        String menuOption = "";
        limpiarConsola();
        System.out.println("""
                                 ============================================
                                          ===== Lista de Amig@s =====
                                 ============================================""");
        System.out.println();
        if (p.getAmigos().isEmpty()) {
            System.out.println(" - Aun no tienes amig@s - \n");

        } else {
            mostrarAmigos(p);

            System.out.println("(1) Selecciona un Amig@");
            System.out.println("(2) Menu Principal");

            while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
                System.out.print("Opcion: ");
                menuOption = keyboardString.nextLine();
                if (menuOption.equals("1") && p.getAmigos().isEmpty()) {
                    menuOption = "";
                }
            }

            switch (menuOption) {
                case "1" -> {
                    limpiarConsola();
                    mostrarAmigos(p);
                    seleccionAmigo(p);
                }
                default ->
                    mostrarMenuPrincipal(p);

            }
        }
    }

    /**
     * Muesta los amigos de un perfil dado
     */
    private void mostrarAmigos(Perfil p) {
        for (int i = 0; i < p.getAmigos().size(); i++) {

            System.out.println();
            System.out.println("(" + (i + 1) + ")" + " Nombre:" + "[" + p.getAmigos().get(i).getNome() + "]" + "  Estado:" + "[" + p.getAmigos().get(i).getEstado() + "] \n");

        }

    }

    /**
     * Menu para interactuar con un amigo, enviar mensaje y mostrar biografia
     */
    private void seleccionAmigo(Perfil p) {

        System.out.print("(0) Salir - Amig@: ");
        int chos = keyboardInt.nextInt();
        while (chos < 0 || chos > p.getAmigos().size()) {
            System.out.print("(0) Salir - Amig@: ");
            chos = keyboardInt.nextInt();
        }
        if (chos == 0) {
            mostrarMenuPrincipal(p);
        } else {

            interaccionAmigo(p.getAmigos().get(chos - 1));
        }

    }

    /**
     * Menu que nos permite enviar un mensaje o mostrar la biografia de un
     * perfil dado
     */
    private void interaccionAmigo(Perfil amigo) {
        String menuOption;

        System.out.println("(1) Enviar mensaje");
        System.out.println("(2) Mostrar biografia");
        System.out.println("(3) Atras");
        menuOption = keyboardString.nextLine();
        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
        }

        switch (menuOption) {
            case "1" -> {

                escribirMensaxe(login, amigo);
                mostrarMenuPrincipal(login);

            }

            case "2" -> {

                mostrarBiografia(amigo);

            }
            default -> {
                mostrarMenuPrincipal(login);
            }
        }

    }

    //ID=mainmenu(5)
    /**
     * Muestra menu mensajes de un perfil
     */
    private void mostrarMensaxes(Perfil p) {

        limpiarConsola();

        if (p.getMensajes().isEmpty()) {
            System.out.println("* - No tienes mensajes - *");

        } else {

            mostrarMensaxesRecibidos(p);

        }
        System.out.println();
        System.out.println("(1) Interactuar con mensaje");
        System.out.println("(2) Menu Principal");
        System.out.print("Opcion: ");
        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
            menuOption = keyboardString.nextLine();
            if (menuOption.equals("1") && p.getMensajes().isEmpty()) {
                System.out.println("* - No tienes mensajes - *");
                menuOption = "0";
            }
            System.out.print("Opcion: ");
        }
        if ("2".equals(menuOption)) {
            mostrarMenuPrincipal(p);
        } else {
            System.out.println("Selecciona un mensaje");

            int mensajeSeleccion = -1;

            while (mensajeSeleccion < 1 || mensajeSeleccion > p.getMensajes().size()) {

                mensajeSeleccion = keyboardInt.nextInt();
            }
            menuOption = "";
            System.out.println("(1) Marcar como leida");
            System.out.println("(2) Eliminar mensaxe");
            System.out.println("(3) Responder a mensaxe");

            while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
                System.out.print("Opcion: ");
                menuOption = keyboardString.nextLine();
            }

            mensajeSeleccion = p.getMensajes().size() - mensajeSeleccion;

            switch (menuOption) {

                case "1" -> {
                    p.getMensajes().get(mensajeSeleccion).setLido(true);
                    mostrarMensaxes(p);

                }

                case "2" -> {
                    p.eliminarMensaxe(p, p.getMensajes().get(mensajeSeleccion));
                    mostrarMensaxes(p);
                }
                default -> {
                    escribirMensaxe(p, p.getMensajes().get(mensajeSeleccion).getRemitente());
                    mostrarMensaxes(login);

                }

            }

        }
    }

    /**
     * Recorre el array de mensajes de un perfil y los muestra
     */
    private void mostrarMensaxesRecibidos(Perfil p) {

        for (int i = p.getMensajes().size() - 1; i >= 0; i--) {

            //fecha y hora publicacion
            System.out.print(" - " + p.getMensajes().get(i).getData().format(DateTimeFormatter.ISO_DATE));
            System.out.print(" a las ");
            System.out.println(p.getMensajes().get(i).getTime().truncatedTo(ChronoUnit.SECONDS) + " - ");

            System.out.println((p.getMensajes().size() - i) + ": " + p.getMensajes().get(i).getTexto());

            System.out.println("Escrito por: " + p.getMensajes().get(i).getRemitente().getNome());
            if (p.getMensajes().get(i).isLido() == false) {
                System.out.println("No leido");
            } else {
                System.out.println("Leido");
            }
        }

    }

    /**
     * Entrada de datos enviar mensaje privado
     */
    private void escribirMensaxe(Perfil remitente, Perfil destinatario) {

        System.out.println("Escribe el contenido del mensaje");
        String contenido = keyboardString.nextLine();
        Mensaxe m = new Mensaxe(contenido, remitente);
        destinatario.engadirMensaxePrivada(m);

        System.out.println("Mensaje enviado");

    }

    /**
     * Muestra el menu eventos el cual nos permite añadir eventos, interactuar
     * con ellos y revisar invitaciones pendientes
     */
    private void mostrarEventos(Perfil p) {
        limpiarConsola();
        System.out.println("""
                                 ============================================
                                             ===== Eventos =====
                                 ============================================""");

        if (p.getEventos().isEmpty()) {

            System.out.println("\n* - Aun no tienes Eventos - *\n");

        } else {
            mostrarEventosAsistencia(p, p.getEventos());
        }
        System.out.println("");
        System.out.println("(1) Anadir Evento");
        System.out.println("(2) Seleccionar Evento");
        System.out.println("(3) Invitaciones Pendientes(" + p.getSolicitudesEventos().size() + ")");
        System.out.println("(4) Atras");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption) && !"4".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
            if (menuOption.equals("2") && p.getEventos().isEmpty()) {
                System.out.println("* - Aun no tienes Eventos - *");
                menuOption = "";
            }
            if (menuOption.equals("3") && p.getSolicitudesEventos().isEmpty()) {
                System.out.println("* - No tienes Invitaciones - *");
                menuOption = "";
            }
        }

        switch (menuOption) {
            case "1" -> {

                p.engadirEvento(mostrarCrearEvento());

                mostrarEventos(login);
            }
            case "2" -> {

                interaccionEvento(p, interactuarInvitacionEventos(p.getEventos()));
                mostrarEventos(login);
            }

            case "3" -> {
                limpiarConsola();
                invitacionesPendientes(p);
            }

            default -> {
                mostrarMenuPrincipal(p);
            }
        }

    }

    /**
     * Permite interactuar con las invitaciones a eventos de un perfil dado.
     */
    private void invitacionesPendientes(Perfil p) {

        mostrarEventosAsistencia(p, p.getSolicitudesEventos());
        System.out.println("Que evento quieres revisar?");
        Evento evento = interactuarInvitacionEventos(p.getSolicitudesEventos());

        System.out.println("¿Quieres asistir?");
        System.out.println("(1) Aceptar Invitacion");
        System.out.println("(2) Rechazar");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();

        }
        if (menuOption.equals("1")) {
            p.getEventos().add(evento);
            p.eliminarSolicitudEvento(evento);
            evento.anadirAsistente(p);
            evento.eliminarPendientes(p);

        } else {
            p.eliminarSolicitudEvento(evento);
            evento.eliminarPendientes(p);

        }
        mostrarEventos(p);
    }

    /**
     * Entrada de datos para crear un evento y lo crea.
     */
    private Evento mostrarCrearEvento() {

        //para crear metodo añadir evento
        String titulo;
        String descripcion;

        String fecha;
        String hora;
        System.out.println();
        System.out.println("Titulo Evento:");
        titulo = keyboardString.nextLine();
        System.out.println("Descripcion :");
        descripcion = keyboardString.nextLine();
        System.out.print("Dia: ");
        fecha = keyboardString.nextLine() + "-";
        System.out.print("Mes: ");
        fecha = fecha + keyboardString.nextLine() + "-";
        System.out.print("Ano: ");
        fecha = fecha + keyboardString.nextLine();
        System.out.print("Hora: ");
        hora = keyboardString.nextLine() + ":";
        System.out.print("Minutos: ");
        hora += keyboardString.nextLine();

        Evento evento = new Evento(login, titulo, descripcion, fecha, hora);

        return evento;
    }

    /**
     * muestra los enventos a los que asiste un perfil
     */
    private void mostrarEventosAsistencia(Perfil p, List<Evento> evento) {
        for (int i = (evento.size() - 1); i >= 0; i--) {
            // numero de evento
            System.out.println(("      - - - (" + (evento.size() - i) + ") - - -"));
            //fecha y hora publicacion
            System.out.print("  - " + evento.get(i).getFecha());
            System.out.print(" a las ");
            System.out.println(evento.get(i).getHora() + " -");

            //autor publicacion
            if (evento.get(i).getAutor() == p) {
                System.out.println("Creador: Creado por ti");
            } else {
                System.out.println("Creador: " + evento.get(i).getAutor().getNome());
            }

            //Titulo y texto publicacion
            System.out.println("Titulo: " + evento.get(i).getTitulo());
            System.out.println("Descripcion: " + evento.get(i).getDescripcion());
            if (evento.get(i).getAsistentes().isEmpty()) {
                System.out.println("Asistentes: No hay asistentes");
            } else {
                System.out.print("Asistentes Confirmados:");
                for (int j = 0; j < evento.size(); j++) {
                    System.out.println(" " + evento.get(i).getAsistentes().get(j).getNome());
                }

            }
            if (evento.get(i).getPendientes().isEmpty()) {
                System.out.println("Asistentes Pendientes: No hay pendientes");
            } else {
                System.out.print("Asistentes Pendientes:");
                for (int j = 0; j < evento.size(); j++) {
                    System.out.println(" " + evento.get(i).getPendientes().get(j).getNome());
                }
                System.out.println("");
            }
        }
    }

    /**
     * Nos permite invitar amigos a un evento.
     */
    private void interaccionEvento(Perfil p, Evento evento) {

        String menuOption = "";

        limpiarConsola();
        System.out.println("Evento: " + evento.getTitulo().toUpperCase() + "\n");
        System.out.println("(1) Invitar amig@s");
        System.out.println("(2) Eliminar");
        System.out.println("(3) Atras");

        while (!"1".equals(menuOption) && !"2".equals(menuOption) && !"3".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
        }

        switch (menuOption) {
            case "1" -> {

                System.out.println("(0)Salir - Amig@: ");
                String amigo = keyboardString.nextLine().toLowerCase();
                while (!p.getAmigos().contains(bd.buscarPerfil(amigo)) && !amigo.equals("0")) {
                    System.out.println("No tienes a " + amigo + " como amig@");
                    amigo = keyboardString.nextLine().toLowerCase();

                }
                if (!amigo.equals("0")) {
                    evento.anadirPendientes(bd.buscarPerfil(amigo));
                    bd.buscarPerfil(amigo).engadirSolicitudEvento(evento);

                }
                mostrarEventos(p);
            }

            case "2" -> {
                if (evento.getAutor().equals(p)) {
                    System.out.println("Vas a eliminar: " + evento.getTitulo().toUpperCase());
                } else {
                    System.out.println("Vas a cancelar tu asistencia a " + evento.getTitulo().toUpperCase());
                }
                System.out.println("¿Estas seguro?");
                System.out.println("(1) Aceptar");
                System.out.println("(2) Rechazar");

                menuOption = keyboardString.nextLine();
                while (menuOption.equals("1") && menuOption.equals("2")) {
                    menuOption = keyboardString.nextLine();
                }

                if (menuOption.equals("1")) {

                    if (evento.getAutor().equals(p)) {
                        limpiarConsola();
                        p.eliminarEvento(evento);
                        //Eliminar solicitudes pendientes
                        for (int i = 0; i < evento.getPendientes().size(); i++) {
                            evento.getPendientes().get(i).getEventos().remove(evento);
                        }
                        //Eliminar evento en los Asistentes
                        for (int i = 0; i < evento.getAsistentes().size(); i++) {
                            evento.getAsistentes().get(i).getEventos().remove(evento);
                        }

                        System.out.println("Evento Eliminado");
                    } else {

                        limpiarConsola();
                        evento.eliminarAsistente(p);
                        p.getEventos().remove(evento);
                        System.out.println("Has cancelado tu asistencia");
                    }
                }
            }
        }

        mostrarEventos(p);

    }

    /**
     * Nos permite elegir un evento de una lista de ellos
     */
    private Evento interactuarInvitacionEventos(List<Evento> eventos) {

        int posicionEvento = -1;

        //posicionEvento nos sirve para invertir la lista y ahorrar trabajo de ordenar continuamente
        while (posicionEvento < 0 || posicionEvento > eventos.size()) {
            System.out.print("Evento:");
            posicionEvento = keyboardInt.nextInt();
        }
        Evento evento = eventos.get(eventos.size() - posicionEvento);

        return evento;
    }

    /**
     * Muestra las recomendaciones de un perfil dado y nos permite elegirla
     */
    private void recomendaciones(Perfil p) {
        ArrayList<Perfil> recomendados = null;
        if (p.getAmigos().isEmpty()) {
            System.out.println("No podemos sugerirte a nadie");

        } else {

            recomendados = comprobarRecomendaciones(p);
//            if (!recomendados.isEmpty()) {
//
//            }
        }
        System.out.println("(1) Elegir recomendacion");
        System.out.println("(2) Menu principal");

        String menuOption = "";
        while (!"1".equals(menuOption) && !"2".equals(menuOption)) {
            System.out.print("Opcion: ");
            menuOption = keyboardString.nextLine();
        }

        switch (menuOption) {
            case "1" -> {
                if (recomendados == null) {
                    System.out.println("No tienes recomendaciones");
                    recomendaciones(p);

                } else {

                    anadirRecomendado(p, recomendados);

                }

            }

            default -> {
                mostrarMenuPrincipal(p);
            }
        }

    }

    /**
     * Comprueba si hay recomendaciones de perfiles, las muestra y las guarda en
     * un array que retornamos para utilizarlo en otros metodos .
     */
    private ArrayList comprobarRecomendaciones(Perfil p) {
        ArrayList<Perfil> recomendados = new ArrayList();
        int i = 0;
        Perfil amigo;
        Perfil recomendado;
        //Mientras no tengas amigos no tienes recomendaciones 
        System.out.println("""
                                 ============================================
                                   ===== Personas que quizas conozcas =====
                                 ============================================""");
        while (i < 15) {

//            i < login.getAmigos().size()
            //Necsitas un random para el perfil amigo y otro para la recomendacion
            int random = (int) (Math.random() * p.getAmigos().size());
            int random2 = (int) (Math.random() * p.getAmigos().get(random).getAmigos().size());

            //Guardamos a nuestro amigo en una variable tipo Perfil y la recomendacion en otra
            amigo = p.getAmigos().get(random);
            recomendado = amigo.getAmigos().get(random2);
            //Si el perfil recomendado esta dentro de los amigos del perfil Login o es el propio login o ya ha sido recomendado
            if (!p.getAmigos().contains(recomendado) && !recomendado.getNome().equals(p.getNome()) && !recomendados.contains(recomendado) && !recomendado.getSolicitudes().contains(p)) {
                recomendados.add(recomendado);
                System.out.println("-" + (recomendados.indexOf(recomendado) + 1) + "- " + recomendado.getNome());

            }

            i++;

        }
        return recomendados;

    }

    /**
     * Envia solicitud a un perfil que indiquemos de un array de perfiles.
     */
    private void anadirRecomendado(Perfil p, ArrayList<Perfil> lista) {

        System.out.println();
        System.out.println("Pulsa el numero de la recomendacion o 0 para salir");
        int chos = keyboardInt.nextInt();
        if (chos == 0) {
            mostrarMenuPrincipal(p);
        }
        while (chos > lista.size()) {
            chos = keyboardInt.nextInt();
        }

        lista.get(chos - 1).engadirSolicitudDeAmizade(p);
        System.out.println("Solicitud enviada");
        anadirRecomendadoPlus(p, lista);
    }

    /**
     * Muestra menu adicional anadir otro recomendado o recargar los
     * recomendados.
     */
    private void anadirRecomendadoPlus(Perfil p, ArrayList<Perfil> lista) {

        System.out.println("(1) Elegir otra recomendacion");
        System.out.println("(2) Refrescar recomendaciones");
        System.out.println("(3) Menu principal");
        int chos = keyboardInt.nextInt();
        while (chos < 0 || chos > 3) {
            chos = keyboardInt.nextInt();
        }
        switch (chos) {
            case 1 -> {
                anadirRecomendado(p, lista);
            }

            case 2 -> {
                recomendaciones(p);

            }

            default -> {
                mostrarMenuPrincipal(p);
            }

        }

    }
}
