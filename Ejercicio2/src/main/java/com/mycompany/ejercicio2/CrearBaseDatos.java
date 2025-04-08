/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author salsa
 */
public class CrearBaseDatos {
    public Connection getConnection(){
    return AccesoBaseDatos.getInstance().getConn();
    }
    public void crearBaseDatos(){
        try(Statement stmt=getConnection().createStatement()){
            String sql="create database if not exists java_02_clinica";
            stmt.executeUpdate(sql);
            System.out.println("Base datos creada");
        }catch(SQLException a){
            System.out.println("Error en la consulta " + a.getMessage());
        }
    }
    public void crearTablas(){
       try(Statement stmt =getConnection().createStatement()){
           String sql = "USE java_02_clinica";
             stmt.executeUpdate(sql);
            sql="Create table if  not exists pacientes("
                   + " dni varchar(9) not null"
                   + ", nombre varchar(15) not null"
                   + ", telefono varchar(9) not null"
                   + ", Primary key(dni))ENGINE=InnoDB";
            stmt.executeUpdate(sql);
            System.out.println("Tabla insertada");
             sql="Create table if  not exists visitas("
                   + " id int not null auto_increment"
                   + ", paciente varchar(9) not null"
                   + ", fecha Date not null"
                   + ", tratamiento enum('Implantologia','Ortodoncia','Periodoncia','Estetica','Extracciones') not null"
                   +", observaciones varchar(50) not null" 
                   + ", Primary key(id)"
                     + ", CONSTRAINT fk_pacientesvisitas FOREIGN KEY (paciente) REFERENCES pacientes (dni))ENGINE=InnoDB";
            stmt.executeUpdate(sql);
            System.out.println("Tablas creadas correctamnete");
       }catch(SQLException a){
            System.out.println("Error en la consulta " + a.getMessage());
        }
    }
}
