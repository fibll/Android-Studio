package schmierwurschd.notes4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // variables
    //  List
    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-----

        // initialize List
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);

        // get list items out of list file
        try {
            // string that contains characters of file
            String items = "";

            // file input stream and stream reader
            FileInputStream fileIn = openFileInput("items.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            // create input buffer and read char counter
            char[] inputBuffer = new char[100];
            int charRead;

            // read out of file
            while ((charRead = InputRead.read(inputBuffer)) > 0) {

                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                items += readstring;
            }
            InputRead.close();

            // add items to list
            // TO CHANGE
            listItems.add(items);

            //display data of items.txt
            // Toast.makeText(getBaseContext(), items, Toast.LENGTH_SHORT).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void newNote(View view) {

        // create intent to edit Activity
        Intent intent = new Intent(this, EditActivity.class);

        // jump to edit Activity
        startActivity(intent);

    }
}
