package  id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest{
    Product product;
    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setProductName("Sampo Cap Bambang");
        this.product.setProductQuantity(100);
    }
    @Test
    void testGetProductId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
    }

    @Test
    void testGetProductName(){
        assertEquals("Sampo Cap Bambang", this.product.getProductName());
    }

    @Test
    void testGetProductQuantity(){
        assertEquals(100, this.product.getProductQuantity());
    }

    @Test
    void testEditProduct(){
        Product productParameter = new Product();
        productParameter.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productParameter.setProductName("Sampo Cap Udin");
        productParameter.setProductQuantity(200);
        product.setProductAttribute(productParameter);

        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getProductId());
        assertEquals("Sampo Cap Udin", this.product.getProductName());
        assertEquals(200, this.product.getProductQuantity());
    }

    @Test
    void testAutomaticGeneratedIdFromStaticId(){
        Product.staticId = 0;
        String firstProductId = "0";
        String firstProductIdTest = Product.getStaticId();
        assertEquals(firstProductId, firstProductIdTest);
    }
}