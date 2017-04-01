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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;


public class MainActivity extends AppCompatActivity {
    private IBMI calc;
    @BindView(R2.id.resultTV)
    TextView resultTV;
    @BindView(R2.id.massET)
    EditText massET;
    @BindView(R2.id.heightET)
    EditText heightETp;
    @BindView(R2.id.submit_button)
    Button submitBtn;
    @BindView(R2.id.RBSI)
    RadioButton radioButtonSI;
    @BindView(R2.id.RBIMP)
    RadioButton radioButtonIMP;
    MenuItem menuItemSave, menuItemRestore;

    private Float currentMass, currentHeight, currentBMI;
    private String massHint, heightHint;
    private String currentUnit;
    private Context context;
    private UnitChanger unitChanger = new UnitChanger();
    private int tostVounter =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentUnit = "None";

        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        ButterKnife.bind(this);

        if (isSomethingSaved()) {
            onRestore();
        } else {
            SImode();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        reSetOnCurrent();
        outState.putFloat("mass", currentMass);
        outState.putFloat("height", currentHeight);
        outState.putString("unit", currentUnit);
        outState.putFloat("BMI", currentBMI);

        outState.putFloat("UCSImass", unitChanger.getSImass());
        outState.putFloat("UCSIheight", unitChanger.getSIheight());
        outState.putFloat("UCIMPmass", unitChanger.getIMPmass());
        outState.putFloat("UCIMPheight", unitChanger.getIMPheight());

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentMass = savedInstanceState.getFloat("mass");
        currentHeight = savedInstanceState.getFloat("height");
        setCurrentUnit(savedInstanceState.getString("unit"));
        currentBMI = savedInstanceState.getFloat("BMI");

        float a, b, c, d;
        a = savedInstanceState.getFloat("UCSImass", 0f);
        b = savedInstanceState.getFloat("UCSIheight", 0f);
        c = savedInstanceState.getFloat("UCIMPmass", 0f);
        d = savedInstanceState.getFloat("UCIMPheight", 0f);
        unitChanger = new UnitChanger(a, b, c, d);

        chooseCalc();
        reGetOfCurrent();
//        toaster("BMI to: "+resultTV.getText().toString());

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actions, menu);
        menuItemSave = menu.findItem(R.id.SAVE);
        menuItemRestore = menu.findItem(R.id.RESTORE);
        if (isSomethingSaved()) {
            menuItemRestore.setEnabled(true);
        } else menuItemRestore.setEnabled(false);
        if (getMass() == 0f || getHeight() == 0f) {
            if (menuItemSave != null) menuItemSave.setEnabled(false);
        } else if (getMass() > 0f && getHeight() > 0f) {
            if (menuItemSave != null) menuItemSave.setEnabled(true);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.SHARE) {
            String message = getString(R.string.share_text) + currentBMI;
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(share, "Share"));
        } else if (id == R.id.AUTH) {
            Intent intent = new Intent(this, AuthorActivity.class);
            startActivity(intent);
        } else if (id == R.id.SAVE) {
            onSave();
        } else if (id == R.id.RESTORE) {
            onRestore();
            /*toaster("teraz pobiorę tekst");
            massET.getText();
            massET.setHint("");*/
        }

        return super.onOptionsItemSelected(item);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.RBIMP:
                if (checked)
                    changeToIMP();
                break;
            case R.id.RBSI:
                if (checked)
                    changeToSI();
                break;
        }
    }

   /* @OnTextChanged(R2.id.resultTV)
    public void bmichanged(){
        toaster("BMI teraz wynosi: "+getBMI());
    }*/

    protected void onSave() {
        reSetOnCurrent();
        if (areCurrentOK()) {
            SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString("mass", massET.getText().toString());
            editor.putString("height", heightETp.getText().toString());
            editor.putString("unit", currentUnit);
            editor.putBoolean("saved", true);

            editor.putFloat("UCSImass", unitChanger.getSImass());
            editor.putFloat("UCSIheight", unitChanger.getSIheight());
            editor.putFloat("UCIMPmass", unitChanger.getIMPmass());
            editor.putFloat("UCIMPheight", unitChanger.getIMPheight());

            editor.commit();
            menuItemRestore.setEnabled(true);
        }
    }

    protected void onRestore() {
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        if (!sharedPrefs.getBoolean("saved", false))
            throw new IllegalStateException("Nothing to restore!");

        float a, b, c, d;
        a = sharedPrefs.getFloat("UCSImass", 0f);
        b = sharedPrefs.getFloat("UCSIheight", 0f);
        c = sharedPrefs.getFloat("UCIMPmass", 0f);
        d = sharedPrefs.getFloat("UCIMPheight", 0f);
        unitChanger = new UnitChanger(a, b, c, d);

        setCurrentUnit(sharedPrefs.getString("unit", "None"));
        if (!currentUnit.equals("None")) {
            massET.setText(sharedPrefs.getString("mass", ""));
            heightETp.setText(sharedPrefs.getString("height", ""));
//            toaster("setCurrentUnit");
            reSetOnCurrent();
        }
    }

    public void calculateBMI(View v) {
        try {
            reSetOnCurrent();
//            toaster(currentUnit + " | " + currentMass + " | " + currentHeight);
            currentBMI = calc.countBMI(currentMass, currentHeight);
            showBMIresult(currentBMI);

            hideKeyboar(v);

        } catch (InvalidMassException e) {
            toaster(getString(R.string.ExcMass));
        } catch (InvalidHeightException e) {
            toaster(getString(R.string.ExcHeight));
        } catch (IllegalArgumentException e) {
            toaster(getString(R.string.ExcMassHeight));
        } catch (Exception e) {
            toaster(getString(R.string.ExcOther) /*+ e.getMessage()*/);
            e.printStackTrace();
        }

    }

    private void SImode() {
        massHint = getString(R.string.mass_hint_kg);
        heightHint = getString(R.string.height_hint_m);
        currentUnit = "SI";
        chooseCalc();
        setHints();
    }

    private void IMPmode() {
        massHint = getString(R.string.mass_hint_lb);
        heightHint = getString(R.string.height_hint_in);
        currentUnit = "IMP";
        chooseCalc();
        setHints();
    }

    private void changeToSI() {
        if (!currentUnit.equals("SI")) {
            unitChanger.toSI(getMass(), getHeight());
            float newMass = unitChanger.getSImass();
            float newHeight = unitChanger.getSIheight();
            SImode();
            afterModeChange(newMass, newHeight);
        }
    }

    private void changeToIMP() {
        if (!currentUnit.equals("IMP")) {
            unitChanger.toIMP(getMass(), getHeight());
            float newMass = unitChanger.getIMPmass();
            float newHeight = unitChanger.getIMPheight();
            IMPmode();
            afterModeChange(newMass, newHeight);
        }
    }

    private void afterModeChange(float mass, float height) {
        if (mass != 0.0f) massET.setText(String.valueOf(mass));
        if (height != 0.0f) heightETp.setText(String.valueOf(height));
//        toaster("afterModeChange");
        if (mass != 0.0f && height != 0.0f) showBMIresult(calc.countBMI(mass, height));
        reSetOnCurrent();
    }


    @OnTextChanged({R2.id.massET, R2.id.heightET})
    public void onInputTextChange() {
        if (getMass() == 0f || getHeight() == 0f) {
            if (submitBtn != null) submitBtn.setEnabled(false);
            if (menuItemSave != null) menuItemSave.setEnabled(false);
        } else if (getMass() > 0f && getHeight() > 0f) {
            if (submitBtn != null) submitBtn.setEnabled(true);
            if (menuItemSave != null) menuItemSave.setEnabled(true);
        }
//        toaster("tekst changed: "+getMass()+" | "+getHeight());
    }

    private void setCurrentUnit(String mode) {
        if (mode.equals("SI")) {
            radioButtonSI.performClick();
        } else if (mode.equals("IMP")) {
            radioButtonIMP.performClick();
        }
    }

    /*
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ################################################################################################
    ####################################### ↓ Should be ok ↓ #######################################
    ################################################################################################
    ////////////////////////////////////////////////////////////////////////////////////////////////
    */

    private boolean isSomethingSaved() {
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        return sharedPrefs.contains("saved");
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

    private void toaster(String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        /*tostVounter+=1;
        toast = Toast.makeText(context, "tost nr "+tostVounter, Toast.LENGTH_SHORT);
        toast.show();*/
    }

    private void hideKeyboar(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private void reSetOnCurrent() {
        currentMass = getMass();
        currentHeight = getHeight();
        currentBMI = getBMI();
    }

    private void reGetOfCurrent() {
        if (areCurrentOK()) {
            massET.setText(String.valueOf(currentMass));
            heightETp.setText(String.valueOf(currentHeight));
//            toaster("reGetOfCurrent");
            showBMIresult(currentBMI);
//            toaster(currentBMI + "");
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

    float getBMI() {
        float value = 0.0f;
        String BMIString = resultTV.getText().toString();
        if (!BMIString.equals("")) {
            BMIString = BMIString.replace(',', '.');
            value = Float.valueOf(BMIString);
        }
        return value;
    }

    private boolean areCurrentOK() {
        boolean isOK = true;
        float tmp;
        try {
            tmp = calc.countBMI(currentMass, currentHeight);
        } catch (IllegalArgumentException e) {
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
        else if (40 <= BMI) color = ContextCompat.getColor(context, R.color.obessIII);
        else color = ContextCompat.getColor(context, R.color.black);
        return color;
    }

    private void chooseCalc() {
        switch (currentUnit) {
            case "SI":
                calc = new BMIforMKG();
                break;
            case "IMP":
                calc = new BMIforLBIN();
                break;
            default:
                toaster(getString(R.string.ExcOther));
        }
    }


    // pamiętanie raz wpisanych danych
    // button zapisywania | ma być aktywny tylko przy aktywnych danych
    // button share wyszeruj np na fb (intent) | aktywne gdy wyliczony wynik

    // dodatkowo
    // test UI przy użyciu jakiegoś frameworka: robotium, espresso albo apium
    // 1 test w espresso

    // DONE

    // osobna ACTIVITY about_autor
    // a tam: Image_view ze zdjęciem
    // otwierane z apki, dowolny layout


    // butter knife zamiast findViewById
    // każda aktywność ma reagować na rotacje
}
