package com.tanmoy.tschat;

import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private EditText editText;
    private ListView listView;
    private String names;
     EditText edit;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> list=new ArrayList<>();
    private DatabaseReference root= FirebaseDatabase.getInstance().getReference().getRoot();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button)findViewById(R.id.button);
        editText=(EditText)findViewById(R.id.editText);
        listView=(ListView)findViewById(R.id.list);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(arrayAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> map=new HashMap<String, Object>();
                map.put(edit.getText().toString(),"");
                root.updateChildren(map);
            }
        });

        user();
    }
    private  void user()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("enter username: ");
         edit=new EditText(this);
        builder.setView(edit);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                names=edit.getText().toString();

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

   user();         }
        });
        builder.show();
    }
}
