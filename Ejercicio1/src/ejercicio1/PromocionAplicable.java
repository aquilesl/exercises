/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio1;

/**
 *
 * @author Alvaro
 */
public class PromocionAplicable {
        private Promocion promocion;
        private DescuentoAplicado descuento;
        
        public PromocionAplicable(Promocion promocion, DescuentoAplicado descuento) {
            this.promocion = promocion;
            this.descuento = descuento;
        }
        
        public Promocion getPromocion() { return promocion; }
        public DescuentoAplicado getDescuento() { return descuento; }
    }
