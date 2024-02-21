package com.example.messfinder;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class MessDashboard extends Activity {
    Button cuser,viewmenu,viewcust,vieworders,logout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messdashboard);

        cuser=(Button)findViewById(R.id.uploadmenu);
        cuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MessDashboard.this,UploadMenu.class);
                startActivity(i1);
            }
        });


        viewmenu=(Button)findViewById(R.id.viewmenu);
        viewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1= new Intent(MessDashboard.this,ViewMenu.class);
                startActivity(i1);
            }
        });

        vieworders=(Button)findViewById(R.id.vieworders);
        vieworders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MessDashboard.this,ViewMessOrders.class);
                startActivity(i1);
            }
        });

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MessDashboard.this,Login.class);
                startActivity(i1);
            }
        });

    }
}
