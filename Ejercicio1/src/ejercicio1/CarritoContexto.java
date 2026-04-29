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
public class CarritoContexto {
    private List<ItemCarrito> items;
    private boolean esClientePremium;
    private double totalSinDescuentos;

    public CarritoContexto(List<ItemCarrito> items, boolean esClientePremium) {
        this.items = items;
        this.esClientePremium = esClientePremium;
        this.totalSinDescuentos = items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }


    public List<ItemCarrito> getItems() { return items; }
    public boolean isEsClientePremium() { return esClientePremium; }
    public double getTotalSinDescuentos() { return totalSinDescuentos; }
}
