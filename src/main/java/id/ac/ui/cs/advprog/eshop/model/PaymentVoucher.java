package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

public class PaymentVoucher {
    public static boolean validatePaymentData(Map<String, String> paymentData) {
        if (paymentData == null) {
            return false;
        }
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        int digitCount = 0;
        for (int i = 0; i < voucherCode.length(); i++) {
            if (Character.isDigit(voucherCode.charAt(i))) {
                digitCount++;
            }
        }
        return digitCount == 8;
    }
}