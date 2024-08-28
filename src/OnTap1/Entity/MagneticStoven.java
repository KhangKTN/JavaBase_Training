package OnTap1.Entity;

public class MagneticStoven extends Stoven {
    float watt;
    String timer;

    public float getWatt() {
        return watt;
    }

    public void setWatt(float watt) {
        this.watt = watt;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    @Override
    public String toString() {
        return super.toString() + "MagneticStoven{" +
                "watt=" + watt +
                ", timer='" + timer + '\'' +
                '}';
    }
}
