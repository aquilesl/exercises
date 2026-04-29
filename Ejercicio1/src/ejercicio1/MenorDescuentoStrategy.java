/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.List;

/**
 *
 * @author Alvaro
 */
public class MenorDescuentoStrategy implements PriorityStrategy {
    @Override
    public void ordenar(List<PromocionAplicable> aplicables) {
        aplicables.sort((p1, p2) -> 
            Double.compare(p1.getDescuento().getMontoDescontado(), 
                          p2.getDescuento().getMontoDescontado()));
    }
}
