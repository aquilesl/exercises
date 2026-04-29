/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alvaro
 */
public class ConcreteDataSource implements DataSource {
    private String id;
    private DataValue currentValue;
    private List<WidgetObserver> observers = new ArrayList<>();
    
    public ConcreteDataSource(String id) {
        this.id = id;
        this.currentValue = new DataValue(0);
    }
    
    public void updateValue(DataValue newValue) {
        this.currentValue = newValue;
        notifyObservers();
    }
    
    private void notifyObservers() {
        for (WidgetObserver observer : observers) {
            observer.onDataChanged(id, currentValue);
        }
    }
    
    @Override
    public void addObserver(WidgetObserver observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(WidgetObserver observer) {
        observers.remove(observer);
    }
    
    @Override
    public DataValue getCurrentValue() { return currentValue; }
    
    @Override
    public String getSourceId() { return id; }
}
