package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    private Order order;

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setItemId("dummyId");
        product.setItemName("dummyProduct");
        product.setItemQuantity(1);
        order = new Order("order1", Collections.singletonList(product), 1708560000L, "Test Author");
    }

    @Test
    public void testVoucherPaymentValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678"); 
        Payment payment = new Payment(order, "VOUCHER", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testVoucherPaymentInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALID123"); 
        Payment payment = new Payment(order, "VOUCHER", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testBankTransferPaymentValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment(order, "TRANSFER_BANK", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testBankTransferPaymentInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", ""); 
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment(order, "TRANSFER_BANK", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }
    
    @Test
    public void testConstructorWithExplicitStatusValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order, "VOUCHER", paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
    
    @Test
    public void testConstructorWithExplicitStatusInvalid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(order, "VOUCHER", paymentData, "INVALID_STATUS");
        });
    }
}