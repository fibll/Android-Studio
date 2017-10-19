package schmierwurschd.notes3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
    }

    public void saveNote (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        String message = "title";


        startActivity(intent);
    }
}
