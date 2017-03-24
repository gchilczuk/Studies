package pl.edu.pwr.swim.chilczuk.hello;


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
}
