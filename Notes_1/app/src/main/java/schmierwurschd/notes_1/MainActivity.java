package schmierwurschd.notes_1;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.R.attr.data;
import static android.R.attr.path;
import static java.lang.System.in;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    // variables
    public String[] items = {
            "Das",
            "ist",
            "eine",
            "Listen",
            "Anwendung",
            "!",
            "Kann",
            "ich",
            "in",
            "der",
            "Liste",
            "auch",
            "scrollen",
            "?"
    };
    ListView listView;
    ArrayAdapter<String> adapter;

    private static final String TAG = "logMessage";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "Layout was correctly built");

        // initalize listView object
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String chosenItem = String.valueOf(adapterView.getItemAtPosition(i));

                        //Toast.makeText(MainActivity.this, chosenItem, Toast.LENGTH_LONG).show();
                    }


                }
        );
    }

    public void newNote (View view) {
        // do stuff
        try {
            String str="";
            FileInputStream fileIn=openFileInput("data.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[200];
            int charRead;

            while ((charRead=InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                str +=readstring;
            }
            InputRead.close();
            //Toast.makeText(getBaseContext(), str,Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }


        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }
}