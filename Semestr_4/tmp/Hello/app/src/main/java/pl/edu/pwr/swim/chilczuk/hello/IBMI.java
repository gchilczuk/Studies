package pl.edu.pwr.swim.chilczuk.hello;


public interface IBMI {

    float countBMI(float mass, float height);
    boolean isValidMass(float mass);
    boolean isValidHeight(float height);
}
