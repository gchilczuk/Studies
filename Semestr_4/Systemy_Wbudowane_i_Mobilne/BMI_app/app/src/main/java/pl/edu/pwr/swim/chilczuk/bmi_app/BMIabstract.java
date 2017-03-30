package pl.edu.pwr.swim.chilczuk.bmi_app;


public abstract class BMIabstract implements IBMI {

    static float fromLBtoKG(float lb){
        return lb*0.453593f;
    }
    static float fromINtoCM(float in){
        return in*2.540f;
    }
    static float fromKGtoLB(float kg){
        return kg*2.20462f;
    }
    static float fromCMtoIN(float cm){
        return cm*0.393701f;
    }
}
