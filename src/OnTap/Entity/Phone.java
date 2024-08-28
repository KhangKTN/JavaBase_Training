package OnTap.Entity;

public abstract class Phone {
    private String ID;
    private String Name;
    private float screenSize;
    private String chipset;
    private int ram;
    private int storage;
    private double price;
    private String Manufacturer;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "ID='" + ID + '\'' +
                ", Name='" + Name + '\'' +
                ", screenSize=" + screenSize +
                ", chipset='" + chipset + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", price=" + price +
                ", Manufacturer='" + Manufacturer + '\'' +
                '}';
    }

    public abstract void showInfo();
}
