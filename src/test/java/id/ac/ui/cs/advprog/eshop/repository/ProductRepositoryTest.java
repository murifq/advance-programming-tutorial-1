package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product productParameter = new Product();
        productParameter.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productParameter.setProductName("Sampo Cap Udin");
        productParameter.setProductQuantity(200);

        String targetToEditProductId = productParameter.getProductId();
        Product targetToEditProduct = productRepository.getProduct(targetToEditProductId);
        targetToEditProduct.setProductAttribute(productParameter);

        assertEquals(targetToEditProduct.getProductId(), productParameter.getProductId());
        assertEquals(targetToEditProduct.getProductName(), productParameter.getProductName());
        assertEquals(targetToEditProduct.getProductQuantity(), productParameter.getProductQuantity());


        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(targetToEditProduct.getProductId(), savedProduct.getProductId());
        assertEquals(targetToEditProduct.getProductName(), savedProduct.getProductName());
        assertEquals(targetToEditProduct.getProductQuantity(), savedProduct.getProductQuantity());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProductNegativeInputProductQuantity() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product productParameter = new Product();
        productParameter.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productParameter.setProductName("Sampo Cap Udin");
        productParameter.setProductQuantity(-200);

        String targetToEditProductId = productParameter.getProductId();
        Product targetToEditProduct = productRepository.getProduct(targetToEditProductId);

        assertFalse(targetToEditProduct.setProductAttribute(productParameter));

        assertEquals(targetToEditProduct.getProductId(), product.getProductId());
        assertEquals(targetToEditProduct.getProductName(), product.getProductName());
        assertEquals(targetToEditProduct.getProductQuantity(), product.getProductQuantity());


        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        String productIdToDelete = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        assertEquals(productRepository.deleteProduct(productIdToDelete), product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductIfThereAreMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        String productIdToDelete = "a0f9de46-90b1-437d-a0bf-d0821dde9096";
        assertEquals(productIdToDelete, productRepository.deleteProduct(productIdToDelete).getProductId());

    }

    @Test
    void testDeleteProductNeverAdded() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        String productIdToDelete = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        assertEquals(null, productRepository.deleteProduct(productIdToDelete));

    }

    @Test
    void testGetProductIfThereAreMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        String productIdToGet = "a0f9de46-90b1-437d-a0bf-d0821dde9096";
        assertEquals(productIdToGet, productRepository.getProduct(productIdToGet).getProductId());

    }

    @Test
    void testGetProductNeverAdded() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        String productIdToGet = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        assertEquals(null, productRepository.getProduct(productIdToGet));

    }
}