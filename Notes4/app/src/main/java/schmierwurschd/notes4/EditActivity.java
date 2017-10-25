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

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

    }

    public void saveNote(View view) {
        // create intent to main activity
        Intent intent = new Intent(this, MainActivity.class);
        EditText editText = (EditText) findViewById(R.id.editTextField);

        // write item into item file
        writeToFile(editText.getText().toString(), "items.txt");

        // jump to main activity
        startActivity(intent);
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
