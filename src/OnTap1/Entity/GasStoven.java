package OnTap1.Entity;

public class GasStoven extends Stoven {
    String fire;

    public String getFire() {
        return fire;
    }

    public void setFire(String fire) {
        this.fire = fire;
    }

    @Override
    public String toString() {
        return super.toString() +  "GasStoven{" +
                "fire='" + fire + '\'' +
                '}';
    }
}
