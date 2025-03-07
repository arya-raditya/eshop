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
    
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_REJECTED = "REJECTED";
    public static final String METHOD_VOUCHER = "VOUCHER";
    public static final String METHOD_TRANSFER_BANK = "TRANSFER_BANK";

    public Payment(Order order, String method, Map<String, String> paymentData) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        this.order = order;
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        if (paymentData == null) {
            this.status = STATUS_REJECTED;
        }else{
            this.status = STATUS_SUCCESS;
        }
    }

    public Payment(Order order, String method, Map<String, String> paymentData, String status) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (status == null || (!status.equals(STATUS_SUCCESS) && !status.equals(STATUS_REJECTED))) {
            throw new IllegalArgumentException("Invalid payment status provided");
        }
        this.order = order;
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
    }

    public void setStatus(String status) {
        if (status == null || (!status.equals(STATUS_SUCCESS) && !status.equals(STATUS_REJECTED))) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }
}