package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;
    
    @Mock
    private PaymentRepository paymentRepository;
    
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
    public void testAddPayment() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        Payment payment = new Payment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);
        Payment result = paymentService.addPayment(order, PaymentMethod.VOUCHER.getValue(), paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }
    
    @Test
    public void testSetStatusSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment(order, PaymentMethod.TRANSFER_BANK.getValue(), paymentData);
        when(paymentRepository.save(payment)).thenReturn(payment);
        Payment updated = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), updated.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }
    
    @Test
    public void testSetStatusRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank ABC");
        paymentData.put("referenceCode", "REF123456");
        Payment payment = new Payment(order, PaymentMethod.TRANSFER_BANK.getValue(), paymentData);
        when(paymentRepository.save(payment)).thenReturn(payment);
        Payment updated = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), updated.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }
}