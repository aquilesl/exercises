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
public class Promocion2x1 implements Promocion {
    private List<String> skusAplicables;
    private boolean acumulable;

    public Promocion2x1(List<String> skusAplicables, boolean acumulable) {
        this.skusAplicables = skusAplicables;
        this.acumulable = acumulable;
    }

    @Override
    public String getNombre() {
        return "Promoción 2x1 en productos seleccionados!";
    }

    @Override
    public DescuentoAplicado calcularDescuento(CarritoContexto contexto) {
        double descuentoTotal = 0.0;
        for (ItemCarrito item : contexto.getItems()) {
            if (skusAplicables.contains(item.getProducto().getSku())) {
                int paresGratis = item.getCantidad() / 2;
                double precioUnitario = item.getProducto().getPrecioUnitario();
                descuentoTotal += paresGratis * precioUnitario;
            }
        }
        return new DescuentoAplicado(getNombre(), descuentoTotal,
            "Se aplicó 2x1: llevando 2, pagás 1. Ahorro: $" + descuentoTotal);
    }

    @Override
    public boolean esAcumulableCon(Promocion otra) {
        return acumulable;
    }
}
