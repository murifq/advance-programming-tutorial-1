package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController{

    @Autowired
    private ProductService service;
    String redirectToProductListPage = "redirect:/product/list";
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);        
        return redirectToProductListPage;
    }
    
    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }  

    @DeleteMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable("productId") String productId, Model model) {
        service.deleteProduct(productId);
        return redirectToProductListPage;
    }

    @GetMapping("/edit/{productId}")
    public String editProductPage(@PathVariable("productId") String productId, Model model) {
        Product product = service.getProduct(productId);
        model.addAttribute("product", product);
        return "EditProduct";
    }

    @PutMapping("/edit/{productId}")
    public String editProductPut(@PathVariable("productId") String productId, Product product, Model model) {
        service.setProductAttribute(product);
        return redirectToProductListPage;
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController{
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model){
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carlistPage(Model model){
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model){
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model){
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);

        return "redirect:listCar";
    }

    @PostMapping
    public String deleteCar(@RequestParam("carId") String carId){
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}