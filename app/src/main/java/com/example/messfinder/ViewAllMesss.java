package com.example.messfinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ViewAllMesss extends Activity {
    SQLiteDatabase db1;
    String hname[],contact[],address[],ucon[],dest[],lat[],lang[],foodtype[],messname[];
    ListView lst1;
    EditText et1;
    Button b1;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allmess);
        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
        b1=(Button)findViewById(R.id.search);
       // et1=(EditText)findViewById(R.id.loc);
        try {
            lst1 = (ListView) findViewById(R.id.lst11);
            lst1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    try {


                        Intent i1= new Intent(ViewAllMesss.this,MessDetails.class);
                        i1.putExtra("name",hname[position]);
                        i1.putExtra("cnum",contact[position]);
                        i1.putExtra("add",ucon[position]);
                        i1.putExtra("lat",lat[position]);
                        i1.putExtra("lang",lang[position]);
                        i1.putExtra("ftype",foodtype[position]);
                        i1.putExtra("messname",messname[position]);
                        startActivity(i1);

                    }catch(Exception e){
                        Toast.makeText(ViewAllMesss.this,""+e,Toast.LENGTH_LONG).show();
                    }

                }
            });



                    //String str11=et1.getText().toString();

                    dest= new String[20];
                    hname = new String[20];
                    contact = new String[20];
                    address = new String[20];
                    lat = new String[20];
                    lang = new String[20];
                    ucon=new String[20];
                    foodtype=new String[20];
                    messname=new String[20];

                    Cursor c1 = db1.rawQuery("select * from Mess", null);

                    int count = 0;

                    while (c1.moveToNext()) {

                     //  if(c1.getString(0).toLowerCase().trim().contentEquals(str11.toLowerCase().trim())) {
                        // String str2 = c1.getString(0);
                        hname[count] = "Name : " + c1.getString(0);
                        contact[count] = "Contact   : " + c1.getString(1);
                        address[count] = "Email  : " + c1.getString(2) + "\nAddress : " + c1.getString(4) ;
                        ucon[count]="Address : " + c1.getString(4);
                        dest[count]=c1.getString(1);
                        lat[count]=c1.getString(5);
                        lang[count]=c1.getString(6);
                        foodtype[count]="Food Type :"+c1.getString(7);
                        messname[count] = "" + c1.getString(0);

                        // Toast.makeText(ViewComplaint.this,""+hname[count],Toast.LENGTH_LONG).show();
                        count = count + 1;
                           }
                 //   }


                    LevelAdapter l1 = new LevelAdapter(ViewAllMesss.this, hname, contact, address);
                    lst1.setAdapter(l1);



        }catch(Exception e){
            Toast.makeText(ViewAllMesss.this,""+e,Toast.LENGTH_LONG).show();
        }

    }
}
