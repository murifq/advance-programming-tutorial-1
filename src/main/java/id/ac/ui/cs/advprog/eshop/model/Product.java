package  id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    static int staticId;
    private String productId;
    private String productName;
    private int productQuantity;

    public int setProductId(){
        this.productId = Integer.toString(Product.staticId++);
        return Product.staticId;
    }

    public String getProductId(){
        return this.productId;
    }

    public void setProductAttribute(Product productParameter){
        this.productName = productParameter.productName;
        this.productQuantity = productParameter.productQuantity;
    }
}