package JPL.L.A201.entities;

public class Airplane {
    private String id;
    private String model;
    private float cruiseSpeed;
    private float cruiseAngle;
    private float maxTakeoffWeight;
    private String flyMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(float cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public float getCruiseAngle() {
        return cruiseAngle;
    }

    public void setCruiseAngle(float cruiseAngle) {
        this.cruiseAngle = cruiseAngle;
    }

    public float getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(float maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    public String getFlyMethod() {
        return flyMethod;
    }

    public void setFlyMethod(String flyMethod) {
        this.flyMethod = flyMethod;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ",model=" + model +
                ",cruiseSpeed=" + cruiseSpeed +
                ",cruiseAngle=" + cruiseAngle +
                ",maxTakeoffWeight=" + maxTakeoffWeight +
                ",flyMethod=" + flyMethod;
    }
}
