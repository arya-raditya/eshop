package id.ac.ui.cs.advprog.eshop.enums;

public enum PaymentMethod {
    VOUCHER("VOUCHER"),
    TRANSFER_BANK("TRANSFER_BANK");

    private final String value;
    PaymentMethod(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}