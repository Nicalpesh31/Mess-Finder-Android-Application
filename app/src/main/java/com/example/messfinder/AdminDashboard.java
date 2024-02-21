package com.example.messfinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboard extends Activity {
    Button cuser,viewmess,viewcust,logout,addmess,vieworders;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admindashboard);

        addmess=(Button)findViewById(R.id.addmess   );
        addmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(AdminDashboard.this,AddMess.class);
                startActivity(i1);
            }
        });


        vieworders=(Button)findViewById(R.id.vieworders);
        vieworders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(AdminDashboard.this,ViewAllOrders.class);
                startActivity(i1);
            }
        });

        viewmess=(Button)findViewById(R.id.viewmess);
        viewmess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(AdminDashboard.this,ViewMess.class);
                startActivity(i1);
            }
        });

        viewcust=(Button)findViewById(R.id.viewcust);
        viewcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(AdminDashboard.this,ViewCustomer.class);
                startActivity(i1);
            }
        });

        logout=(Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1= new Intent(AdminDashboard.this,Login.class);
                startActivity(i1);
            }
        });
    }
}
