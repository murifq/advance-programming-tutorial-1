package id.ac.ui.cs.advprog.eshop.model;
import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest{
    private List<Product> products;
    private Order order;
    @BeforeEach
    void setUpOrder(){
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setName("Sampo Cap Bambang");
        product1.setQuantity(2);
        Product product2 = new Product();
        product2.setId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setName("Sabun Cap Usep");
        product2.setQuantity(1);
        this.products.add(product1);
        this.products.add(product2);

        this.order = new Order("13652556-012a0-4c07-b546-54eb1396d79b",
                this.products, 1708560000L, "Safira Sudrajat");
    }

    @Test
    void testCreatePaymentByVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals("VOUCHER_CODE", paymentByVoucherCode.getMethod());
    }

    @Test
    void testCreatePaymentByCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", "1");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentData, this.order);
        System.out.println("Line 28 "+paymentByCashOnDelivery.paymentData.size());
        System.out.println("Line 28 "+paymentByCashOnDelivery.method);

        assertEquals("CASH_ON_DELIVERY", paymentByCashOnDelivery.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "WRONG_METHOD", paymentData, this.order);
        });
    }

    @Test
    void testSetToSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);
        paymentByVoucherCode.setStatus("SUCCESS");

        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testSetToRejectedStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);
        paymentByVoucherCode.setStatus("REJECTED");

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeMoreThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56781");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeLessThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotStartedWithESHOP(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotContain8NumChar(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", "1");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByCashOnDelivery.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseAddressIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "1");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByCashOnDelivery.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseDeliveryFeeIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", null);

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", paymentData, this.order);

        assertEquals(PaymentStatus.REJECTED.getValue(), paymentByCashOnDelivery.getStatus());
    }
}