/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecotrack.ed_p3_grupo7.logica;

import com.ecotrack.ed_p3_grupo7.datos.Residuo;
import java.util.Comparator;

/**
 *
 * @author arianamoreira
 */
public class ComparadorPorPrioridad implements Comparator<Residuo> {
    @Override
    public int compare(Residuo r1, Residuo r2){
        int p1 = r1.getNivelPrioridad();
        int p2 = r2.getNivelPrioridad();
        return p2-p1;
    }
}
