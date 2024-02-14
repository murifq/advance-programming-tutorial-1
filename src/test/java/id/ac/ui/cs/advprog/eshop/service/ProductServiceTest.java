package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testCreateProductIfProductIdIsNotSet() {
        Product productToCreate = new Product();
        productToCreate.setProductName("Test Product");
        productToCreate.setProductQuantity(1);

        Mockito.when(productRepository.create(any())).thenReturn(productToCreate);

        Product createdProduct = productService.create(productToCreate);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
    }

    @Test
    public void testCreateProductIfProductIdIsSet() {
        Product productToCreate = new Product();
        productToCreate.setProductName("Test Product");
        productToCreate.setProductQuantity(1);
        productToCreate.setProductId("0");

        Mockito.when(productRepository.create(any())).thenReturn(productToCreate);

        Product createdProduct = productService.create(productToCreate);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
    }

    @Test
    public void testGetProductIfProductExist() {
        Product productToGet = new Product();
        productToGet.setProductName("Test Product");
        productToGet.setProductQuantity(1);
        productToGet.setProductId("0");

        String ProductIdToGet = "0";

        Mockito.when(productRepository.getProduct("0")).thenReturn(productToGet);

        Product foundProduct = productService.getProduct(ProductIdToGet);

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
        assertEquals(1, foundProduct.getProductQuantity());
        assertEquals("0", foundProduct.getProductId());
    }

    @Test
    public void testGetProductIfProductNotExist() {
        Product productToGet = new Product();
        productToGet.setProductName("Test Product");
        productToGet.setProductQuantity(1);
        productToGet.setProductId("0");

        String ProductIdToGet = "0";

        Mockito.when(productRepository.getProduct("0")).thenReturn(null);

        Product foundProduct = productService.getProduct(ProductIdToGet);

        assertNull(foundProduct);

    }

    @Test
    public void testFindAllProducts() {
        List<Product> mockProductList = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductName("Product 1");
        product1.setProductQuantity(1);

        Product product2 = new Product();
        product2.setProductName("Product 2");
        product2.setProductQuantity(2);

        productService.create(product1);
        productService.create(product2);

        mockProductList.add(product1);
        mockProductList.add(product2);

        Iterator<Product> productIterator = mockProductList.iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertEquals(2, allProducts.size());
        assertEquals("Product 1", allProducts.get(0).getProductName());
        assertEquals("Product 2", allProducts.get(1).getProductName());
    }

    @Test
    public void testDeleteProduct() {
        String productIdToDelete = "123";

        Product deletedProduct = new Product();
        deletedProduct.setProductName("Product 1");
        deletedProduct.setProductQuantity(1);
        Mockito.when(productRepository.deleteProduct(productIdToDelete)).thenReturn(deletedProduct);

        boolean result = productService.deleteProduct(productIdToDelete);

        assertTrue(result);
    }

    @Test
    public void testDeleteProductNotFound() {
        String productIdToDelete = "123";

        Product deletedProduct = new Product();
        deletedProduct.setProductName("Product 1");
        deletedProduct.setProductQuantity(1);
        Mockito.when(productRepository.deleteProduct(productIdToDelete)).thenReturn(null);

        boolean result = productService.deleteProduct(productIdToDelete);

        assertFalse(result);
    }

    @Test
    public void testSetProductAttributeSuccessful() {
        String productIdToEdit = "0";

        Product productToEdit = new Product();
        productToEdit.setProductName("Product 1");
        productToEdit.setProductQuantity(1);
        productToEdit.setProductId("0");

        Product productToEditReference = new Product();
        productToEditReference.setProductName("Product 2");
        productToEditReference.setProductQuantity(2);
        productToEditReference.setProductId("0");

        Mockito.when(productRepository.getProduct(productIdToEdit)).thenReturn(productToEdit);

        boolean result = productService.setProductAttribute(productToEditReference);

        assertTrue(result);
    }

    @Test
    public void testSetProductAttributeFail() {
        String productIdToEdit = "111";

        Product productToEdit = new Product();
        productToEdit.setProductName("Product 1");
        productToEdit.setProductQuantity(1);
        productToEdit.setProductId("0");

        Product productToEditReference = new Product();
        productToEditReference.setProductName("Product 2");
        productToEditReference.setProductQuantity(2);
        productToEditReference.setProductId("0");

        Mockito.when(productRepository.getProduct(productIdToEdit)).thenReturn(null);

        boolean result = productService.setProductAttribute(productToEditReference);

        assertFalse(result);
    }

//    @Test
//    public void testGetProduct() {
//        String productIdToGet = "456";
//        Product mockedProduct = new Product("Mocked Product");
//
//        Mockito.when(productRepository.getProduct(productIdToGet)).thenReturn(mockedProduct);
//
//        Product retrievedProduct = productService.getProduct(productIdToGet);
//
//        assertNotNull(retrievedProduct);
//        assertEquals("Mocked Product", retrievedProduct.getProductName());
//    }

//    @Test
//    public void testSetProductAttribute() {
//        String productId = "789";
//        Product existingProduct = new Product("Existing Product");
//        existingProduct.setProductId(productId);
//
//        Product updatedProduct = new Product("Updated Product");
//        updatedProduct.setProductId(productId);
//
//        Mockito.when(productRepository.getProduct(productId)).thenReturn(existingProduct);
//        Mockito.when(productRepository.create(any())).thenReturn(updatedProduct);
//
//        boolean result = productService.setProductAttribute(updatedProduct);
//
//        assertTrue(result);
//        assertEquals("Updated Product", existingProduct.getProductName());
//    }
}
