package pl.edu.pwr.swim.chilczuk.bmi_app;


public abstract class BMIabstract implements IBMI {

    static float fromLBtoKG(float lb){
        return lb*0.45359237f;
    }
    static float fromINtoCM(float in){
        return in*2.540f;
    }
    static float fromKGtoLB(float kg){
        return kg*2.20462262185f;
    }
    static float fromCMtoIN(float cm){
        return cm*0.393700787402f;
    }
}
