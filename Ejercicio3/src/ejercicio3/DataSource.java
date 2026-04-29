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

public interface DataSource {
    String getSourceId();
    void addObserver(WidgetObserver observer);
    void removeObserver(WidgetObserver observer);
    DataValue getCurrentValue();
}
