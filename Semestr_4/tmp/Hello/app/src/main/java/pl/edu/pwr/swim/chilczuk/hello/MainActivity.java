package pl.edu.pwr.swim.chilczuk.hello;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText massEditText = (EditText) findViewById(R.id.massET);
        massEditText.setText("napisik");
    }
}
