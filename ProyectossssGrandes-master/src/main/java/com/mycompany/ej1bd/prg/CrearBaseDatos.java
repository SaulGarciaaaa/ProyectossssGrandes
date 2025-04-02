/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1bd.prg;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DAM121
 */
public class CrearBaseDatos {
    private Connection getConnection() {
        return AccesoBaseDatos.getInstance().getConn();
    }
    public void crearBasedeDatos() {
    try(Statement stmt=getConnection().createStatement();){
        String sql=("Create Database if not exists PRG_ejemplo1 ");
        stmt.executeUpdate(sql);
        System.out.println("Database insertada correctamnete");
     }catch(SQLException a){
         System.out.println("Error en la consulta " + a.getMessage());
     }
    }

    public void crearTablas() {
        try(Statement stmt=getConnection().createStatement();) {
            String sql = "USE PRG_ejemplo1";
             stmt.executeUpdate(sql);
             sql = ("Create table if not exists usuarios (id int not null auto_increment"
                    + ", usurname varchar(12) default null"
                    + ", password varchar(60) default null"
                    + ", email varchar(45) default null"
                    + ", PRIMARY KEY(id))ENGINE=InnoDB");
            stmt.executeUpdate(sql);
            System.out.println("Tabla insertada correctamente");
        }catch(SQLException a){
            System.out.println("Error en la consulta " + a.getMessage());
        }
    }
}
