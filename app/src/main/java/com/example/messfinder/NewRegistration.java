package com.example.messfinder;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewRegistration extends Activity {
    EditText user_name,contact,email,pass;
    Button user_register;
    String pass11="";
    Spinner sp2;
    SQLiteDatabase db1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newregistration);

        user_name=(EditText)findViewById(R.id.fullname);
        contact=(EditText)findViewById(R.id.cnum);
        email=(EditText)findViewById(R.id.main_user_name);
        pass=(EditText)findViewById(R.id.main_password);
        user_register=(Button)findViewById(R.id.main_login);
       // sp1=(Spinner)findViewById(R.id.role) ;

        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean status =true;
                try {
                    if(user_name.getText().toString().length()==0){
                        user_name.setText("Enter Valid Name");
                        status =false;
                    }
                    if(contact.getText().toString().length()!=10){
                        contact.setError("Enter Valid Number");
                        status =false;
                    }

                    if(email.getText().toString().length()==0){
                        email.setError("Enter Valid Email");
                        email.requestFocus();
                        status =false;
                    }
                    if(pass.getText().toString().length()==0){
                        pass.setError("Enter Valid Password");
                        pass.requestFocus();
                        status =false;
                    }

                    if(status) {

                        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
                        db1.execSQL("create table  if not exists userdata(Name varchar(900),Contact varchar(900),Email varchar(900),Password varchar(900),URole varchar(900),Status varchar(900))");
                        ContentValues cv1 = new ContentValues();
                        cv1.put("Name", user_name.getText().toString());
                        cv1.put("Contact", contact.getText().toString());
                        cv1.put("Email", email.getText().toString());
                        cv1.put("Password", pass.getText().toString());
                        cv1.put("URole", "User");
                        cv1.put("Status", "0");
                        db1.insert("userdata", null, cv1);
                        Toast.makeText(NewRegistration.this, "User Added Sucessfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }catch(Exception e){
                    Toast.makeText(NewRegistration.this,""+e,Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
