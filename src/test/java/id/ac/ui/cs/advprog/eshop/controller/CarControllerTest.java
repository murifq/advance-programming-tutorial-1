package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
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
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;
    @MockBean
    private CarServiceImpl carService;
    @Test
    public void testCreateCarPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/car/createCar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("createCar"));
    }


    @Test
    public void testCreateCarPost() throws Exception {
        Car car = new Car();
        Car expectedResult = new Car(); // Replace with the expected return value

        Mockito.when(carService.create(Mockito.any())).thenReturn(expectedResult);

        mockMvc.perform(MockMvcRequestBuilders.post("/car/createCar")
                        .flashAttr("car", car))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));
    }



    @Test
    public void testCarListPage() throws Exception {
        List<Car> cars = Arrays.asList(new Car(), new Car());
        Mockito.when(carService.findAll()).thenReturn(cars);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/listCar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("carList"))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", cars));
    }

    @Test
    public void testDeleteCar() throws Exception {
        String carId = "0";

        mockMvc.perform(MockMvcRequestBuilders.post("/car/deleteCar")
                        .param("carId", carId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));
    }

    @Test
    public void testEditCarPage() throws Exception {
        String carId = "0";
        Car car = new Car();
        Mockito.when(carService.findById(carId)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/editCar/{carId}", carId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("editCar"))
                .andExpect(MockMvcResultMatchers.model().attribute("car", car));
    }

    @Test
    public void testEditCarPost() throws Exception {
        // Create a Car object with necessary attributes for testing
        Car car = new Car();
        car.setCarId("0"); // Replace with the appropriate values for testing

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/car/editCar")
                        .flashAttr("car", car)) // Use flashAttr to simulate @ModelAttribute
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));
    }
}
