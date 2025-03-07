package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();
    
    public Payment save(Payment payment) {
        for (int i = 0; i < paymentData.size(); i++) {
            if (paymentData.get(i).getId().equals(payment.getId())) {
                paymentData.set(i, payment);
                return payment;
            }
        }
        paymentData.add(payment);
        return payment;
    }
    
    public Payment getPayment(String paymentId) {
        for (Payment p : paymentData) {
            if (p.getId().equals(paymentId)) {
                return p;
            }
        }
        return null;
    }
    
    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentData);
    }
}