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

public class ViewMess extends Activity {
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



            dest= new String[20];
            hname = new String[20];
            contact = new String[20];
            address = new String[20];
            ucon=new String[20];


            Cursor c1 = db1.rawQuery("select * from Mess", null);

            int count = 0;

            while (c1.moveToNext()) {

                //if(c1.getString(5).contentEquals("1")) {
                    // String str2 = c1.getString(0);
                    hname[count] = "Name : " + c1.getString(0);
                    contact[count] = "Contact   : " + c1.getString(1);
                    address[count] = "Email  : " + c1.getString(2) + "\nAddress : " + c1.getString(4) ;
                    ucon[count]=c1.getString(0);
                    dest[count]=c1.getString(1);

                    // Toast.makeText(ViewComplaint.this,""+hname[count],Toast.LENGTH_LONG).show();
                    count = count + 1;
             //   }
            }


            LevelAdapter l1 = new LevelAdapter(ViewMess.this, hname, contact, address);
            lst1.setAdapter(l1);
        }catch(Exception e){
            Toast.makeText(ViewMess.this,""+e,Toast.LENGTH_LONG).show();
        }

    }
}
