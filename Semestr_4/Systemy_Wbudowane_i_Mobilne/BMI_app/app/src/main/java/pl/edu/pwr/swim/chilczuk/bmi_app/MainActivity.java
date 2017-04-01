package pl.edu.pwr.swim.chilczuk.bmi_app;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private IBMI calc;
    private TextView resultTV;
    private EditText massET, heightETp;
    private Float currentMass, currentHeight, currentBMI;
    private String massHint, heightHint;
    private String currentUnit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        resultTV = (TextView) findViewById(R.id.resultTV);
        massET = (EditText) findViewById(R.id.massET);
        heightETp = (EditText) findViewById(R.id.heightET);

        SImode();
        try{
            onRestore();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        reSetOnCurrent();
        outState.putFloat("mass", currentMass);
        outState.putFloat("height", currentHeight);
        outState.putString("unit", currentUnit);
        outState.putFloat("BMI", currentBMI);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentMass = savedInstanceState.getFloat("mass");
        currentHeight = savedInstanceState.getFloat("height");
        currentUnit = savedInstanceState.getString("unit");
        currentBMI = savedInstanceState.getFloat("BMI");
        reGetOfCurrent();

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.MI_SI) {
            if (!currentUnit.equals("SI"))
                changeToSI();
            return true;
        } else if (id == R.id.MI_IMP) {
            if (!currentUnit.equals("IMP"))
                changeToIMP();
            return true;
        } else if (id == R.id.SHARE) {
            String message = "Jeden pomidor jest obowiązkowy";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);

            startActivity(Intent.createChooser(share, "TiTlE"));
        } else if (id == R.id.AUTH){
            Intent intent = new Intent(this, AuthorActivity.class);
            startActivity(intent);
        } else if (id == R.id.SAVE){
            onSave();
        } else if (id == R.id.RESTORE) {
            onRestore();
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onSave() {
        reSetOnCurrent();
        if (areCurrentOK()) {
            SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("mass", massET.getText().toString());
            editor.putString("height", heightETp.getText().toString());
            editor.putString("unit", currentUnit);
            editor.commit();
        }
    }
    protected void onRestore() {
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        currentUnit = sharedPrefs.getString("unit", "SI");
        if (currentUnit.equals("SI")){
            SImode();
        } else if (currentUnit.equals("IMP")){
            IMPmode();
        }
        massET.setText(sharedPrefs.getString("mass", ""));
        heightETp.setText(sharedPrefs.getString("height", ""));
        reSetOnCurrent();
        currentBMI = calc.countBMI(currentMass, currentHeight);
        showBMIresult(currentBMI);
    }

    public void calculateBMI(View v){
        try {
            reSetOnCurrent();
            currentBMI = calc.countBMI(currentMass, currentHeight);
            showBMIresult(currentBMI);

            hideKeyboar(v);

        }catch (InvalidMassException e){
            toaster(getString(R.string.ExcMass));
        }catch (InvalidHeightException e){
            toaster(getString(R.string.ExcHeight));
        }catch (IllegalArgumentException e) {
            toaster(getString(R.string.ExcMassHeight));
        } catch (Exception e) {
            toaster(getString(R.string.ExcOther));
        } finally {
            resultTV.setText("");
        }

    }

    private void SImode() {
        massHint = getString(R.string.mass_hint_kg);
        heightHint = getString(R.string.height_hint_m);

        calc = new BMIforMKG();

        setHints();
        currentUnit = "SI";
    }

    private void changeToSI() {
        float newMass = BMIabstract.fromLBtoKG(getMass());
        float newHeight = BMIabstract.fromINtoCM(getHeight());
        SImode();
        afterModeChange(newMass, newHeight);
    }

    private void IMPmode() {
        massHint = getString(R.string.mass_hint_lb);
        heightHint = getString(R.string.height_hint_in);

        calc = new BMIforLBIN();

        setHints();
        currentUnit = "IMP";
    }

    private void changeToIMP() {
        float newMass = BMIabstract.fromKGtoLB(getMass());
        float newHeight = BMIabstract.fromCMtoIN(getHeight());
        IMPmode();
        afterModeChange(newMass, newHeight);
    }

    private void afterModeChange(float mass, float height) {
        if (mass != 0.0f) massET.setText(String.valueOf(mass));
        if (height != 0.0f) heightETp.setText(String.valueOf(height));
        if (mass != 0.0f && height != 0.0f) showBMIresult(calc.countBMI(mass, height));
    }

    private void setHints() {
        massET.setHint(massHint);
        heightETp.setHint(heightHint);
    }

    private void showBMIresult(float bmiResult) {
        CharSequence bmiResCSeq = String.format("%.2f", bmiResult);
        resultTV.setText(bmiResCSeq);
        resultTV.setTextColor(chooseColor(bmiResult));
    }

    private void toaster(String text){
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }

    private void hideKeyboar(View v){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void reSetOnCurrent(){
        currentMass = getMass();
        currentHeight = getHeight();
        currentBMI = getBMI();
    }

    private void reGetOfCurrent(){
        if (areCurrentOK()){
            massET.setText(String.valueOf(currentMass));
            heightETp.setText(String.valueOf(currentHeight));
            resultTV.setText(String.valueOf(currentBMI));
        }
    }

    float getMass() {
        float value = 0.0f;
        String massString = massET.getText().toString();
        if (!massString.equals(""))
            value = Float.valueOf(massString);
        return value;
    }

    float getHeight() {
        float value = 0.0f;
        String heightString = heightETp.getText().toString();
        if (!heightString.equals(""))
            value = Float.valueOf(heightString);
        return value;
    }

    float getBMI(){
        float value = 0.0f;
        String BMIString = resultTV.getText().toString();
        if (!BMIString.equals("")) {
            BMIString = BMIString.replace(',', '.');
            value = Float.valueOf(BMIString);
        }
        return value;
    }

    private boolean areCurrentOK(){
        boolean isOK;
        float tmp;
        try{
            tmp = calc.countBMI(currentMass, currentHeight);
            isOK = String.format("%.2f", currentBMI).equals(String.format("%.2f", tmp));
        }catch (IllegalArgumentException e){
            isOK = false;
        }
        return isOK;
    }


    private int chooseColor(float BMI) {
        int color;
        if (BMI < 15) color = ContextCompat.getColor(context, R.color.underweightIII);
        else if (15.0f <= BMI && BMI < 16.0f)
            color = ContextCompat.getColor(context, R.color.underweightII);
        else if (16.0f <= BMI && BMI < 18.5f)
            color = ContextCompat.getColor(context, R.color.underweightI);
        else if (18.5f <= BMI && BMI < 25)
            color = ContextCompat.getColor(context, R.color.normalWeight);
        else if (25 <= BMI && BMI < 30) color = ContextCompat.getColor(context, R.color.overweight);
        else if (30 <= BMI && BMI < 35) color = ContextCompat.getColor(context, R.color.obessI);
        else if (35 <= BMI && BMI < 40) color = ContextCompat.getColor(context, R.color.obessII);
        else if (40 < BMI) color = ContextCompat.getColor(context, R.color.obessIII);
        else color = ContextCompat.getColor(context, R.color.black);
        return color;
    }

    // osobna ACTIVITY about_autor
    // a tam: Image_view ze zdjęciem
    // otwierane z apki, dowolny layout

    // każda aktywność ma reagować na rotacje
    // pamiętanie raz wpisanych danych
    // button zapisywania | ma być aktywny tylko przy aktywnych danych
    // button share wyszeruj np na fb (intent) | aktywne gdy wyliczony wynik

    // dodatkowo
    // butter knife zamiast findViewById
    // test UI przy użyciu jakiegoś frameworka: robotium, espresso albo apium
    // 1 test w espresso

}
