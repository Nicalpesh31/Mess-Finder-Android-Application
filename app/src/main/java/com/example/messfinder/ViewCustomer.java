package com.example.messfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ViewCustomer extends Activity {
    SQLiteDatabase db1;
    String hname[],contact[],address[],ucon[],dest[];
    ListView lst1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmuser);
        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
        try {
            lst1 = (ListView) findViewById(R.id.lst11);

            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    try {

                        String query11 = "update userdata set status='1' where Name='" + ucon[position] + "'";
                        db1.execSQL(query11);
                        Toast.makeText(ViewCustomer.this,"User Registration Confirmed Sucessfully",Toast.LENGTH_LONG).show();
                        finish();
                        Intent i1= new Intent(ViewCustomer.this, ViewCustomer.class);
                        startActivity(i1);

                    }catch(Exception e){
                        Toast.makeText(ViewCustomer.this,""+e,Toast.LENGTH_LONG).show();
                    }

                }
            });

            dest= new String[20];
            hname = new String[20];
            contact = new String[20];
            address = new String[20];
            ucon=new String[20];


            Cursor c1 = db1.rawQuery("select * from userdata", null);

            int count = 0;

            while (c1.moveToNext()) {

              //  if(c1.getString(4).contentEquals("Mess Owner")) {
                    // String str2 = c1.getString(0);
                    hname[count] = "Name : " + c1.getString(0);
                    contact[count] = "Contact   : " + c1.getString(1);
                    address[count] = "Email  : " + c1.getString(2) + "\nRole : " + c1.getString(4) ;
                    ucon[count]=c1.getString(0);
                    dest[count]=c1.getString(1);

                    // Toast.makeText(ViewComplaint.this,""+hname[count],Toast.LENGTH_LONG).show();
                    count = count + 1;
             //   }
            }


            LevelAdapter l1 = new LevelAdapter(ViewCustomer.this, hname, contact, address);
            lst1.setAdapter(l1);
        }catch(Exception e){
            Toast.makeText(ViewCustomer.this,""+e,Toast.LENGTH_LONG).show();
        }

    }
}
