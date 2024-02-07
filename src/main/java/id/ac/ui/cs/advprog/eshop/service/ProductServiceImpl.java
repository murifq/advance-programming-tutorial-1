package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product){
        if(product.getProductId() == null){
            String productId = Product.getStaticId();
            product.setProductId(productId);
            productRepository.create(product);
        }else{
            productRepository.create(product);
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Boolean deleteProduct(String productId){
        Product deletedProduct = productRepository.deleteProduct(productId);
        if(deletedProduct != null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Product getProduct(String productId){
        Product getProduct = productRepository.getProduct(productId);
        if(getProduct != null){
            return getProduct;
        }else{
            return null;
        }
    }

    @Override
    public boolean setProductAttribute(Product productParameter){
        Product getProduct = productRepository.getProduct(productParameter.getProductId());
        boolean successfulEdit = getProduct.setProductAttribute(productParameter);
        if(successfulEdit == true){
            return true;
        }else{
            return false;
        }
    }
}