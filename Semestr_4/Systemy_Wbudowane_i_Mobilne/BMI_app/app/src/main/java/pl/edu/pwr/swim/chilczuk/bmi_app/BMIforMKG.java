package pl.edu.pwr.swim.chilczuk.bmi_app;

public class BMIforMKG extends BMIabstract {
    static final float MIN_HEIGHT = 0.5f;
    static final float MAX_HEIGHT = 3.0f;
    static final float MIN_WEIGHT = 10.0f;
    static final float MAX_WEIGHT = 600.0f;

    @Override
    public float countBMI(float mass, float height) {
        height = height / 100.0f;
        boolean h = isValidHeight(height), m = isValidMass(mass);
        if (!h && !m) throw new IllegalArgumentException("Wrong parameters");
        else if (!h) throw new InvalidHeightException("<50, 300>");
        else if (!m) throw new InvalidMassException("<10, 600>");
        else return mass / (height * height);
    }

    @Override
    public boolean isValidMass(float mass) {
        return mass >= MIN_WEIGHT && mass <= MAX_WEIGHT;
    }

    @Override
    public boolean isValidHeight(float height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }

}