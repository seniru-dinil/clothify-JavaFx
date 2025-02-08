package edu.icet.clothify.component.cart;

import edu.icet.clothify.dto.CartHelper;
import edu.icet.clothify.dto.Product;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

public class ShoppingCartService {
    @Getter
    @Setter
    private List<CartHelper> cartHelper;

    private static ShoppingCartService instance;
    private VBox cartContainer;
    private Label totLabel;

    private ShoppingCartService() {
        cartHelper = new ArrayList<>();
    }

    public static ShoppingCartService getInstance() {
        return instance == null ? instance = new ShoppingCartService() : instance;
    }

    public void initializeFXModels(VBox cartContainer, Label totLabel) {
        this.totLabel = totLabel;
        this.cartContainer = cartContainer;
    }

    public void addToCartContainer(Product product) {
        checkifExists(product);
        addToCart();
    }

    public void addToCart() {
        setTotal();
        cartContainer.getChildren().clear();
        for (CartHelper i : cartHelper) {
            HBox cartItem = CartItemFactory.getInstance().createCartItem(i);
            cartContainer.getChildren().add(cartItem);
        }
    }

    private void checkifExists(Product product) {
        boolean bool = true;
        for (CartHelper i : cartHelper) {
            if (i.getProduct().equals(product)) {
                i.setQuantity(i.getQuantity() + 1);
                bool = false;
            }
        }
        if (bool) {
            cartHelper.add(new CartHelper(product, 1));
        }
    }

    public void deleteFromCart(CartHelper helper) {
        cartHelper.remove(helper);
        addToCart();
    }

    public Double getTotalPrice() {
        Double total =0.0;
        for (CartHelper i : cartHelper) {
            total += i.getProduct().getProductPrice()*i.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartContainer.getChildren().clear();
        totLabel.setText("");
        cartHelper.clear();
    }

    public void setTotal(){
        totLabel.setText(getTotalPrice()+"");
    }

}
