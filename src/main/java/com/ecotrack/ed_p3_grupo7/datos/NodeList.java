/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.datos;

/**
 *
 * @author arianamoreira
 */
public class NodeList<E> {
    private E contenido;
    private NodeList<E> siguiente;
    
    public NodeList(E contenido){
        this.contenido = contenido;
        this.siguiente = null;
    }
    
    public E getContenido(){
        return contenido;
    }
    
    public E setContenido(E contenido){
        this.contenido = contenido;
        return null;
    }
    
    public NodeList<E> getSiguiente(){
        return siguiente;
    }
    
    public void setSiguiente(NodeList<E> siguiente){
        this.siguiente = siguiente;
    }
}
