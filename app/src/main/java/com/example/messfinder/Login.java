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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    Button main_login;
    EditText main_user_name, main_passward;
    TextView tv1,tv2;
    SQLiteDatabase db1;
    String name="",contact="";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        tv1=(TextView)findViewById(R.id.signup);
        tv2=(TextView)findViewById(R.id.messlogin);

        main_user_name=(EditText)findViewById(R.id.main_user_name);
        main_passward=(EditText)findViewById(R.id.main_password);
        main_login=(Button)findViewById(R.id.main_login);

        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Login.this,NewRegistration.class);
                startActivity(i1);

            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(Login.this,MessLogin.class);
                startActivity(i1);

            }
        });

        main_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               String role="";
                String uid="";
                boolean status=true;
                String uname=main_user_name.getText().toString();
                String pass= main_passward.getText().toString();

                if(uname.length()<=0){
                    main_user_name.setError("Enter Valid Username");
                    main_passward.requestFocus();
                    status=false;
                }
                if(pass.length()<=0){
                    main_passward.setError("Enter Valid Username");
                    main_passward.requestFocus();
                    status=false;
                }

                if(status){


                    if(uname.contentEquals("admin")&&pass.contentEquals("admin")){

                   Intent i1= new Intent(Login.this,AdminDashboard.class);
                   startActivity(i1);
                    }else{

                        db1 = openOrCreateDatabase("MyDb", Context.MODE_PRIVATE, null);
                        Cursor c1=db1.rawQuery("select * from userdata",null);
                        boolean isfound=false;
                        while(c1.moveToNext()){


                            if(uname.contentEquals(c1.getString(2)) && pass.contentEquals(c1.getString(3))){


                                isfound=true;
                                uid=c1.getString(0);
                                name=c1.getString(0);
                                contact=c1.getString(1);
                                role=c1.getString(4);
                            }

                           // Toast.makeText(Login.this,""+c1.getString(2),Toast.LENGTH_LONG).show();
                        }
                        if(isfound){
                            Toast.makeText(Login.this,"Role"+role,Toast.LENGTH_LONG).show();
                            SharedPreferences shr= PreferenceManager.getDefaultSharedPreferences(Login.this);
                            SharedPreferences.Editor edit=shr.edit();
                            edit.putString("name",name);
                            edit.putString("uid",uid);
                            edit.putString("contact",contact);
                            edit.commit();



                          //  if(role.contentEquals("Mess Owner")){
                                Intent i1= new Intent(Login.this,UserDashboard.class);
                                startActivity(i1);

                           // }
                                /*
                            if(role.contentEquals("Company")){
                                Toast.makeText(Login.this,"Here",Toast.LENGTH_LONG).show();
                                Intent i1= new Intent(Login.this,Company.class);
                                startActivity(i1);
                            }
                            if(role.contentEquals("Warehouse")){
                                Toast.makeText(Login.this,"Here",Toast.LENGTH_LONG).show();
                                Intent i1= new Intent(Login.this,WareHouseDashboard.class);
                                startActivity(i1);

                            }
                            */


                        }

                    }

                }



            }
        });
    }




}
