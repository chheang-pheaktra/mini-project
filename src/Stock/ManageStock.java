package Stock;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.DoublePredicate;

public class ManageStock {
   private Integer ProductID;
   private String NameProduct;
   private Integer Qty;
   private Double ProductPrice;
   private LocalDate DateOfProduct;

    public Integer getProductID() {
        return ProductID;
    }

    public void setProductID(Integer productID) {
        ProductID = productID;
    }

    public String getNameProduct() {
        return NameProduct;
    }

    public void setNameProduct(String nameProduct) {
        NameProduct = nameProduct;
    }

    public Integer getQty() {
        return Qty;
    }

    public void setQty(Integer qty) {
        Qty = qty;
    }

    public Double getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(Double productPrice) {
        ProductPrice = productPrice;
    }

    public LocalDate getDateOfProduct() {
        return DateOfProduct;
    }

    public void setDateOfProduct(LocalDate dateOfProduct) {
        DateOfProduct = dateOfProduct;
    }
    public ManageStock(Integer productID, String nameProduct, Integer qty, Double productPrice, LocalDate dateOfProduct){
        this.ProductID=productID;
        this.NameProduct=nameProduct;
        this.Qty=qty;
        this.ProductPrice=productPrice;
        this.DateOfProduct=dateOfProduct;
    }
    public String toString(){
        return "Product"+
                "ProductID"+ProductID+
                "ProductName"+NameProduct+
                "Qyt"+Qty+
                "Price"+ProductPrice+
                "Date"+DateOfProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManageStock that = (ManageStock) o;
        return Objects.equals(ProductID, that.ProductID) && Objects.equals(NameProduct, that.NameProduct) && Objects.equals(Qty, that.Qty) && Objects.equals(ProductPrice, that.ProductPrice) && Objects.equals(DateOfProduct, that.DateOfProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ProductID, NameProduct, Qty, ProductPrice, DateOfProduct);
    }
}
