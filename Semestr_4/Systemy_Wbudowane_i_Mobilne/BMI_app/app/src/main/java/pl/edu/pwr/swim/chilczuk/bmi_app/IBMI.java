package pl.edu.pwr.swim.chilczuk.bmi_app;

public interface IBMI {

    float countBMI(float mass, float height);
    boolean isValidMass(float mass);
    boolean isValidHeight(float height);
}
