package com.example.experienceletterbuilder.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.experienceletterbuilder.R;

import androidx.appcompat.app.AppCompatActivity;

public class otherPage2 extends AppCompatActivity {
        TextView text1, text2, text3, text4;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_other2);

            text1=findViewById(R.id.text1);
            text2=findViewById(R.id.text2);
            text3=findViewById(R.id.text3);
            text4=findViewById(R.id.text4);

            SharedPreferences sp2=getApplicationContext().getSharedPreferences("EmployeeDetails", Context.MODE_PRIVATE);
            String eName=sp2.getString("eName","");
            String eAddress=sp2.getString("eAddress","");
            String eContact=sp2.getString("eContact","");
            String eEmail=sp2.getString("eEmail","");

            text1.setText(eName);
            text2.setText(eAddress);
            text3.setText(eContact);
            text4.setText(eEmail);
        }
    }
