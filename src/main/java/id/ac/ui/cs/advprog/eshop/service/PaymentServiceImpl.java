package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    
    @Override
    public Payment addPayment(Order order, String method, java.util.Map<String, String> paymentData) {
        return null;
    }
    
    @Override
    public Payment setStatus(Payment payment, String status) {
        return null;
    }
    
    @Override
    public Payment getPayment(String paymentId) {
        return null;
    }
    
    @Override
    public List<Payment> getAllPayments() {
        return null;
    }
}