package OnTap1.Entity;

public class Stoven {
    int type;
    String stovenID;
    String brand;
    String productCode;
    String productionCountry;
    int productionYear;
    int noOfStoven;
    long price;
    long salePrice;
    int warrantYear;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStovenID() {
        return stovenID;
    }

    public void setStovenID(String stovenID) {
        this.stovenID = stovenID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductionCountry() {
        return productionCountry;
    }

    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public int getNoOfStoven() {
        return noOfStoven;
    }

    public void setNoOfStoven(int noOfStoven) {
        this.noOfStoven = noOfStoven;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(long salePrice) {
        this.salePrice = salePrice;
    }

    public int getWarrantYear() {
        return warrantYear;
    }

    public void setWarrantYear(int warrantYear) {
        this.warrantYear = warrantYear;
    }

    @Override
    public String toString() {
        return "Stoven{" +
                "type=" + type +
                ", stovenID='" + stovenID + '\'' +
                ", brand='" + brand + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productionCountry='" + productionCountry + '\'' +
                ", productionYear=" + productionYear +
                ", noOfStoven=" + noOfStoven +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", warrantYear=" + warrantYear +
                '}';
    }
}
