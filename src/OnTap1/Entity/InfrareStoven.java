package OnTap1.Entity;

public class InfrareStoven extends Stoven {
    float watt;
    String cookMode;

    public float getWatt() {
        return watt;
    }

    public void setWatt(float watt) {
        this.watt = watt;
    }

    public String getCookMode() {
        return cookMode;
    }

    public void setCookMode(String cookMode) {
        this.cookMode = cookMode;
    }

    @Override
    public String toString() {
        return super.toString() + "InfrareStoven{" +
                "watt=" + watt +
                ", cookMode='" + cookMode + '\'' +
                '}';
    }
}
