package JPL.L.A201.entities;

import JPL.L.A201.utils.PlaneTypeConstant;

public class Fixedwing extends Airplane {
    private PlaneTypeConstant planeType;
    private int minNeedRunway;

    public PlaneTypeConstant getPlaneType() {
        return planeType;
    }

    public void setPlaneType(PlaneTypeConstant planeType) {
        this.planeType = planeType;
    }

    public int getMinNeedRunway() {
        return minNeedRunway;
    }

    public void setMinNeedRunway(int minNeedRunway) {
        this.minNeedRunway = minNeedRunway;
    }

    @Override
    public String toString() {
        return super.toString() +
                ",planeType=" + planeType +
                ",minNeedRunway=" + minNeedRunway;
    }
}
