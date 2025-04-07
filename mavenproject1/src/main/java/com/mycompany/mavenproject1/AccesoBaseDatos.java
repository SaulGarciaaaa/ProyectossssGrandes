/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author salsa
 */
public class AccesoBaseDatos {
    private Connection conn = null;
    private static String BD = "java_03_verano";
    private static String USUARIO = "root";
    private static String CLAVE = "mysql";
    private static String URL = "jdbc:mysql://localhost:3307/" + BD;

    private AccesoBaseDatos() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn = (Connection) DriverManager.getConnection(URL, properties);
            if (conn == null) {
                DriverManager.getConnection(URL, properties);
            } else {
                System.out.println("Conexion correcta a: " + URL);
            }
        } catch (SQLException a) {
            System.out.println("SQLException: " + a.getMessage());
            System.out.println("SQLState: " + a.getSQLState());
            System.out.println("VendorError: " + a.getErrorCode());
        }
    }

    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }

    private static class AccesoBaseDatosHolder {
        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }

    public Connection getConn() {
        return conn;
    }
    public boolean cerrar(){
        boolean siCerrada=false;
        try{
           conn.close();
        if (conn.isClosed()) {
                siCerrada=true;
            }
        }catch(SQLException a){
             System.out.println("Se produjo un error en el cierre"); 
        }
        return siCerrada;
    }
}
