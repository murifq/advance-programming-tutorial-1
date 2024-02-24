package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private CarServiceImpl carService;
    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("CreateProduct"));
    }


    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        Product expectedResult = new Product(); // Replace with the expected return value

        Mockito.when(productService.create(Mockito.any())).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                        .flashAttr("product", product))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }



    @Test
    public void testProductListPage() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        Mockito.when(productService.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ProductList"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", products));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        String productId = "0";

        mockMvc.perform(MockMvcRequestBuilders.delete("/product/delete/{productId}", productId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));
    }

    @Test
    public void testEditProductPage() throws Exception {
        String productId = "0";
        Product product = new Product();
        Mockito.when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/{productId}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("EditProduct"))
                .andExpect(MockMvcResultMatchers.model().attribute("product", product));
    }

    @Test
    public void testEditProductPost() throws Exception {
        String productId = "0";
        Product product = new Product();
        Mockito.when(productService.findById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit/{productId}", productId)
                .flashAttr("product", product))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/product/list"));

    }
}
