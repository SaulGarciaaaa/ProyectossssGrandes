/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.ej1bd.prg;

import java.util.List;


/**
 *
 * @author salsa
 * @param <T>
 */
public interface Repositorio<T> {
    public  List<T> listar();
    public T id(int id);
    public void guarda(T t);
    public void borrar(int id);
}
