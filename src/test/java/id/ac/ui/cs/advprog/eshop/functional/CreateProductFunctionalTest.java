package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automitically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    /**
     * The base URL for testing. Default to (@code http://localhost).
     */
    private int serverPort;


    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest(){
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void productListButton_isCorrect(ChromeDriver driver) throws Exception{
        //  Exercise
        driver.get(baseUrl);
        String productListButtonText = driver.findElement(By.tagName("a")).getText();

        // Verify
        assertEquals("Lihat Produk", productListButtonText);
    }

    @Test
    void productListButton_isWorking(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        driver.findElement(By.className("btn")).click();

        String pageTitle = driver.getTitle();
        String pageTitleMessage = driver.findElement(By.tagName("h2")).getText();
        String createProductButton = driver.findElement(By.name("createProductButton")).getText();

        // Verify
        assertEquals("Product List", pageTitle);
        assertEquals("Product List", pageTitleMessage);
        assertEquals("Create Product", createProductButton);

    }

    @Test
    void createProduct_isCorrect(ChromeDriver driver) throws Exception{
        driver.get(baseUrl);
        // Product List Button clicked
        driver.findElement(By.className("btn")).click();

        // Create Product Button clicked
        driver.findElement(By.className("btn")).click();

        String productName = "Ayam Kecap Angsa";
        int productQuantity = 1;

        driver.findElement(By.id("nameInput")).sendKeys(productName);
        driver.findElement(By.id("quantityInput")).clear();
        driver.findElement(By.id("quantityInput")).sendKeys(String.valueOf(productQuantity));
        driver.findElement(By.className("btn")).click();

        String pageTitle = driver.getTitle();
        String pageTitleMessage = driver.findElement(By.tagName("h2")).getText();
        String createProductButton = driver.findElement(By.name("createProductButton")).getText();

        // Verify
        assertEquals("Product List", pageTitle);
        assertEquals("Product List", pageTitleMessage);
        assertEquals("Create Product", createProductButton);

        String productNameNew = "Ayam Kecap Angsa";
        String productQuantityNew = "1";

        String productNameHTML = driver.findElement(By.name("productName")).getText();
        String productQuantityHTML = driver.findElement(By.name("productQuantity")).getText();

        assertEquals(productNameNew, productNameHTML);
        assertEquals(productQuantityNew, productQuantityHTML);
    }
}