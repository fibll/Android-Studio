package schmierwurschd.notes4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    private int oldNote = 0;
    private int itemPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // get list out of list file
        String inputFromFile = readFromFile("titels.data");
        getListFromInput(inputFromFile);

        // get intent
        Intent intent = getIntent();
        String noteTitle = intent.getStringExtra(MainActivity.MESSAGE_TITLE);
        itemPosition = intent.getIntExtra(MainActivity.MESSAGE_INDEX, -1);

        if(noteTitle != null)
        {
            // load note file with the given title
            EditText editText = (EditText) findViewById(R.id.editTextField);
            String noteText = readFromFile(noteTitle + ".note");
            editText.setText(noteText);
            oldNote = 1;
        }
    }

    public void saveNote(View view) {
        // create intent to main activity
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextField);
        String noteTitle;
        String noteTitleFileName;

        // add just the first line to item list, and if the note is not empty
        StringTokenizer stringToken = new StringTokenizer(editText.getText().toString(), "\n");
        if(stringToken.hasMoreTokens()) {
            noteTitle = stringToken.nextToken();

            // if string is too big, just take 25 characters
            if(noteTitle.length() > 30) {
                // the 3 dots should not be in the file name
                noteTitleFileName = noteTitle.substring(0, 30);
                noteTitle = noteTitleFileName + "...";
            }

            // if old note, delete old item out of item list
            if(oldNote > 0) {
                listItems.remove(itemPosition);
            }

            // add the new title to the beginning
            listItems.add(0, noteTitle);

            // move list into one ';' separated string;
            String outputString = getTokenStringFromList();

            // write title into title file
            writeToFile(outputString, "titels.data");

            // write note into note file
            writeToFile(editText.getText().toString(), noteTitle + ".note");
        }

        // jump to main activity
        startActivity(intent);
    }

    public String getTokenStringFromList() {
        String tokenString = "";
        int index = 0;

        // return with error if string is empty
        if(listItems.isEmpty()) {
            return "error_getTokenStringFromList";
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

        if(!stringToken.hasMoreTokens())
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
            return "error_readFromFile";
        }
    }

    public int writeToFile(String input, String fileName) {
        // write item into item file
        try {
            // create output stream and output writer
            FileOutputStream fileOut = openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);

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
