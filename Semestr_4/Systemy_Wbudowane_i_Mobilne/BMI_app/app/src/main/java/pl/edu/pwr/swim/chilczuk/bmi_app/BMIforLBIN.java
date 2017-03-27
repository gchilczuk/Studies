package pl.edu.pwr.swim.chilczuk.bmi_app;

public class BMIforLBIN implements IBMI{
    static final float MIN_HEIGHT = 19.685f;
    static final float MAX_HEIGHT = 118.110f;
    static final float MIN_WEIGHT = 22.0f;
    static final float MAX_WEIGHT = 1322.0f;

    @Override
    public float countBMI(float mass, float height) {
        if (!isValidHeight(height) || !isValidMass(mass)) throw new IllegalArgumentException("Wrong parameters");
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
