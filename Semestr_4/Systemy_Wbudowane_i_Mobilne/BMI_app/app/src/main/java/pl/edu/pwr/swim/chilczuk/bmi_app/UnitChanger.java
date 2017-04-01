package pl.edu.pwr.swim.chilczuk.bmi_app;

public class UnitChanger {
    float SImass, SIheight;
    float IMPmass, IMPheight;
    
    UnitChanger(){
        SImass = 0f;
        IMPmass = 0f;
        SIheight = 0f;
        IMPheight = 0f;
    }
    
    public void toIMP(float massKG, float heightCM){
        if (massKG != SImass){
            SImass = massKG;
            IMPmass = BMIabstract.fromKGtoLB(SImass);
        }
        if (heightCM != SIheight){
            SIheight = heightCM;
            IMPheight = BMIabstract.fromCMtoIN(SIheight);
        }
    }
    
    public void toSI(float massLB, float heightIN){
        if (massLB != IMPmass){
            IMPmass = massLB;
            SImass = BMIabstract.fromLBtoKG(IMPmass);
        }
        if (heightIN != IMPheight){
            IMPheight = heightIN;
            SIheight = BMIabstract.fromINtoCM(IMPheight);
        }
    }

    public float getSImass() {
        return SImass;
    }

    public float getSIheight() {
        return SIheight;
    }

    public float getIMPmass() {
        return IMPmass;
    }

    public float getIMPheight() {
        return IMPheight;
    }
}
