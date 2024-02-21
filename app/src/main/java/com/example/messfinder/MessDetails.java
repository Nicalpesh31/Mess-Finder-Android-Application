package com.example.messfinder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MessDetails extends Activity {

TextView messname,messtype,address,contact;
Button b1,b2,b3,b4;
String contact1="",lat="",lang="",messname1="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messdetails);
        messname=(TextView)findViewById(R.id.messname);
        messtype=(TextView)findViewById(R.id.messtype);
        address=(TextView)findViewById(R.id.address);
        contact=(TextView)findViewById(R.id.contact);



        Intent i1 = getIntent();
        messname.setText(i1.getStringExtra("name"));
        messtype.setText(i1.getStringExtra("ftype"));
        address.setText(i1.getStringExtra("add"));
        contact.setText(i1.getStringExtra("cnum"));
        contact1=i1.getStringExtra("cnum").toString();

        messname1=i1.getStringExtra("messname");
        SharedPreferences shr= PreferenceManager.getDefaultSharedPreferences(MessDetails.this);
        SharedPreferences.Editor edit= shr.edit();
        edit.putString("uid", messname1);
        edit.commit();

        b1=(Button)findViewById(R.id.viewmenu);
        b2=(Button)findViewById(R.id.call);
        b3=(Button)findViewById(R.id.map);
        b4=(Button)findViewById(R.id.putorder);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


        Intent i1= new Intent(MessDetails.this,ViewMenu.class);

        startActivity(i1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(Intent.ACTION_CALL);
                i1.setData(Uri.parse("tel:"+contact1));
                startActivity(i1);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String locationName="Mess Here";
                String geoUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lang + " (" + locationName + ")";
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1= new Intent(MessDetails.this,PutOrder.class);
                i1.putExtra("messname",messname1);
                startActivity(i1);

            }
        });
    }
}
