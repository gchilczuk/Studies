package pl.edu.pwr.swim.chilczuk.bmi_app;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    IBMI calc;
    Button submit_button;
    TextView resultTV;
    EditText massET, heightETp;
    Float defaultMass, defeaultHeight;
    String bmiResultHint, massHint, heightHint;
    Boolean no_error = true;
    Context context;

    private void SImode(){
        defaultMass = 70.0f;
        defeaultHeight = 1.8f;

        massHint = getString(R.string.mass_hint_kg);
        heightHint = getString(R.string.height_hint_m);

        calc = new BMIforMKG();

        setHints();
    }

    private void IMPmode(){
        defaultMass = 154.0f;
        defeaultHeight = 66.9f;

        massHint = getString(R.string.mass_hint_lb);
        heightHint = getString(R.string.height_hint_in);

        calc = new BMIforLBIN();

        setHints();
    }

    private void setHints(){
        massET.getText().clear();
        heightETp.getText().clear();
        resultTV.setText("");

        bmiResultHint = String.format("%.2f", calc.countBMI(defaultMass, defeaultHeight));
        massET.setHint(massHint);
        heightETp.setHint(heightHint);
        resultTV.setHint(bmiResultHint);
    }

    private void showBMIresult(float bmiResult){
        CharSequence bmiResCSeq = String.format("%.2f",bmiResult);
        resultTV.setText(bmiResCSeq);
        resultTV.setTextColor(chooseColor(bmiResult));
    }

    private int chooseColor(float BMI){
        int color;
        if (BMI < 15) color = ContextCompat.getColor(context, R.color.Underweight_III);
        else if (15.0f <= BMI && BMI < 16.0f) color = ContextCompat.getColor(context, R.color.Underweight_II);
        else if (16.0f <= BMI && BMI < 18.5f) color = ContextCompat.getColor(context, R.color.Underweight_I);
        else if (18.5f <= BMI && BMI < 25) color = ContextCompat.getColor(context, R.color.Normal_weight);
        else if (25 <= BMI && BMI < 30) color = ContextCompat.getColor(context, R.color.Overweight);
        else if (30 <= BMI && BMI < 35) color = ContextCompat.getColor(context, R.color.Obess_I);
        else if (35 <= BMI && BMI < 40) color = ContextCompat.getColor(context, R.color.Obess_II);
        else if (40 < BMI) color = ContextCompat.getColor(context, R.color.Obess_III);
        else  color = ContextCompat.getColor(context, R.color.Black);
        return color;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        submit_button = (Button) findViewById(R.id.submit_button);
        resultTV = (TextView) findViewById(R.id.resultTV);
        massET = (EditText) findViewById(R.id.massET);
        heightETp = (EditText) findViewById(R.id.heightET);

        SImode();

        View.OnClickListener listener = createOnClickListener();
        submit_button.setOnClickListener(listener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.MI_SI) {
            SImode();
            return true;
        }else if(id == R.id.MI_IMP){
            IMPmode();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener createOnClickListener(){
        return new View.OnClickListener() {
            float bmi_result, massF, heightF;
            Toast toast;
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

            private void getValues(){
                massF = Float.valueOf(massET.getText().toString());
                heightF = Float.valueOf(heightETp.getText().toString());
            }

            public void onClick(View v) {
                try {
                    no_error = true;
                    getValues();
                    bmi_result = calc.countBMI(massF, heightF);
                    showBMIresult(bmi_result);

                    if (no_error)  imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                } catch (NumberFormatException e){
                    toast = Toast.makeText(context, "Please input value", Toast.LENGTH_SHORT);
                    toast.show();
                } catch (IllegalArgumentException e){
                    toast = Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                } catch (Exception e){
                    toast = Toast.makeText(context, "Something gone wrong", Toast.LENGTH_SHORT);
                    toast.show();
                } finally {
                    no_error = false;
                }
            }
        };
    }
    // dodatkowo
    // butter knife zamiast findViewById
    // test UI przy użyciu jakiegoś frameworka: robotium, espresso albo apium
    // 1 test w espresso

}