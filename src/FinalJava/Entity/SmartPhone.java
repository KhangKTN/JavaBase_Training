package FinalJava.Entity;

public class SmartPhone extends Phone {
    private String os;
    private int batteryTime;
    private int memoryCard;
    private int screenSize;
    private String fingerPrint;

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

    public int getMemoryCard() {
        return memoryCard;
    }

    public void setMemoryCard(int memoryCard) {
        this.memoryCard = memoryCard;
    }

    public int getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(int screenSize) {
        this.screenSize = screenSize;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    @Override
    public String toString() {
        return super.toString() + " SmartPhone{" +
                "os='" + os + '\'' +
                ", batteryTime=" + batteryTime +
                ", memoryCard=" + memoryCard +
                ", screenSize=" + screenSize +
                ", fingerPrint='" + fingerPrint + '\'' +
                '}';
    }

    @Override
    public void showInfo() {
        System.out.println(this);
    }
}
