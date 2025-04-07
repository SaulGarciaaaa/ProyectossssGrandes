/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.mavenproject1;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author salsa
 */
public class Mavenproject1 {

    public static void main(String[] args) {
        MetodosBD MBD = new MetodosBD();
        int opcion;
        do {
            System.out.println("Actividades");
            System.out.println("1- Consultar actividades");
            System.out.println("2- Inscribirse en actividad");
            System.out.println("3- Anular inscripcion");
            opcion = Teclado.compNum("Introduce una opcion");
            switch (opcion) {
                case 1 -> {
                    List<Actividades> actividades = MBD.consultaractividad();
                    System.out.println("Nombre de las actividades: ");
                    for (Actividades actividad : actividades) {
                        System.out.println(actividad.getNombre());
                    }
                    String nombre = Teclado.compNombre("Introduce el nombre de una de estas actividades");
                    boolean comp = false;
                    Iterator<Actividades> IT = actividades.iterator();
                    while (IT.hasNext() && !comp) {
                        Actividades actividad = IT.next();
                        if (actividad.getNombre().equalsIgnoreCase(nombre)) {
                            System.out.println(actividad.toString());
                            List<Participantes> participantes = MBD.usuariosEnActividad(actividad.getNombre());
                            for (Participantes participante : participantes) {
                                System.out.println(participante.toString());
                                comp = true;
                            }
                        }
                    }
                    if (!comp) {
                        System.out.println("No se encontro ninguna actividad con ese nombre");
                    }
                }
                case 2 -> {
                    List<Participantes> participantes = MBD.consultarUsuarios();
                    String dni = Teclado.validarDNI("Introduce tu dni");
                    boolean comp = false;
                    for (Participantes participante : participantes) {
                        if (participante.getDni().equalsIgnoreCase(dni)) {
                            comp = true;
                            MBD.incripcionactividad(Teclado.compNombre("Introduce el nombre de una de estas actividad ainscribirse"),dni);
                            
                                }
                    }
                    if (!comp) {
                        System.out.println("No se encontro a ninguna persona con ese dni registrada");
                    }
                }
                case 3 -> {
                    List<Participantes> participantes = MBD.consultarUsuarios();
                    String dni = Teclado.validarDNI("Introduce tu dni");
                    boolean comp = false;
                    for (Participantes participante : participantes) {
                        if (participante.getDni().equalsIgnoreCase(dni)) {
                            comp = true;
                    MBD.anularinscripcion(dni,Teclado.compNombre("Introduce el nombre de una de estas actividad ainscribirse"));
                     }
                    }
                }
                case 4 ->
                    System.out.println("Saliendo del programa");

                default ->
                    System.out.println("Introduce una opcion valida");
            }
        } while (opcion != 4);
    }
}
