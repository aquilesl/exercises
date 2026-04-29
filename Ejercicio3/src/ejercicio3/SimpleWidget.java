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
public abstract class SimpleWidget implements Widget, WidgetObserver {
    protected String id;
    protected String sourceId;
    protected DataSource dataSource;
    protected DataValue currentValue;
    
    public SimpleWidget(String id, DataSource dataSource) {
        this.id = id;
        this.dataSource = dataSource;
        this.sourceId = dataSource.getSourceId();
        this.dataSource.addObserver(this);
        this.currentValue = dataSource.getCurrentValue();
    }
    
    @Override
    public void onDataChanged(String sourceId, DataValue newValue) {
        if (this.sourceId.equals(sourceId)) {
            update(newValue);
        }
    }
    
    @Override
    public void update(DataValue newValue) {
        this.currentValue = newValue;
        render();
    }
    
    @Override
    public String getId() { return id; }
}
