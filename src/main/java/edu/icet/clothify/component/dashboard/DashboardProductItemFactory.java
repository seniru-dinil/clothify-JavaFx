package edu.icet.clothify.component.dashboard;

import javafx.scene.layout.HBox;

public class DashboardProductItemFactory {
    private static DashboardProductItemFactory instance;
    private DashboardProductItemFactory(){}
    public static DashboardProductItemFactory getInstance(){
        if (instance==null)instance=new DashboardProductItemFactory();
        return instance;
    }

    public HBox createProductItem(String productName, Long totalQuantity, double v, String productImagePath) {
        return new HBox();
    }
}
