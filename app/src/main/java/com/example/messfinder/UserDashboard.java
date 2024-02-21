package com.example.messfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserDashboard extends Activity {
    Button messnear,searchbyname,viewallmess,orders,logout;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdashboard);

        messnear=(Button)findViewById(R.id.searchmess);
        messnear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,ViewNearByMess.class);
                startActivity(i1);
            }
        });

        searchbyname=(Button)findViewById(R.id.searchbyname);
        searchbyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,MessByName.class);
                startActivity(i1);
            }
        });

        viewallmess=(Button)findViewById(R.id.viewallmess);
        viewallmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,ViewAllMesss.class);
                startActivity(i1);
            }
        });

        orders=(Button)findViewById(R.id.myorders);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,MyOrders.class);
                startActivity(i1);
            }
        });



/*
        viewmenu=(Button)findViewById(R.id.viewmenu);
        viewmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,ViewMenu.class);
                startActivity(i1);
            }
        });

        viewcust=(Button)findViewById(R.id.viewcust);
        viewcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(MessDashboard.this,ViewCustomer.class);
                startActivity(i1);
            }
        });
*/
        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(UserDashboard.this,Login.class);
                startActivity(i1);
            }
        });

    }
}
