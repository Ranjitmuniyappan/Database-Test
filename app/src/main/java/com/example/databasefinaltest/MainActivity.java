package com.example.databasefinaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void insert(View view)
    {
        TextView response = findViewById(R.id.list);
        EditText userId = findViewById(R.id.userId);
        EditText password = findViewById(R.id.password);
        InfoPojo info = new InfoPojo(userId.getText().toString(),password.getText().toString());
        DBHelper db = new DBHelper(this,null,null,1);
        db.addInfo(info);
        response.setText("Data Added Successfully");
        userId.setText("");
        password.setText("");

    }
    public void fetch(View view)
    {
        TextView list = findViewById(R.id.list);
        DBHelper db = new DBHelper(this,null, null,1);
        String data = db.getData();
        list.setText(data);

    }
    public void delete(View view)
    {
        EditText id = findViewById(R.id.ID);
        TextView response = findViewById(R.id.list);
        DBHelper db = new DBHelper(this,null,null,1);
        db.deleteData(Integer.parseInt(id.getText().toString()));
        response.setText("Data Has been Deleted...");
        id.setText("");
    }
    public void update(View view)
    {
        EditText id = findViewById(R.id.ID);
        EditText userId = findViewById(R.id.userId);
        EditText pass = findViewById(R.id.password);
        TextView list = findViewById(R.id.list);
        DBHelper dbHelper = new DBHelper(this,null,null,1);
        dbHelper.updateData(Integer.parseInt(id.getText().toString()),userId.getText().toString(),pass.getText().toString());
        list.setText("Data Has been Updated...");

        id.setText("");
        userId.setText("");
        pass.setText("");
    }
    public void find(View view)
    {
        EditText id = findViewById(R.id.ID);
        TextView list = findViewById(R.id.list);
        DBHelper dbHelper = new DBHelper(this,null,null,1);
        String data= dbHelper.findData(Integer.parseInt(id.getText().toString()));
        list.setText(data);
    }
}
