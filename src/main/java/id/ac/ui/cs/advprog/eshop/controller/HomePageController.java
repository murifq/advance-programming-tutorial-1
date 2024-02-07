package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomePageController{

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "homePage";
    }
}