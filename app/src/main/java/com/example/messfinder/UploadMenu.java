package com.example.messfinder;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UploadMenu extends Activity {
    EditText menuname,menudate,menudesc,otherdetails;
    Button user_register;
    String pass11="";
    Spinner sp1,sp2;
    SQLiteDatabase db1;

    final Calendar myCalendar= Calendar.getInstance();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uploadmenu);

        menuname=(EditText)findViewById(R.id.menuname);
        menudate=(EditText)findViewById(R.id.menudate);
        menudesc=(EditText)findViewById(R.id.menudesc);
        otherdetails=(EditText)findViewById(R.id.otherdetails);
        user_register=(Button)findViewById(R.id.main_login);
        sp1=(Spinner)findViewById(R.id.role);



        final DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
                menudate.setText(new StringBuilder().append(day).append("-")
                        .append(view.getMonth()).append("-").append(year)
                        .append(" "));
            }
        };
        menudate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(UploadMenu.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        user_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean status =true;
                try {
                    if(menuname.getText().toString().length()==0){
                        menuname.setText("Enter Valid Menu Name");
                        status =false;
                    }
                    if(menudate.getText().toString().length()==10){
                        menudate.setError("Enter Valid Menu Date");
                        status =false;
                    }

                    if(menudesc.getText().toString().length()==0){
                        menudesc.setError("Enter Valid Description");
                        menudesc.requestFocus();
                        status =false;
                    }
                    if(otherdetails.getText().toString().length()==0){
                        otherdetails.setError("Enter Other Details");
                        otherdetails.requestFocus();
                        status =false;
                    }

                    if(status) {

                        SharedPreferences shr= PreferenceManager.getDefaultSharedPreferences(UploadMenu.this);
                        String uid=shr.getString("uid","NA");

                        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
                        db1.execSQL("create table  if not exists messmenu(MenuTitle varchar(900),Menudate varchar(900),MenuDesc varchar(900),OtherDetails varchar(900),Mtype varchar(900),Messid varchar(900))");
                        ContentValues cv1 = new ContentValues();
                        cv1.put("MenuTitle",menuname.getText().toString());
                        cv1.put("Menudate", menudate.getText().toString());
                        cv1.put("MenuDesc",menudesc.getText().toString());
                        cv1.put("OtherDetails", otherdetails.getText().toString());
                        cv1.put("Mtype", sp1.getSelectedItem().toString());
                        cv1.put("Messid", uid);
                        db1.insert("messmenu", null, cv1);
                        Toast.makeText(UploadMenu.this, "Menu Added Sucessfully", Toast.LENGTH_LONG).show();
                    finish();
                    }
                }catch(Exception e){
                    Toast.makeText(UploadMenu.this,""+e,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void updateLabel(){
        String myFormat="dd/MM/yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);

        //menudate.setText(dateFormat.format(myCalendar.getTime()));
    };

}
