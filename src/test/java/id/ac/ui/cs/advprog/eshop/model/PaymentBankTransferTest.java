package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PaymentBankTransferTest {

    @Test
    public void testValidatePaymentDataValid() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("bankName", "Bank ABC");
        data.put("referenceCode", "REF123456");
        assertTrue(PaymentBankTransfer.validatePaymentData(data));
    }

    @Test
    public void testValidatePaymentDataInvalidBankName() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("bankName", "");
        data.put("referenceCode", "REF123456");
        assertFalse(PaymentBankTransfer.validatePaymentData(data));
    }

    @Test
    public void testValidatePaymentDataInvalidReferenceCode() {
        Map<String, String> data = new LinkedHashMap<>();
        data.put("bankName", "Bank ABC");
        data.put("referenceCode", "");
        assertFalse(PaymentBankTransfer.validatePaymentData(data));
    }
}