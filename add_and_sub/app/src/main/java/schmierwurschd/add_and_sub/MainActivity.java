package schmierwurschd.add_and_sub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

import static schmierwurschd.add_and_sub.R.id.radio;

public class MainActivity extends AppCompatActivity {

    public static final String ACTIVITY_MESSAGE = "add_or_sub";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        // do stuff
        Intent intent = new Intent(this, DisplaySolutionActivitiy.class);

        // get field data
        EditText numberTextEdit1 = (EditText) findViewById(R.id.number1_textField);
        EditText numberTextEdit2 = (EditText) findViewById(R.id.number2_textField);

        // convert into int
        int number1 = Integer.parseInt(numberTextEdit1.getText().toString());
        int number2 = Integer.parseInt(numberTextEdit2.getText().toString());

        // calculate
        //if(R.id.add_radioButton);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        //RadioButton radio1 = (RadioButton) findViewById(R.id.add_radioButton);
        int solution = 0;
        if(switch1.isChecked())
            solution = number1 + number2;
        else
            solution = number1 - number2;

        intent.putExtra(ACTIVITY_MESSAGE, String.valueOf(solution));
        startActivity(intent);
    }
}
