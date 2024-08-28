package OnTap.Entity;

public class OldPhone extends Phone {
    private int status;
    private int body;
    private double priceWarranty;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public double getPriceWarranty() {
        return priceWarranty;
    }

    public void setPriceWarranty(double priceWarranty) {
        this.priceWarranty = priceWarranty;
    }

    @Override
    public String toString() {
        return super.toString() + "OldPhone{" +
                "status=" + status +
                ", body=" + body +
                ", priceWarranty=" + priceWarranty +
                ", warranty=" + 7 +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(this.toString());
    }
}
