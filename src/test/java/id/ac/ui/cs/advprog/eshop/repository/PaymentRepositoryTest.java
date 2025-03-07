package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Order order;

    @BeforeEach
    public void setUp() {
        paymentRepository = new PaymentRepository();
        Product product = new Product();
        product.setItemId("dummyId");
        product.setItemName("dummyProduct");
        product.setItemQuantity(1);
        order = new Order("order1", Collections.singletonList(product), 1708560000L, "Test Author");
    }

    @Test
    public void testSaveAndGetPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        paymentRepository.save(payment);
        Payment found = paymentRepository.getPayment(payment.getId());
        assertNotNull(found);
        assertEquals(payment.getId(), found.getId());
        assertEquals(payment.getStatus(), found.getStatus());
    }

    @Test
    public void testGetAllPayments() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment1 = new Payment(order, PaymentMethod.VOUCHER.getValue(), paymentData1);
        paymentRepository.save(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "Bank ABC");
        paymentData2.put("referenceCode", "REF123456");
        Payment payment2 = new Payment(order, PaymentMethod.TRANSFER_BANK.getValue(), paymentData2);
        paymentRepository.save(payment2);

        assertEquals(2, paymentRepository.getAllPayments().size());
    }
}