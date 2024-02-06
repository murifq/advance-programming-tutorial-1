package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        product.setProductId();
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product deleteProduct(String productId){
        for(int i = 0; i<productData.size(); i++){
            Product product = productData.get(i);
            if(product.getProductId().equals(productId)){
                productData.remove(i);
                return product;
            }
        }
        return null;
    }
    
    public Product getProduct(String productId){
        for(int i = 0; i<productData.size(); i++){
            Product product = productData.get(i);
            if(product.getProductId().equals(productId)){
                return product;
            }
        }
        return null;
    }

}