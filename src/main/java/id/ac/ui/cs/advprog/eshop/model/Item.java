package id.ac.ui.cs.advprog.eshop.model;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class Item {
    protected String itemId;
    protected String itemName;
    protected int itemQuantity;
}