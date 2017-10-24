package schmierwurschd.notes4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void newNote(View view) {

        // create intent to edit Activity
        Intent intent = new Intent(this, EditActivity.class);

        // jump to edit Activity
        startActivity(intent);

    }
}
