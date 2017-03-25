package pl.edu.pwr.swim.chilczuk.bmi_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Toast do wyświetlania błędnego komunikatu
    // relative layaout
    // stringi w values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.submit_button);
        final TextView result = (TextView) findViewById(R.id.resultTV);
        final EditText mass = (EditText) findViewById(R.id.massET);
        final EditText height = (EditText) findViewById(R.id.heightET);
        final BMIforMKG calc = new BMIforMKG();
        final String bmi_result_hint = calc.countBMI(50, 1.7f)+"";
        result.setHint(bmi_result_hint);
        button.setOnClickListener(new View.OnClickListener() {
            String bmi_result;
            public void onClick(View v) {
                bmi_result = calc.countBMI(Integer.valueOf(mass.getText().toString()), Float.valueOf(height.getText().toString()))+"";
                result.clearComposingText();
                result.setText(bmi_result);
            }
        });
    }

    // dodatkowo
    // butter knife zamiast findViewById
    // test UI przy użyciu jakiegoś frameworka: robotium, espresso albo apium
    // 1 test w espresso

}
