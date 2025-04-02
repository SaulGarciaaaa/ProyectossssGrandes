/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ej1bd.prg;


/**
 *
 * @author salsa
 */
public class Ej1BDPRG {

    public static void main(String[] args) {
        int opcion;
        CrearBaseDatos CBD=new CrearBaseDatos();
        CBD.crearBasedeDatos();
        CBD.crearTablas();
        UsuarioDAOImp UDAO=new UsuarioDAOImp(); 
        do{
            System.out.println("Gestion de usuarios");
            System.out.println("1. Actualizar");
            System.out.println("2. Eliminar");
            System.out.println("3. Agregar");
            System.out.println("4. Listar");
            System.out.println("5. Salir");
            opcion=Teclado.compNum("Introduce una opcion");
            switch(opcion){
                case 1->{
                    int id=Teclado.compNum("Introduce la id a actualizar");
                    if (UDAO.existeUsuario(id)) {
                       Usuarios U1=UDAO.id(id);
                    }else{
                        System.out.println("El usuario con esa id no existe");
                    }
                }
                case 2->
                    UDAO.borrar(Teclado.compNum("Introduce la id a eliminar"));
                case 3->
                    UDAO.guarda(new Usuarios());
                case 4->{
                    for (Usuarios usuario: UDAO.listar()) {
                        System.out.println(usuario.toString());
                    }   
                }
                case 5->
                    System.out.println("Saliendo del programa");
                default->
                     System.out.println("Introduce una opcion valida 1-5");
            }
        }while(opcion!=5);
    }
}
