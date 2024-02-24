package  id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest{
    Product product;
    @BeforeEach
    void setUp(){
        this.product = new Product();
        this.product.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.product.setName("Sampo Cap Bambang");
        this.product.setQuantity(100);
    }
    @Test
    void testgetId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.product.getId());
    }

    @Test
    void testgetName(){
        assertEquals("Sampo Cap Bambang", this.product.getName());
    }

    @Test
    void testgetQuantity(){
        assertEquals(100, this.product.getQuantity());
    }

    @Test
    void testSetProductAttribute(){
        product.setId("1");
        product.setName("a");
        product.setQuantity(2);

        assertEquals("1", product.getId());
        assertEquals("a", product.getName());
        assertEquals(2, product.getQuantity());
    }
}