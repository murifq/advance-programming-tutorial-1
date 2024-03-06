package id.ac.ui.cs.advprog.eshop.model;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest{
    @Test
    void testCreatePaymentByVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("CASH_ON_DELIVERY", paymentByVoucherCode.getMethod());
    }

    @Test
    void testCreatePaymentByCashOnDelivery() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", "1");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", "SUCCESS", paymentData);

        assertEquals("CASH_ON_DELIVERY", paymentByCashOnDelivery.getMethod());
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b",
                    "WRONG_METHOD", paymentData);
        });
    }

    @Test
    void testSetToSuccessStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);
        paymentByVoucherCode.setStatus("SUCCESS");

        assertEquals("SUCCESS", paymentByVoucherCode.getStatus());
    }

    @Test
    void testSetToRejectedStatus(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);
        paymentByVoucherCode.setStatus("REJECTED");

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeMoreThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC56781");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeLessThan16CharLong(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC567");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotStartedWithESHOP(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP1234ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeIsNotContain8NumChar(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP123ABC5678");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidVoucherCodeBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", "1");

        Payment paymentByVoucherCode = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByVoucherCode.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseInvalidPaymentDataFormat(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByCashOnDelivery.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseAddressIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", null);
        paymentData.put("deliveryFee", "1");

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByCashOnDelivery.getStatus());
    }

    @Test
    void testInvalidCashOnDeliveryBecauseDeliveryFeeIsNull(){
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "Jl. Orchard");
        paymentData.put("deliveryFee", null);

        Payment paymentByCashOnDelivery = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", "SUCCESS", paymentData);

        assertEquals("REJECTED", paymentByCashOnDelivery.getStatus());
    }
}