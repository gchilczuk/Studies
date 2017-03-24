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
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result.clearComposingText();
                result.setText(calc.countBMI(mass.getAlpha(), height.getAlpha())+1+"");
            }
        });
    }

    // dodatkowo
    // butter knife zamiast findViewById
    // test UI przy użyciu jakiegoś frameworka: robotium, espresso albo apium
    // 1 test w espresso

}
