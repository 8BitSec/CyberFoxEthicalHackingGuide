package com.apps.cyberfox_ethicalhackingguide;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;

public class Linux_Cmds extends AppCompatActivity implements SearchView.OnQueryTextListener{

    DatabaseHelper myDB;

    // Declare Variables
    ListView list;
    ListViewAdapter adapter;
    SearchView editsearch;
    String[] commandsNameList;
    ArrayList<CommandNames> arraylist = new ArrayList<CommandNames>();
    FloatingActionButton fab = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linux__cmds);

        myDB = new DatabaseHelper(this);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_command_dialog();
            }
        });

        // Generate sample data
        Cursor res = myDB.getAllData();

        commandsNameList = new String[50];

        int i = 0;
        while( res.moveToNext() ){
            commandsNameList[i] = res.getString(0) + ":\n" + res.getString(1);
            i++;
        }

        // Locate the ListView in listview_main.xml
        list = (ListView) findViewById(R.id.listview);

        for (i = 0; i < commandsNameList.length; i++) {
            CommandNames commandNames = new CommandNames(commandsNameList[i]);
            // Binds all strings into an array
            arraylist.add(commandNames);
        }

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter(this, arraylist);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.searchview);
        editsearch.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }

    public void add_command_dialog(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addcommanddialog, null);
        dialogBuilder.setView(dialogView);

        final EditText name = dialogView.findViewById(R.id.cmd_name);
        final EditText desc = dialogView.findViewById(R.id.cmd_description);

        dialogBuilder.setTitle("Add Command");
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                boolean isInserted = myDB.insertData(name.getText().toString(),desc.getText().toString());
                if( isInserted ){
                    Toast.makeText(Linux_Cmds.this,"Command added",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Linux_Cmds.this,"Command add failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}
