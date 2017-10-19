package schmierwurschd.notes3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    public static final String MESSAGE_TITLE = "com.example.myfirstapp.MESSAGE_TITLE";
    //public static final String MESSAGE_FILE = "com.example.myfirstapp.MESSAGE_FILE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void saveNote (View view) {
        // intent (to jump to) MainActivity
        Intent intent = new Intent(this, MainActivity.class);

        // stuff to send
        EditText editText = (EditText) findViewById(R.id.editText);
        String title = editText.getText().toString();
        //String file = "file1";

        // send message
        intent.putExtra(MESSAGE_TITLE, title);
        //intent.putExtra(MESSAGE_FILE, file);

        startActivity(intent);
    }
}
