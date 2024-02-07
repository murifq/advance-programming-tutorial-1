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

    public boolean setProductAttribute(Product productParameter){
        int productQuantity = productParameter.productQuantity;
        if(productQuantity > 0){
            this.productName = productParameter.productName;
            this.productQuantity = productParameter.productQuantity;
            return true;
        }else{
            return false;
        }
    }
}