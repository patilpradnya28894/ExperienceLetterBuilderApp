package com.example.experienceletterbuilder.Activity;

//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.experienceletterbuilder.R;
//import com.itextpdf.text.Font;

import java.io.File;
import java.io.FileOutputStream;

public class WorkExperienceActivity extends AppCompatActivity {

    EditText editDesignation,editDepartment,editJoinDate;
    Button browseSign,browseStamp,finishBtn;
    ImageView imageView,imageView2;

    private  static final int IMAGE_PICK_CODE=1000;
    private  static final int PERMISSION_CODE=1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_experience);

        ActivityCompat.requestPermissions(WorkExperienceActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        editDesignation=findViewById(R.id.editDesignation);
        editDepartment=findViewById(R.id.editDepartment);
        editJoinDate=findViewById(R.id.editJoinDate);

        browseSign=findViewById(R.id.browseSign);
        browseStamp=findViewById(R.id.browseStamp);
        finishBtn=findViewById(R.id.finishBtn);

        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);

    }

    public void onBrowseSign(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_CODE);
            }else {
                    pickImageFromGallery();
            }
        }else  {
            //System os less than marshmallow
            pickImageFromGallery();
        }
    }

    private void pickImageFromGallery() {

        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }else {
                    Toast.makeText(this,"Permission Denied...!",Toast.LENGTH_LONG).show();
                }

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==IMAGE_PICK_CODE);
        imageView.setImageURI(data.getData());

    }





    public void onBrowseStamp(View view) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){

                String[] permissions={Manifest.permission.READ_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_CODE);
            }else {
                pickImageFromGallery();
            }
        }else  {
            //System os less than marshmallow
            pickImageFromGallery();
        }
    }

    public void createPDF(View view) {
        PdfDocument pdfDocument=new PdfDocument();
        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(400,500,1).create();
        PdfDocument.Page page=pdfDocument.startPage(pageInfo);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("CompanyDetails", Context.MODE_PRIVATE);
        String cName=sp.getString("cName","");
        String cSlogan=sp.getString("cSlogan","");
        String cWebsite=sp.getString("cWebsite","");
        String cEmail=sp.getString("cEmail","");
        String cContact=sp.getString("cContact","");

        SharedPreferences sp2=getApplicationContext().getSharedPreferences("EmployeeDetails", Context.MODE_PRIVATE);
        String eName=sp2.getString("eName","");
        String eAddress=sp2.getString("eAddress","");
        String eContact=sp2.getString("eContact","");
        String eEmail=sp2.getString("eEmail","");

        SharedPreferences pref=getApplicationContext().getSharedPreferences("ImageLogo",Context.MODE_PRIVATE);
        String encodedImage=pref.getString("image_data","");
        if( !encodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imageView.setImageBitmap(bitmap);
        }


        String e_desination=editDesignation.getText().toString();
        String e_department=editDepartment.getText().toString();
        String e_JoinDate=editJoinDate.getText().toString();

        int x=10,y=25;



        Paint companytitlePaint=new Paint();
        page.getCanvas().drawText("Employee Experience Letter",100,30,companytitlePaint);
        companytitlePaint.setTextAlign(Paint.Align.CENTER);
        companytitlePaint.setTextSize(500.0f);
        companytitlePaint.setColor(255);



        Paint titlePaint=new Paint();
        page.getCanvas().drawText("We Make Things You Will Love",100,50,titlePaint);
        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(500.0f);


       // titlePaint.

        Paint companyNamePaint=new Paint();
        page.getCanvas().drawText("Company Name: "+cName,100,70,companyNamePaint);
        companyNamePaint.setTextAlign(Paint.Align.CENTER);
        companyNamePaint.setTextSize(200.0f);

        Paint employeeNamePaint=new Paint();
        page.getCanvas().drawText("Employee Name: "+eName,100,90,employeeNamePaint);
        employeeNamePaint.setTextAlign(Paint.Align.CENTER);
        employeeNamePaint.setTextSize(200.0f);

        Paint employeeAddressPaint=new Paint();
        page.getCanvas().drawText("Employee Addresss: "+eAddress,100,110,employeeAddressPaint);
        employeeAddressPaint.setTextAlign(Paint.Align.CENTER);
        employeeAddressPaint.setTextSize(200.0f);

        Paint employeeContactPaint=new Paint();
        page.getCanvas().drawText("Employee Contact: "+eContact,100,130,employeeContactPaint);
        employeeContactPaint.setTextAlign(Paint.Align.CENTER);
        employeeContactPaint.setTextSize(200.0f);

        Paint employeeEmailPaint=new Paint();
        page.getCanvas().drawText("Employee Email: "+eEmail,100,150,employeeEmailPaint);
        employeeEmailPaint.setTextAlign(Paint.Align.CENTER);
        employeeEmailPaint.setTextSize(200.0f);

        Paint empDesignationPaint=new Paint();
        page.getCanvas().drawText("Employee Designation: "+e_desination,100,170,empDesignationPaint);
        empDesignationPaint.setTextAlign(Paint.Align.CENTER);
        empDesignationPaint.setTextSize(200f);


        Paint empDepartmentPaint=new Paint();
        page.getCanvas().drawText("Employee Department: "+e_department,100,190,empDepartmentPaint);
        empDepartmentPaint.setTextAlign(Paint.Align.CENTER);
        empDepartmentPaint.setTextSize(200f);

        Paint empJoinDatePaint=new Paint();
        page.getCanvas().drawText("Join Date: "+e_JoinDate,100,210,empJoinDatePaint);
        empJoinDatePaint.setTextAlign(Paint.Align.CENTER);
        empJoinDatePaint.setTextSize(200f);




        Paint sloganPaint=new Paint();
        page.getCanvas().drawText("Slogan: "+cSlogan,100,230,sloganPaint);
        sloganPaint.setTextAlign(Paint.Align.CENTER);
        sloganPaint.setTextSize(200f);

        Paint websitePaint=new Paint();
        page.getCanvas().drawText("Company WebSite: "+cWebsite,100,250,websitePaint);
        websitePaint.setTextAlign(Paint.Align.CENTER);
        websitePaint.setTextSize(200f);

        Paint emailPaint=new Paint();
        page.getCanvas().drawText("Company Email: "+cEmail,100,270,emailPaint);
        emailPaint.setTextAlign(Paint.Align.CENTER);
        emailPaint.setTextSize(200f);

        Paint contactPaint=new Paint();
        page.getCanvas().drawText("Company Contact No: "+cContact,100,290,contactPaint);
        contactPaint.setTextAlign(Paint.Align.CENTER);
        contactPaint.setTextSize(200f);







        pdfDocument.finishPage(page);

        String filePath= Environment.getExternalStorageDirectory().getPath()+"/ExperianceLetter.pdf";
        File myFile=new File(filePath);
        try {
               pdfDocument.writeTo(new FileOutputStream(myFile));
           }
           catch (Exception e){
               e.printStackTrace();
               editDesignation.setText("error");
           }

           pdfDocument.close();





    }
}
