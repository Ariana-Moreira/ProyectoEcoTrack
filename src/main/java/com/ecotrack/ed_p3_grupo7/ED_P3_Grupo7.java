/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ecotrack.ed_p3_grupo7;

import com.ecotrack.ed_p3_grupo7.logica.SistemaEcoTrack;
import com.ecotrack.ed_p3_grupo7.vista.VentanaPrincipal;

/**
 *
 * @author arianamoreira
 */
public class ED_P3_Grupo7 {

    public static void main(String[] args) {
        //Comienza la logica del sistema
        SistemaEcoTrack sistema = new SistemaEcoTrack();
        
        //Se crea la ventana para ver interfaz grafica
        VentanaPrincipal ventana = new VentanaPrincipal(sistema);
        
        // Hace visible la ventana
        ventana.setVisible(true);
    }
}
