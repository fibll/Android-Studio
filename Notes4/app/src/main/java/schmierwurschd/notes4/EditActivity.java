package schmierwurschd.notes4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class EditActivity extends AppCompatActivity {

    // variables
    // list
    private ArrayList<String> listItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // read in from file
        String inputFromFile = readFromFile("items.txt");

        // get list out of fileOutput
        getListFromInput(inputFromFile);
    }

    public void saveNote(View view) {
        // create intent to main activity
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextField);

        // add new text to items list
        if(!editText.getText().toString().isEmpty()) {
            listItems.add(editText.getText().toString());
        }

        // move list into one ';' seperated string;
        String outputString = getTokenStringFromList();

        Toast.makeText(getBaseContext(), outputString, Toast.LENGTH_SHORT).show();

        // write item into item file
        writeToFile(outputString, "items.txt");

        // jump to main activity
        startActivity(intent);
    }

    public String getTokenStringFromList() {
        String tokenString = "";
        int index = 0;

        // return with error if string is empty
        if(listItems.isEmpty()) {
            return "error";
        }

        // go through list and put all items to one string
        while(index < listItems.size()) {
            tokenString += listItems.get(index) + ";";
            index++;
        }

        return tokenString;
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
