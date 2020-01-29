package cs108.stanford.edu.ShippingCalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void calculateCost(View view){
        EditText input = findViewById(R.id.newweight);
        if(input.getText().toString().isEmpty()){return;}

        double weight = Double.parseDouble(input.getText().toString());

        int cost = 0;
        RadioGroup methods = findViewById(R.id.types);
        int method = methods.getCheckedRadioButtonId();
        switch (method){
            case R.id.next:
                cost = 10;
                break;
            case R.id.second:
                cost = 5;
                break;
            case R.id.standard:
                cost = 3;
                break;
        }
        double currentCost = cost * weight;

        CheckBox insurance = findViewById(R.id.insurance);
        int total;
        if(insurance.isChecked()) total = (int) Math.ceil(currentCost * 1.2);
        else{
            total = (int)Math.ceil(currentCost);
        }

        TextView tv = findViewById(R.id.tbd);
        tv.setText("$" + Integer.toString(total));
    }
}
