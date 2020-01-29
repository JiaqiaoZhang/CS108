package cs108.stanford.edu.colorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeColor(View view){
        SeekBar redBar = findViewById(R.id.redbar);
        SeekBar blueBar = findViewById(R.id.bluebar);
        SeekBar greenBar = findViewById(R.id.greenbar);

        int red = redBar.getProgress();
        int green = greenBar.getProgress();
        int blue = blueBar.getProgress();

        View back = findViewById(R.id.background);
        back.setBackgroundColor(Color.rgb(red, green, blue));

        TextView redView = findViewById(R.id.red);
        TextView blueView = findViewById(R.id.blue);
        TextView greenView = findViewById(R.id.green);

        redView.setText(Integer.toString(red));
        blueView.setText(Integer.toString(blue));
        greenView.setText(Integer.toString(green));
    }
}
