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
public class DataValue {
    private Object value;
    private long timestamp;

    public DataValue() {
    }

    public DataValue(Object value) {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }
    
}
