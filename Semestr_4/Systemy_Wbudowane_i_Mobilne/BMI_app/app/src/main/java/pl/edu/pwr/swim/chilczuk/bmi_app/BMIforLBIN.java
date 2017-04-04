package pl.edu.pwr.swim.chilczuk.bmi_app;

public class BMIforLBIN extends BMIabstract{
    static final float MIN_HEIGHT = 20f;
    static final float MAX_HEIGHT = 120f;
    static final float MIN_WEIGHT = 20.0f;
    static final float MAX_WEIGHT = 1300.0f;

    @Override
    public float countBMI(float mass, float height) {
        boolean h = isValidHeight(height), m = isValidMass(mass);
        if (!h && !m) throw new IllegalArgumentException("Wrong parameters");
        else if (!h) throw new InvalidHeightException("<20, 120>");
        else if (!m) throw new InvalidMassException("<20, 1300>");
        return mass/(height*height) * 703;
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
