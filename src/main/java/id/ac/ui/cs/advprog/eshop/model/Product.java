package  id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    static int staticId;
    private String productId;
    private String productName;
    private int productQuantity;

    public String getProductId(){
        return this.productId;
    }

    static public String getStaticId(){
        int staticId = Product.staticId;
        Product.staticId++;
        return Integer.toString(staticId);
    }

    public String getProductName(){
        return this.productName;
    }

    public int getProductQuantity(){
        return this.productQuantity;
    }


    public  void setProductId(String productId){
        this.productId = productId;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public void setProductQuantity(int productQuantity){
        this.productQuantity = productQuantity;
    }

    public boolean setProductAttribute(Product productParameter){
        int productQuantity = productParameter.productQuantity;
        if(productQuantity >= 0){
            this.productName = productParameter.productName;
            this.productQuantity = productParameter.productQuantity;
            return true;
        }else{
            return false;
        }
    }
}