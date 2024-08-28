package OnTap.Entity;

public class HomePhone extends Phone {
    private int warranty;
    private int range;

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return super.toString() + "HomePhone{" +
                "warranty=" + warranty +
                ", range=" + range +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(toString());
    }
}
