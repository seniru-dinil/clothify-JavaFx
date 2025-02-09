package edu.icet.clothify.component.dashboard;

import javafx.scene.layout.HBox;

public class DashboardCustomerItemFactory {
    private static DashboardCustomerItemFactory instance;
    private DashboardCustomerItemFactory(){}
    public static DashboardCustomerItemFactory getInstance(){
        if (instance==null)instance=new DashboardCustomerItemFactory();
        return instance;
    }

    public HBox createEmployeeItem(String s, Double totalOrderValue) {
        return new HBox();
    }
}
