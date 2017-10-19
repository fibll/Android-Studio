package schmierwurschd.notes3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listItems = new ArrayList<String>();
    //String[] listItems = {"Note1", "Note2", "Note3"};
    // set adapter for List
    private ArrayAdapter adapter;
    private ListView listView;
    private int counter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize List
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        listView.setAdapter(adapter);
        listItems.add("Note " + String.valueOf(counter));

    }

    public void newNote (View view) {
        // new list item
        counter++;
        listItems.add("Note " + String.valueOf(counter));
        adapter.notifyDataSetChanged();


        //Intent intent = new Intent(this, EditActivity.class);

        //startActivity(intent);
    }
}
