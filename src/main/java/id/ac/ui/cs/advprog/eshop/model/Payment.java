package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;
import java.util.UUID;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

@Getter
public class Payment {
    private String id;
    private Order order;
    private String method;
    private Map<String, String> paymentData;
    private String status;

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
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            boolean valid = validatePaymentData(paymentData);
            this.status = valid ? PaymentStatus.SUCCESS.getValue() : PaymentStatus.REJECTED.getValue();
        }
    }

    public Payment(Order order, String method, Map<String, String> paymentData, String status) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        if (method == null || method.isEmpty()) {
            throw new IllegalArgumentException("Payment method cannot be null or empty");
        }
        if (status == null || 
            (!status.equals(PaymentStatus.SUCCESS.getValue()) && !status.equals(PaymentStatus.REJECTED.getValue()))) {
            throw new IllegalArgumentException("Invalid payment status provided");
        }
        this.order = order;
        this.id = UUID.randomUUID().toString();
        this.method = method;
        this.paymentData = paymentData;
        this.status = status;
    }

    private boolean validatePaymentData(Map<String, String> paymentData) {
        if (this.method.equals(PaymentMethod.VOUCHER.getValue())) {
            return PaymentVoucher.validatePaymentData(paymentData);
        } else if (this.method.equals(PaymentMethod.TRANSFER_BANK.getValue())) {
            return PaymentBankTransfer.validatePaymentData(paymentData);
        }
        return false;
    }

    public void setStatus(String status) {
        if (status == null ||
           (!status.equals(PaymentStatus.SUCCESS.getValue()) &&
            !status.equals(PaymentStatus.REJECTED.getValue()))) {
            throw new IllegalArgumentException("Invalid payment status");
        }
        this.status = status;
    }

}