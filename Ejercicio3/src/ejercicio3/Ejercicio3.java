/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alvaro
 */
public class Ejercicio3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Map<String, Object> config2 = new HashMap<>();
        config2.put("type", "panel");
        config2.put("id", "main");

        List<Map<String, Object>> children = new ArrayList<>();

        Map<String, Object> chart1 = new HashMap<>();
        chart1.put("type", "chart");
        chart1.put("id", "chart1");
        chart1.put("sourceId", "cpu_usage");
        children.add(chart1);

        Map<String, Object> counter1 = new HashMap<>();
        counter1.put("type", "counter");
        counter1.put("id", "counter1");
        counter1.put("sourceId", "requests_total");
        children.add(counter1);

        Map<String, Object> subpanel2 = new HashMap<>();
        subpanel2.put("type", "panel");
        subpanel2.put("id", "subpanel");

        List<Map<String, Object>> subChildren = new ArrayList<>();
        Map<String, Object> table1 = new HashMap<>();
        table1.put("type", "table");
        table1.put("id", "table1");
        table1.put("sourceId", "error_logs");
        subChildren.add(table1);
        subpanel2.put("children", subChildren);

        children.add(subpanel2);
        config2.put("children", children);

       
        //datasource
        DataSourceRegistry dataSourceRegistry = new DataSourceRegistry();
        dataSourceRegistry.registerDataSource(new ConcreteDataSource("cpu_usage"));
        dataSourceRegistry.registerDataSource(new ConcreteDataSource("requests_total"));
        dataSourceRegistry.registerDataSource(new ConcreteDataSource("error_logs"));

        // factory
        WidgetFactoryRegistry factoryRegistry = new WidgetFactoryRegistry();
        factoryRegistry.registerFactory("chart", new ChartWidgetFactory());
        factoryRegistry.registerFactory("table", new TableWidgetFactory());
        factoryRegistry.registerFactory("counter", new CounterWidgetFactory());

        // make dahsboard
        DashboardBuilder builder = new DashboardBuilder(factoryRegistry, dataSourceRegistry);
        Widget dashboard = builder.buildFromConfig(config2); // o config

        dashboard.render();

        // updating data
        DataSource cpuSource = dataSourceRegistry.getDataSource("cpu_usage");
        ((ConcreteDataSource) cpuSource).updateValue(new DataValue(85.5));

        
    }
    
}
