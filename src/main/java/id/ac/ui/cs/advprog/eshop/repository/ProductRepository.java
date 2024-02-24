package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        if (product.getId() == null) {
            UUID uuid = UUID.randomUUID();
            product.setId(uuid.toString());
        }

        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public void deleteProduct(String productId){
        productData.removeIf(product -> product.getId().equals(productId));
    }
    
    public Product findById(String productId){
        for(int i = 0; i<productData.size(); i++){
            Product product = productData.get(i);
            if(product.getId().equals(productId)){
                return product;
            }
        }
        return null;
    }

    public Product update(String productId, Product productParameter){
        for(int i = 0; i<productData.size(); i++){
            Product product = productData.get(i);
            if(product.getId().equals(productId)){
                // Update the existing car with the new information
                product.setName(productParameter.getName());
                product.setQuantity(productParameter.getQuantity());
                return product;
            }
        }
        return null; // Handle the case where the car is not found
    }

}