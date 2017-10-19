package schmierwurschd.add_and_sub;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplaySolutionActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_solution_activitiy);

        // get intent data
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.ACTIVITY_MESSAGE);

        // write message into text field
        TextView textView = (TextView) findViewById(R.id.solution_textView);
        textView.setText(message);
    }
}
