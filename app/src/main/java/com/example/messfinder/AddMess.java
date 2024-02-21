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

public class AddMess extends Activity {
    EditText user_name,contact,email,pass,address,lat,lang,menutype;
    Button user_register;
    String pass11="";
    Spinner sp1,sp2;
    SQLiteDatabase db1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addmess);

        user_name=(EditText)findViewById(R.id.fullname);
        contact=(EditText)findViewById(R.id.cnum);
        email=(EditText)findViewById(R.id.main_user_name);
        pass=(EditText)findViewById(R.id.main_password);
        user_register=(Button)findViewById(R.id.main_login);
        address=(EditText)findViewById(R.id.address);
        lat=(EditText)findViewById(R.id.lat);
        lang=(EditText)findViewById(R.id.lang);
        sp1=(Spinner)findViewById(R.id.role) ;

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
                        db1.execSQL("create table  if not exists Mess(Name varchar(900),Contact varchar(900),Email varchar(900),Password varchar(900),Address varchar(900),lat varchar(900),lang varchar(900),MenuType varchar(900))");
                        ContentValues cv1 = new ContentValues();
                        cv1.put("Name", user_name.getText().toString());
                        cv1.put("Contact", contact.getText().toString());
                        cv1.put("Email", email.getText().toString());
                        cv1.put("Password", pass.getText().toString());
                        cv1.put("Address", address.getText().toString());
                        cv1.put("lat", lat.getText().toString());
                        cv1.put("lang", lang.getText().toString());
                        cv1.put("MenuType", sp1.getSelectedItem().toString());
                        db1.insert("Mess", null, cv1);
                        Toast.makeText(AddMess.this, "User Added Sucessfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }catch(Exception e){
                    Toast.makeText(AddMess.this,""+e,Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
