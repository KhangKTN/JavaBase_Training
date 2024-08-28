package FinalJava.Entity;

public abstract class Phone {
    private int type;
    private String phoneId;
    private String brand;
    private String color;
    private int productionYear;
    private long price;
    private long promotionPrice;
    private int warrantyPeriod;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(long promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "type=" + type +
                ", phoneId='" + phoneId + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", productionYear=" + productionYear +
                ", price=" + price +
                ", promotionPrice='" + promotionPrice + '\'' +
                ", warrantyPeriod=" + warrantyPeriod +
                '}';
    }

    public abstract void showInfo();
}
