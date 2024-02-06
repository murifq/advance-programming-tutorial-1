package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService{
    public Product create(Product product);
    public List<Product> findAll();
    public Boolean deleteProduct(String productId);
    public Product getProduct(String productId);
    public void setProductAttribute(Product product);
}