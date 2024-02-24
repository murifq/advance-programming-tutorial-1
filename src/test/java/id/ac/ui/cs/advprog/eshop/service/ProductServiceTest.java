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
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testCreateProductIfProductIdIsNotSet() {
        Product productToCreate = new Product();
        productToCreate.setName("Test Product");
        productToCreate.setQuantity(1);

        Mockito.when(productRepository.create(any())).thenReturn(productToCreate);

        Product createdProduct = productService.create(productToCreate);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
    }

    @Test
    public void testCreateProductIfProductIdIsSet() {
        Product productToCreate = new Product();
        productToCreate.setName("Test Product");
        productToCreate.setQuantity(1);
        productToCreate.setId("0");

        Mockito.when(productRepository.create(any())).thenReturn(productToCreate);

        Product createdProduct = productService.create(productToCreate);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
    }

    @Test
    public void testGetProductIfProductExist() {
        Product productToGet = new Product();
        productToGet.setName("Test Product");
        productToGet.setQuantity(1);
        productToGet.setId("0");

        String ProductIdToGet = "0";

        Mockito.when(productRepository.findById("0")).thenReturn(productToGet);

        Product foundProduct = productService.findById(ProductIdToGet);

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        assertEquals(1, foundProduct.getQuantity());
        assertEquals("0", foundProduct.getId());
    }

    @Test
    public void testGetProductIfProductNotExist() {
        Product productToGet = new Product();
        productToGet.setName("Test Product");
        productToGet.setQuantity(1);
        productToGet.setId("0");

        String ProductIdToGet = "0";

        Mockito.when(productRepository.findById("0")).thenReturn(null);

        Product foundProduct = productService.findById(ProductIdToGet);

        assertNull(foundProduct);

    }

    @Test
    public void testFindAllProducts() {
        List<Product> mockProductList = new ArrayList<>();

        Product product1 = new Product();
        product1.setName("Product 1");
        product1.setQuantity(1);

        Product product2 = new Product();
        product2.setName("Product 2");
        product2.setQuantity(2);

        productService.create(product1);
        productService.create(product2);

        mockProductList.add(product1);
        mockProductList.add(product2);

        Iterator<Product> productIterator = mockProductList.iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertEquals(2, allProducts.size());
        assertEquals("Product 1", allProducts.get(0).getName());
        assertEquals("Product 2", allProducts.get(1).getName());
    }

    @Test
    public void testDeleteProduct() {
        String productIdToDelete = "123";

        Product deletedProduct = new Product();
        deletedProduct.setName("Product 1");
        deletedProduct.setQuantity(1);
        productService.deleteProductById(productIdToDelete);

        verify(productRepository, times(1)).deleteProduct(productIdToDelete);
    }

    @Test
    public void testSetProductAttributeSuccessful() {
        String productIdToEdit = "0";

        Product productToEdit = new Product();
        productToEdit.setName("Product 1");
        productToEdit.setQuantity(1);
        productToEdit.setId("0");

        Product productToEditReference = new Product();
        productToEditReference.setName("Product 2");
        productToEditReference.setQuantity(2);
        productToEditReference.setId("0");

        Product productToEditResult = productToEditReference;

        Mockito.when(productRepository.findById(productIdToEdit)).thenReturn(productToEditResult);

        productService.update(productIdToEdit, productToEditReference);

        assertEquals(productToEditReference.getId(), productToEditResult.getId());
        assertEquals(productToEditReference.getName(), productToEditResult.getName());
        assertEquals(productToEditReference.getQuantity(), productToEditResult.getQuantity());

    }
}
