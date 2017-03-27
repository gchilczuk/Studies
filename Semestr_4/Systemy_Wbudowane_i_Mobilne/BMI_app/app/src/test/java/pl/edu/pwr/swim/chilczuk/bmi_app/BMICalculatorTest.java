package pl.edu.pwr.swim.chilczuk.bmi_app;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BMICalculatorTest {

    @Test
    public void massUnderZeroIsInvalid() throws Exception {
        // GIVEN
        float testMass = -1.0f;

        // WHEN
        IBMI calc = new BMIforMKG();

        // THEN
        assertFalse(calc.isValidMass(testMass));
    }

    @Test
    public void exampleBMIisValid() throws Exception {
        // GIVEN
        float testMass1 = 50.0f, testMass2 = 80.0f;
        float testHeight1 = 1.95f, testHeight2 = 1.65f;

        // WHEN
        IBMI calc = new BMIforMKG();

        // THEN
        assertEquals(calc.countBMI(testMass1, testHeight1), testMass1/(testHeight1*testHeight1));


    }
}
