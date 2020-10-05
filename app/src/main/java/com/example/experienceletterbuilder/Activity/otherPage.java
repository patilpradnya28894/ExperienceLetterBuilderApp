package com.example.experienceletterbuilder.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.experienceletterbuilder.R;

import androidx.appcompat.app.AppCompatActivity;

public class otherPage extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        t4=findViewById(R.id.t4);
        t5=findViewById(R.id.t5);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("CompanyDetails", Context.MODE_PRIVATE);
        String cName=sp.getString("cName","");
        String cSlogan=sp.getString("cSlogan","");
        String cWebsite=sp.getString("cWebsite","");
        String cEmail=sp.getString("cEmail","");
        String cContact=sp.getString("cContact","");

        t1.setText(cName);
        t2.setText(cSlogan);
        t3.setText(cWebsite);
        t4.setText(cEmail);
        t5.setText(cContact);


    }
}
