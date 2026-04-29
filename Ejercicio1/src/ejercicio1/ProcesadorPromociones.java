/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alvaro
 */
public class ProcesadorPromociones {
    private ReglaAcumulacion reglaAcumulacion;
    private PriorityStrategy priorityStrategy;
    
    public ProcesadorPromociones() {
        this.reglaAcumulacion = new AcumulacionPorPromocion();
        this.priorityStrategy = new MayorDescuentoStrategy();
    }
    
    public ProcesadorPromociones(ReglaAcumulacion reglaAcumulacion, PriorityStrategy priorityStrategy) {
        this.reglaAcumulacion = reglaAcumulacion;
        this.priorityStrategy = priorityStrategy;
    }
    
    public ResumenDescuentos aplicarPromociones(List<Promocion> promociones, CarritoContexto contexto) {
  
        if (promociones == null || promociones.isEmpty()) {
            return new ResumenDescuentos(contexto.getTotalSinDescuentos());
        }
        
        List<PromocionAplicable> aplicables = new ArrayList<>();
        for (Promocion promocion : promociones) {
            DescuentoAplicado descuento = promocion.calcularDescuento(contexto);
            if (descuento.getMontoDescontado() > 0) {
                aplicables.add(new PromocionAplicable(promocion, descuento));
            }
        }
        
        if (aplicables.isEmpty()) {
            return new ResumenDescuentos(contexto.getTotalSinDescuentos());
        }
        
        priorityStrategy.ordenar(aplicables);
        
        List<PromocionAplicable> seleccionadas = seleccionarPromocionesGreedy(aplicables);
        
        ResumenDescuentos resumen = new ResumenDescuentos(contexto.getTotalSinDescuentos());
        for (PromocionAplicable seleccionada : seleccionadas) {
            resumen.aplicarDescuento(seleccionada.getDescuento());
        }
        
        return resumen;
    }
    

    private List<PromocionAplicable> seleccionarPromocionesGreedy(List<PromocionAplicable> aplicables) {
        List<PromocionAplicable> seleccionadas = new ArrayList<>();
        
        for (PromocionAplicable candidata : aplicables) {
            boolean puedeAplicar = true;
            
            for (PromocionAplicable seleccionada : seleccionadas) {
                if (!reglaAcumulacion.sePuedenAcumular(
                        candidata.getPromocion(), 
                        seleccionada.getPromocion())) {
                    puedeAplicar = false;
                    break;
                }
            }
            
            if (puedeAplicar) {
                seleccionadas.add(candidata);
            }
        }
        
        return seleccionadas;
    }
    

    private List<PromocionAplicable> seleccionarPromocionesOptimas(List<PromocionAplicable> aplicables) {
        List<PromocionAplicable> mejorSeleccion = new ArrayList<>();
        double[] maxDescuento = {0};
        
        backtracking(aplicables, 0, new ArrayList<>(), mejorSeleccion, maxDescuento);
        
        return mejorSeleccion;
    }
    
    private void backtracking(List<PromocionAplicable> aplicables, 
                              int index, 
                              List<PromocionAplicable> actual, 
                              List<PromocionAplicable> mejorSeleccion,
                              double[] maxDescuento) {
        if (index >= aplicables.size()) {
            double descuentoActual = calcularDescuentoTotal(actual);
            if (descuentoActual > maxDescuento[0]) {
                maxDescuento[0] = descuentoActual;
                mejorSeleccion.clear();
                mejorSeleccion.addAll(actual);
            }
            return;
        }
        
        backtracking(aplicables, index + 1, actual, mejorSeleccion, maxDescuento);
        
        PromocionAplicable candidata = aplicables.get(index);
        if (esAcumulableConSeleccionadas(candidata, actual)) {
            actual.add(candidata);
            backtracking(aplicables, index + 1, actual, mejorSeleccion, maxDescuento);
            actual.remove(actual.size() - 1);
        }
    }
    
    private boolean esAcumulableConSeleccionadas(PromocionAplicable candidata, 
                                                   List<PromocionAplicable> seleccionadas) {
        for (PromocionAplicable seleccionada : seleccionadas) {
            if (!reglaAcumulacion.sePuedenAcumular(candidata.getPromocion(), 
                                                   seleccionada.getPromocion())) {
                return false;
            }
        }
        return true;
    }
    
    private double calcularDescuentoTotal(List<PromocionAplicable> promociones) {
        return promociones.stream()
            .mapToDouble(p -> p.getDescuento().getMontoDescontado())
            .sum();
    }
}