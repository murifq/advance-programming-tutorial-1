package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
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
class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void testCreateCarIfIdIsNotSet() {
        Car carToCreate = new Car();
        carToCreate.setName("Test Car");
        carToCreate.setQuantity(1);

        Mockito.when(carRepository.create(any())).thenReturn(carToCreate);

        Car createdCar = carService.create(carToCreate);

        assertNotNull(createdCar);
        assertEquals("Test Car", createdCar.getName());
    }

    @Test
        public void testCreateCarIfIdIsSet() {
        Car carToCreate = new Car();
        carToCreate.setName("Test Car");
        carToCreate.setQuantity(1);
        carToCreate.setId("0");

        Mockito.when(carRepository.create(any())).thenReturn(carToCreate);

        Car createdCar = carService.create(carToCreate);

        assertNotNull(createdCar);
        assertEquals("Test Car", createdCar.getName());
    }

    @Test
    public void testGetCarIfCarExist() {
        Car carToCreate = new Car();
        carToCreate.setName("Test Car");
        carToCreate.setQuantity(1);
        carToCreate.setId("0");

        String carIdToGet = "0";

        Mockito.when(carService.findById("0")).thenReturn(carToCreate);

        Car foundCar = carService.findById(carIdToGet);

        assertNotNull(foundCar);
        assertEquals("Test Car", foundCar.getName());
        assertEquals(1, foundCar.getQuantity());
        assertEquals("0", foundCar.getId());
    }

    @Test
    public void testGetCarIfCarNotExist() {
        Car carToCreate = new Car();
        carToCreate.setName("Test Car");
        carToCreate.setQuantity(1);
        carToCreate.setId("0");

        String carIdToGet = "0";

        Mockito.when(carRepository.findById("0")).thenReturn(null);

        Product foundProduct = carService.findById(carIdToGet);

        assertNull(foundProduct);

    }

    @Test
    public void testFindAllCars() {
        List<Car> mockCarList = new ArrayList<>();

        Car car1 = new Car();
        car1.setName("Car 1");
        car1.setQuantity(1);

        Car car2 = new Car();
        car2.setName("Car 2");
        car2.setQuantity(1);

        carService.create(car1);
        carService.create(car2);

        mockCarList.add(car1);
        mockCarList.add(car2);

        Iterator<Car> carIterator = mockCarList.iterator();
        Mockito.when(carRepository.findAll()).thenReturn(carIterator);

        List<Car> allCars = carService.findAll();

        assertEquals(2, allCars.size());
        assertEquals("Car 1", allCars.get(0).getName());
        assertEquals("Car 2", allCars.get(1).getName());
    }

    @Test
    public void testDeleteCar() {
        String carIdToDelete = "123";

        Car carToDelete = new Car();
        carToDelete.setName("Test Car");
        carToDelete.setQuantity(1);
        carToDelete.setId("0");
        carService.deleteCarById(carIdToDelete);

        verify(carRepository, times(1)).delete(carIdToDelete);
    }

    @Test
    public void testSetCarAttributeSuccessful() {
        String productIdToEdit = "0";

        Car carToEdit = new Car();
        carToEdit.setName("Test Car");
        carToEdit.setQuantity(1);
        carToEdit.setId("0");

        Car carToEditReference = new Car();
        carToEditReference.setName("Car 2");
        carToEditReference.setQuantity(2);
        carToEditReference.setId("0");

        Car carToEditResult = carToEditReference;

        Mockito.when(carRepository.findById(productIdToEdit)).thenReturn(carToEditResult);

        carService.update(productIdToEdit, carToEditReference);

        assertEquals(carToEditReference.getId(), carToEditResult.getId());
        assertEquals(carToEditReference.getName(), carToEditResult.getName());
        assertEquals(carToEditReference.getQuantity(), carToEditResult.getQuantity());

    }
}
