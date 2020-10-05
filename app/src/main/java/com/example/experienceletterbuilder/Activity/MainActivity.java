package com.example.experienceletterbuilder.Activity;

//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.experienceletterbuilder.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.BreakIterator;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText editCompanyName, editSlogan, editWebsite, editEmail, editContactNo;
    Button browseBtn, nextBtn1,save;
    ImageView logoImage;
    Bitmap bitmapImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editCompanyName = findViewById(R.id.editCompanyName);
        editSlogan = findViewById(R.id.editSlogan);
        editWebsite = findViewById(R.id.editWebsite);
        editEmail = findViewById(R.id.editEmail);
        editContactNo = findViewById(R.id.editContactNo);


        browseBtn = findViewById(R.id.browseBtn);
        nextBtn1 = findViewById(R.id.nextBtn1);
        save=findViewById(R.id.save);

        logoImage = findViewById(R.id.logoImage);

    }



    public void onNext1(View view) {
        String companyName = editCompanyName.getText().toString();
        String slogan = editSlogan.getText().toString();
        String website = editWebsite.getText().toString();
        String Email = editEmail.getText().toString();
        String contactNo = editContactNo.getText().toString();


        if (companyName.length() == 0) {
            editCompanyName.setError("Company Name is Mandatory");
        } else if (slogan.length() == 0) {
            editSlogan.setError("Slogan Name is Mandatory");
        } else if (website.length() == 0) {
            editWebsite.setError("website Name is Mandatory");
        } else if (Email.length() == 0) {
            editEmail.setError("Email Name is Mandatory");
        } else if (contactNo.length() == 0) {
            editContactNo.setError("contactNo Name is Mandatory");
        } else {


            Intent intent = new Intent(MainActivity.this, PersonalDetailActivity.class);
            startActivity(intent);

        }
    }
        public void onSave(View view){
            String companyName = editCompanyName.getText().toString();
            String slogan = editSlogan.getText().toString();
            String website = editWebsite.getText().toString();
            String Email = editEmail.getText().toString();
            String contactNo = editContactNo.getText().toString();


            SharedPreferences sharedPreferences = getSharedPreferences("CompanyDetails", this.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("cName", companyName);
            editor.putString("cSlogan", slogan);
            editor.putString("cWebsite", website);
            editor.putString("cEmail", Email);
            editor.putString("cContact", contactNo);

            editor.commit();
            Intent intent1=new Intent(MainActivity.this,otherPage.class);
            startActivity(intent1);
        }

    public void onBrowse(View view) {
        logoDownloader l1=new logoDownloader();
        try {
            bitmapImage=l1.execute("https://image.shutterstock.com/image-photo/image-260nw-601427237.jpg").get();

            logoImage.setImageBitmap(bitmapImage);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


//        java.io.ByteArrayOutputStream baos=new java.io.ByteArrayOutputStream();
//        Bitmap bm=((android.graphics.drawable.BitmapDrawable)logoImage.getDrawable()).getBitmap();
//        bm.compress(Bitmap.CompressFormat.JPEG,100,baos);
//        byte[] imageByte=baos.toByteArray();
//        String imageString=android.util.Base64.encodeToString(imageByte,android.util.Base64.DEFAULT);
//
//        textEncode.setText(imageString);

//        SharedPreferences shre = getSharedPreferences("ImageLogo",this.MODE_PRIVATE);
//        SharedPreferences.Editor edit=shre.edit();
//        edit.putString("image_data",imageString);
//        edit.commit();

    }


    public class logoDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url=new URL(strings[0]);

                HttpURLConnection connection=(HttpURLConnection) url.openConnection();

                connection.connect();

                InputStream inputStream=connection.getInputStream();
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);

                return (bitmap);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }catch (IOException e){

            }

            return null;
        }
    }
}
