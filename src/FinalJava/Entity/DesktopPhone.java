package FinalJava.Entity;

public class DesktopPhone extends Phone {
    private String speaker;
    private String wire;

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getWire() {
        return wire;
    }

    public void setWire(String wire) {
        this.wire = wire;
    }

    @Override
    public String toString() {
        return super.toString() + " DesktopPhone{" +
                "wire='" + wire + '\'' +
                ", speaker='" + speaker + '\'' +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(this);
    }
}
