package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;
import java.util.UUID;

@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private Map<String, String> paymentData;
    private String status;

    public Payment(Order order, String method, Map<String, String> paymentData) {
    }

    public Payment(Order order, String method, Map<String, String> paymentData, String status) {
    }

    public void setStatus(String status) {
    }
}