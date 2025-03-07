package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PaymentVoucherTest {

    @Test
    public void testValidatePaymentDataValid() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("voucherCode", "ESHOP1234ABC5678");
        assertTrue(PaymentVoucher.validatePaymentData(data));
    }

    @Test
    public void testValidatePaymentDataInvalidLength() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("voucherCode", "ESHOP123");
        assertFalse(PaymentVoucher.validatePaymentData(data));
    }

    @Test
    public void testValidatePaymentDataInvalidPrefix() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("voucherCode", "SHOP1234ABC5678");
        assertFalse(PaymentVoucher.validatePaymentData(data));
    }
}