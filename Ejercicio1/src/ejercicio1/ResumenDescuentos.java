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
public class ResumenDescuentos {
    private double totalOriginal;
    private double totalFinal;
    private List<DescuentoAplicado> descuentosAplicados = new ArrayList<>();

    public ResumenDescuentos(double totalOriginal) {
        this.totalOriginal = totalOriginal;
        this.totalFinal = totalOriginal;
    }

    public void aplicarDescuento(DescuentoAplicado descuento) {
        descuentosAplicados.add(descuento);
        totalFinal -= descuento.getMontoDescontado();
    }

    public double getTotalOriginal() { return totalOriginal; }
    public double getTotalFinal() { return totalFinal; }
    public List<DescuentoAplicado> getDescuentosAplicados() { return descuentosAplicados; }
}
