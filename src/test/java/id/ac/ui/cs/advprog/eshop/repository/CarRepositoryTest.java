package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setName("Wuling Aventador");
        car.setQuantity(100);
        car.setColor("Oren Toren Air");
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getId(), savedCar.getId());
        assertEquals(car.getName(), savedCar.getName());
        assertEquals(car.getQuantity(), savedCar.getQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testEditCar() {
        Car car = new Car();
        car.setName("Wuling Aventador");
        car.setQuantity(100);
        car.setColor("Oren Toren Air");
        carRepository.create(car);

        Car carParameter = new Car();
        carParameter.setId(car.getId());
        carParameter.setName("Sampo Cap Udin");
        carParameter.setQuantity(200);

        carRepository.update(carParameter.getId(), carParameter);

        assertEquals(carParameter.getId(), car.getId());
        assertEquals(carParameter.getName(), car.getName());
        assertEquals(carParameter.getQuantity(), car.getQuantity());
    }

    @Test
    void testEditCarNotFound() {
        Car car = new Car();
        car.setName("Wuling Aventador");
        car.setQuantity(100);
        car.setColor("Oren Toren Air");
        carRepository.create(car);

        Car carParameter = new Car();
        carParameter.setId("id yang salah");
        carParameter.setName("Sampo Cap Udin");
        carParameter.setQuantity(200);

        Car carResult = carRepository.update(carParameter.getId(), carParameter);
        assertNull(carResult);

    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setName("Wuling Aventador");
        car.setQuantity(100);
        car.setColor("Oren Toren Air");
        carRepository.create(car);

        String carIdToDelete = car.getId();
        carRepository.delete(carIdToDelete);

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }


    @Test
    void testDeleteCarNeverAdded() {
        Car car = new Car();
        car.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setName("Sampo Cap Bambang");
        car.setQuantity(100);

        String carIdToDelete = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Car foundCar = carRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(foundCar);
    }

    @Test
    void testGetCarIfThereAreMoreThanOneCar() {
        Car car1 = new Car();
        car1.setName("Wuling Aventador");
        car1.setQuantity(100);
        car1.setColor("Oren Toren Air");
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setName("Lamborghini Air EV");
        car2.setQuantity(100);
        car2.setColor("Kuning Blue Band");
        carRepository.create(car2);

        String carIdToGet = car1.getId();
        assertEquals(car1.getId(), carRepository.findById(carIdToGet).getId());

    }
//
    @Test
    void testGetCarNeverAdded() {
        Car car = new Car();
        car.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setName("Sampo Cap Bambang");
        car.setQuantity(100);

        String carIdToGet = "asdasde-1c39-460e-8860-71af6af63bd6";
        assertEquals(null, carRepository.findById(carIdToGet));

    }
}