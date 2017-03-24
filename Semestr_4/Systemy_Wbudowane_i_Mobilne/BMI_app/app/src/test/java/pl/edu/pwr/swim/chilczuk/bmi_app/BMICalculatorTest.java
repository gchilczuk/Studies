package pl.edu.pwr.swim.chilczuk.bmi_app;

import org.junit.Test;
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
}
