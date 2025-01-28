package edu.icet.clothify.component;

import edu.icet.clothify.dto.Product;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShoppingCartService {

    private static ShoppingCartService instance;
    private VBox cartContainer;


    public static ShoppingCartService getInstance(){
        return instance==null?instance= new ShoppingCartService():instance;
    }

    public void initializeCartContainer(VBox cartContainer){
        this.cartContainer=cartContainer;
    }

    public void addToCartContainer(Product product){
        HBox cartItem = CartItemFactory.getInstance().createCartItem(product);
        cartContainer.getChildren().add(cartItem);
    }

}
