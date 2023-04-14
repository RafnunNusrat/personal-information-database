 package com.example.labexam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etDOB, etContact;
    Button btnInsert, btnUpdate, btnDelete, btnView;
    TextView tvTitle;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle=findViewById(R.id.tvTitle);
        etName=findViewById(R.id.etName);
        etContact=findViewById(R.id.etContact);
        etDOB=findViewById(R.id.etDOB);
        btnInsert=findViewById(R.id.btnInsert);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnView=findViewById(R.id.btnView);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=etName.getText().toString();
                String contactTXT=etContact.getText().toString();
                String dobTXT=etDOB.getText().toString();

                Boolean checkinsertdata=DB.insertUserData(nameTXT,contactTXT,dobTXT);
                if (checkinsertdata=true)
                    Toast.makeText(MainActivity.this,"New Entry Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Colud not Insert new entry",Toast.LENGTH_LONG).show();
            }

        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=etName.getText().toString();
                String contactTXT=etContact.getText().toString();
                String dobTXT=etDOB.getText().toString();



                Boolean checkupdatedata=DB.updateUserData(nameTXT,contactTXT,dobTXT);
                if (checkupdatedata=true)
                    Toast.makeText(MainActivity.this,"Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Could not update",Toast.LENGTH_LONG).show();
            }

        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT=etName.getText().toString();


                Boolean checkdeletedata=DB.deleteUserData(nameTXT);
                if (checkdeletedata=true)
                    Toast.makeText(MainActivity.this,"Entry deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Could Not delete entry",Toast.LENGTH_LONG).show();
            }

        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Entry Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n");

                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}