package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
        Payment payment = new Payment(order, method, paymentData);
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        if (!status.equals(PaymentStatus.SUCCESS.getValue()) &&
            !status.equals(PaymentStatus.REJECTED.getValue())) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        payment.setStatus(status);
        // Update related Order status: if payment is SUCCESS, Order becomes SUCCESS;
        // if REJECTED, Order becomes FAILED.
        if (payment.getOrder() != null) {
            if (status.equals(PaymentStatus.SUCCESS.getValue())) {
                payment.getOrder().setStatus("SUCCESS");
            } else if (status.equals(PaymentStatus.REJECTED.getValue())) {
                payment.getOrder().setStatus("FAILED");
            }
        }
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}