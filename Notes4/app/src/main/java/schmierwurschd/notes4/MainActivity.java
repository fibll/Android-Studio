package schmierwurschd.notes4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

        // read in from file
        String inputFromFile = readFromFile("titels.data");

        // get list out of fileOutput
        if(!inputFromFile.equals("error")) {
            getListFromInput(inputFromFile);
        }

        // initialize the item click listener
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        String noteTitle = String.valueOf(adapterView.getItemAtPosition(position));
                        // if title got 3 points in = substring with 30 characters
                        if(noteTitle.length() >= 33) {
                            noteTitle = noteTitle.substring(0, 30);
                        }

                        Intent intent = new Intent(MainActivity.this, EditActivity.class);

                    }
                }
        );
    }

    public void newNote(View view) {

        // create intent to edit Activity
        Intent intent = new Intent(this, EditActivity.class);

        // jump to edit Activity
        startActivity(intent);

    }

    public int getListFromInput(String input)
    {
        // create StringTokenizer with delimiter ";"
        StringTokenizer stringToken = new StringTokenizer(input, ";");

        if(!stringToken.hasMoreElements())
        {
            return 1;
        }

        // get all the tokens out of input
        while (stringToken.hasMoreTokens()) {

            // add next token to list
            listItems.add(stringToken.nextToken());
        }

        return 0;
    }

    public String readFromFile(String fileName){
        // get list fileContent out of list file
        try {
            // string that contains characters of file
            String fileContent = "";

            // file input stream and stream reader
            FileInputStream fileIn = openFileInput(fileName);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            // create input buffer and read char counter
            char[] inputBuffer = new char[100];
            int charRead;

            // read out of file
            while ((charRead = InputRead.read(inputBuffer)) > 0) {

                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                fileContent += readstring;
            }
            InputRead.close();

            //display data of file
            // Toast.makeText(getBaseContext(), fileContent, Toast.LENGTH_SHORT).show();

            return fileContent;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public int writeToFile(String input, String fileName) {
        // write item into item file
        try {
            // create output stream and output writer
            FileOutputStream fileOut = openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileOut);

            // write into file
            outputWriter.write(input);

            // close file
            outputWriter.close();

            //display file saved message
            //Toast.makeText(getBaseContext(), "File saved successfully!", Toast.LENGTH_SHORT).show();

            return 0;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
