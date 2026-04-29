/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

/**
 *
 * @author Alvaro
 */
public class GaugeWidget extends SimpleWidget {
    public GaugeWidget(String id, DataSource dataSource) {
        super(id, dataSource);
    }
    
    @Override
    public void render() {
        System.out.println("Render gauge " + id + ": " + currentValue);
    }
}
