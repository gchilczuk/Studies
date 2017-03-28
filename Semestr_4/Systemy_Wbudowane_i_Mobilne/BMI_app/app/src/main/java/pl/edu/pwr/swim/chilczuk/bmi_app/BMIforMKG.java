package pl.edu.pwr.swim.chilczuk.bmi_app;

public class BMIforMKG extends BMIabstract {
    static final float MIN_HEIGHT = 0.5f;
    static final float MAX_HEIGHT = 3.0f;
    static final float MIN_WEIGHT = 10.0f;
    static final float MAX_WEIGHT = 600.0f;

    @Override
    public float countBMI(float mass, float height) {
        if (!isValidHeight(height) || !isValidMass(mass)) throw new IllegalArgumentException("Wrong parameters");
        else return mass/(height*height);
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