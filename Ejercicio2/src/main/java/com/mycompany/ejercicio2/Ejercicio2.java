/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ejercicio2;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author salsa
 */
public class Ejercicio2 {

    public static void main(String[] args) {
        MetodosBD bd = new MetodosBD();
        int opcion;
        do {
            System.out.println("1. Insertar paciente");
            System.out.println("2. Crear visita");
            System.out.println("3. Mostrar agenda del día");
            System.out.println("4. Mostrar visitas de un paciente");
            System.out.println("5. Salir");
            opcion = Integer.parseInt(Teclado.scanner.nextLine());

            switch (opcion) {
                case 1 -> bd.insertarPaciente(new Paciente(Teclado.leerDNI(), Teclado.leerNombre(), Teclado.leerTelefono()));
                case 3 -> {
                    System.out.print("Fecha (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(Teclado.scanner.nextLine());
                    List<Visita> visitas = bd.obtenerVisitasPorFecha(fecha);
                    visitas.forEach(System.out::println);
                }
            }
        } while (opcion != 5);
    } 
}
