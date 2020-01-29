package cs108.stanford.edu.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.*;
import android.widget.EditText;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> shoppingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shoppingList  = new ArrayList<String>();
    }

    public void addItem(View view){
        EditText input = findViewById(R.id.newItem);
        String item = input.getText().toString();
        input.setText("");
        shoppingList.add(item);

        String disp = "";
        for(int i = 0; i < shoppingList.size(); i ++){
            disp += shoppingList.get(i) + "\n";
        }
        TextView show = findViewById(R.id.currentItem);
        show.setText(disp);

    }

    public void clearList(View view){
        shoppingList.clear();
        TextView disp = findViewById(R.id.currentItem);
        disp.setText("");
    }

}
