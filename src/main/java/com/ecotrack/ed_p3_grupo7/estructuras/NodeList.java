/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.estructuras;
/**
 *
 * @author arianamoreira
 */
public class NodeList<E> {
    private E contenido;
    private NodeList<E> siguiente;
    private NodeList<E> anterior;
    
    public NodeList(E contenido){
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }
    
    public NodeList(E contenido, NodeList<E> siguiente, NodeList<E> anterior){
        this.contenido = contenido;
        this.siguiente = siguiente;
        this.anterior = anterior;
    }
    
    public E getContenido(){
        return contenido;
    }
    
    public void setContenido(E contenido){
        this.contenido = contenido;
    }
    
    public NodeList<E> getSiguiente(){
        return siguiente;
    }
    
    public void setSiguiente(NodeList<E> siguiente){
        this.siguiente = siguiente;
    }
    
    public NodeList<E> getAnterior(){
        return anterior;
    }
    
    public void setAnterior(NodeList<E> anterior){
        this.anterior = anterior;
    }
}
