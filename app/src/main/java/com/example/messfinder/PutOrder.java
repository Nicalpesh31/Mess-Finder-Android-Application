package com.example.messfinder;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PutOrder extends Activity {
    EditText address,noofdays;
    Button putorder;
    SQLiteDatabase db1;
    String messname="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.putorder);
      try {
          SharedPreferences shr= PreferenceManager.getDefaultSharedPreferences(PutOrder.this);
          Intent i1 = new Intent();
          messname = shr.getString("uid","NA");
          Toast.makeText(PutOrder.this, "Your Order Put Sucessfully"+messname, Toast.LENGTH_LONG).show();
          address = (EditText) findViewById(R.id.address);
          noofdays = (EditText) findViewById(R.id.noofdays);

          putorder = (Button) findViewById(R.id.putorder);
          putorder.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  SharedPreferences shr = PreferenceManager.getDefaultSharedPreferences(PutOrder.this);
                  db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
                  db1.execSQL("create table  if not exists Orders(Name varchar(900),Address varchar(900),Udays varchar(900),MessName varchar(900))");
                  ContentValues cv1 = new ContentValues();
                  cv1.put("Name", shr.getString("name", "NA"));
                  cv1.put("Address", address.getText().toString());
                  cv1.put("Udays", noofdays.getText().toString());
                  cv1.put("MessName", messname);
                  db1.insert("Orders", null, cv1);
                  Toast.makeText(PutOrder.this, "Your Order Put Sucessfully", Toast.LENGTH_LONG).show();
                  finish();

              }
          });
      }catch(Exception e){
          Toast.makeText(PutOrder.this, ""+e, Toast.LENGTH_LONG).show();
      }
    }
}
