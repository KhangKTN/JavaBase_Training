package FinalJava.Entity;

public class KeypadPhone extends Phone{
    private String os;
    private int batteryTime;
    private String keyboard;

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getBatteryTime() {
        return batteryTime;
    }

    public void setBatteryTime(int batteryTime) {
        this.batteryTime = batteryTime;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public String toString() {
        return super.toString() + " KeypadPhone{" +
                "os='" + os + '\'' +
                ", batteryTime=" + batteryTime +
                ", keyboard='" + keyboard + '\'' +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(this);
    }
}
