package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.*;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.PayloadApplicationEvent;

@SpringBootTest
class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;
    List<Product> products;
    List<Order> orders;


    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        products.add(product1);

        orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, order1);
        payments.add(paymentByVoucherCode);

        paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", "1");

        Payment paymentByCashOnDelivery = new Payment("23652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentData, order2);
        payments.add(paymentByCashOnDelivery);
    }

    @Test
    void testAddPaymentIfOrderNotNull() {
        Payment paymentByVoucherCode= payments.getFirst();
        Order order = orders.getFirst();
        doReturn(paymentByVoucherCode).when(paymentRepository).save(paymentByVoucherCode);

        Payment result = paymentService.addPayment(order, paymentByVoucherCode.getMethod(), paymentByVoucherCode.getPaymentData());
//        verify(paymentRepository, times(1)).save(result);
        assertEquals(paymentByVoucherCode.getId(), result.getId());
    }

    @Test
    void testAddPaymentIfOrderNull() {
        Payment paymentByVoucherCode= payments.getFirst();
        Order order = null;
        Mockito.when(paymentRepository.save(paymentByVoucherCode)).thenReturn(null);
//        doReturn(paymentByVoucherCode).when(paymentRepository).save(paymentByVoucherCode);

        assertThrows(IllegalArgumentException.class, () -> {
            Payment result = paymentService.addPayment(order, paymentByVoucherCode.getMethod(), paymentByVoucherCode.getPaymentData());
        });
    }

    @Test
    void testSetStatusPaymentToSuccess() {
        Payment paymentByVoucherCode= payments.getFirst();
        Order order = paymentByVoucherCode.getOrder();

        Mockito.when(paymentRepository.findById(paymentByVoucherCode.getId())).thenReturn(paymentByVoucherCode);

        Payment result = paymentService.setStatus(paymentByVoucherCode, "SUCCESS");
        assertEquals("SUCCESS", result.getStatus());
        assertEquals("SUCCESS", result.getOrder().getStatus());
    }

    @Test
    void testSetStatusPaymentToRejected() {
        Payment paymentByVoucherCode= payments.getFirst();
        Order order = paymentByVoucherCode.getOrder();

        doReturn(paymentByVoucherCode).when(paymentRepository).findById(paymentByVoucherCode.getId());

        Payment result = paymentService.setStatus(paymentByVoucherCode, "REJECTED");
        verify(paymentRepository, times(1)).save(result);
        assertEquals("REJECTED", result.getStatus());
        assertEquals("FAILED", result.getOrder().getStatus());
    }

    @Test
    void getPaymentFound(){
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment, result);
    }

    @Test
    void getPaymentNotFound(){
        Payment payment = payments.getFirst();
        doReturn(null).when(paymentRepository).findById(payment.getId());
        Payment result = paymentService.getPayment(payment.getId());
        assertNull(result);
    }

    @Test
    void getAllPaymentIsNotEmpty(){
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        assertEquals(payments, result);
    }

    @Test
    void getAllPaymentIsEmpty(){
        List<Payment> emptyPayments = new ArrayList<>();
        doReturn(emptyPayments).when(paymentRepository).findAll();
        List<Payment> result = paymentService.getAllPayments();
        assertTrue(result.isEmpty());
    }
}