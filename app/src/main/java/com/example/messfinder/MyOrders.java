package com.example.messfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyOrders extends Activity {
    SQLiteDatabase db1;
    String hname[],contact[],address[],ucon[],dest[],lat[],lang[],foodtype[];
    ListView lst1;
    EditText et1;
    Button b1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myorders);
        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
        b1=(Button)findViewById(R.id.search);
        et1=(EditText)findViewById(R.id.loc);
        try {
                    lst1 = (ListView) findViewById(R.id.lst11);
                   // String str11=et1.getText().toString();

                    dest= new String[20];
                    hname = new String[20];
                    contact = new String[20];
                    address = new String[20];
                    lat = new String[20];
                    lang = new String[20];
                    ucon=new String[20];
                    foodtype=new String[20];
                    SharedPreferences shr= PreferenceManager.getDefaultSharedPreferences(MyOrders.this);
                    String name=shr.getString("name","NA");

                    Cursor c1 = db1.rawQuery("select * from Orders", null);

                    int count = 0;

                    while (c1.moveToNext()) {

                       if(c1.getString(0).toLowerCase().trim().contentEquals(name.toLowerCase().trim())) {
                        // String str2 = c1.getString(0);
                        hname[count] = "Name : " + c1.getString(0);
                        address[count] = "Address  : " + c1.getString(1);
                        ucon[count]="No of Days : " + c1.getString(2);
                       // dest[count]=c1.getString(1);
                      //  lat[count]=c1.getString(5);
                       // lang[count]=c1.getString(6);
                       // foodtype[count]="Food Type :"+c1.getString(7);

                        // Toast.makeText(ViewComplaint.this,""+hname[count],Toast.LENGTH_LONG).show();
                        count = count + 1;
                           }
                    }


                    LevelAdapter l1 = new LevelAdapter(MyOrders.this, hname, ucon, address);
                    lst1.setAdapter(l1);




        }catch(Exception e){
            Toast.makeText(MyOrders.this,""+e,Toast.LENGTH_LONG).show();
        }

    }
}
