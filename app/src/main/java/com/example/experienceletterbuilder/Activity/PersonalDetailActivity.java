package com.example.experienceletterbuilder.Activity;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.experienceletterbuilder.R;

public class PersonalDetailActivity extends AppCompatActivity {

    EditText editEmployeeName,editEmployeeAddress,editEmployeeContactNo,editEmployeeEmail;
    Button nextBtn2,saveBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);

        editEmployeeName=findViewById(R.id.editEmployeeName);
        editEmployeeAddress=findViewById(R.id.editEmployeeAddress);
        editEmployeeContactNo=findViewById(R.id.editEmployeeContactNo);
        editEmployeeEmail=findViewById(R.id.editEmployeeEmail);

        nextBtn2=findViewById(R.id.nextBtn2);
        saveBtn2=findViewById(R.id.saveBtn2);


    }

    public void onNext2(View view) {
        Intent intent=new Intent(this,WorkExperienceActivity.class);
        startActivity(intent);
    }

    public void onSave2(View view) {
        String eName=editEmployeeName.getText().toString();
        String eAddress=editEmployeeAddress.getText().toString();
        String eContact=editEmployeeContactNo.getText().toString();
        String eEmail=editEmployeeEmail.getText().toString();

        SharedPreferences sharedPreferences1 = getSharedPreferences("EmployeeDetails", this.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = sharedPreferences1.edit();
        editor1.putString("eName",eName);
        editor1.putString("eAddress",eAddress);
        editor1.putString("eContact",eContact);
        editor1.putString("eEmail",eEmail);
        editor1.commit();
        Intent intent1=new Intent(PersonalDetailActivity.this,otherPage2.class);
        startActivity(intent1);
    }
}
