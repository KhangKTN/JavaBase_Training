package OnTap.Entity;

public class ForeignPhone extends Phone {
    private String country;
    private double priceWarranty;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getPriceWarranty() {
        return priceWarranty;
    }

    public void setPriceWarranty(double priceWarranty) {
        this.priceWarranty = priceWarranty;
    }

    @Override
    public String toString() {
        return super.toString() + "ForeignPhone{" +
                "country='" + country + '\'' +
                ", priceWarranty=" + priceWarranty +
                ", warranty=" + 7 +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(this.toString());
    }
}
