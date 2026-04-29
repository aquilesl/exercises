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
public class EnvioGratisPremium implements Promocion {
    private double costoEnvio;
    private boolean acumulable;

    public EnvioGratisPremium(double costoEnvio, boolean acumulable) {
        this.costoEnvio = costoEnvio;
        this.acumulable = acumulable;
    }

    @Override
    public String getNombre() {
        return "Envío gratis para clientes premium";
    }

    @Override
    public DescuentoAplicado calcularDescuento(CarritoContexto contexto) {
        if (contexto.isEsClientePremium()) {
            return new DescuentoAplicado(getNombre(), costoEnvio,
                "Cliente premium: envío gratis ($" + costoEnvio + ")");
        }
        return new DescuentoAplicado(getNombre(), 0, "No aplica: cliente no es premium");
    }

    @Override
    public boolean esAcumulableCon(Promocion otra) {
        return acumulable;
    }
}
