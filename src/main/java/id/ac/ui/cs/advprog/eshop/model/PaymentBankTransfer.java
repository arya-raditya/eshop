package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentBankTransfer {
    public static boolean validatePaymentData(Map<String, String> paymentData) {
        if (paymentData == null) {
            return false;
        }
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName == null || bankName.trim().isEmpty()) {
            return false;
        }
        if (referenceCode == null || referenceCode.trim().isEmpty()) {
            return false;
        }
        return true;
    }
}
