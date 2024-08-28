package JPL.L.A201.entities;

public class Helicopter extends Airplane {
    private int range;

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return super.toString() + ",range=" + range;
    }
}
