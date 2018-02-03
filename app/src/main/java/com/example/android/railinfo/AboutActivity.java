package com.example.android.railinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by sathirishabh on 09-08-2017.
 */

public class AboutActivity extends AppCompatActivity {

    private static final String MY_FACEBOOK_ACCOUNT="https://www.facebook.com/sathi.rishabh";
    private static final String INSTA_ACCOUNT="https://www.instagram.com/sathirishabh";
    private static final String TWITTER_ACCOUNT="https://twitter.com/sathirishabh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        ImageView im1=(ImageView)findViewById(R.id.infoimg1);
        ImageView im2=(ImageView)findViewById(R.id.infoimg2);
        ImageView im3=(ImageView)findViewById(R.id.infoimg3);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myfb=Uri.parse(MY_FACEBOOK_ACCOUNT);
                Intent intent=new Intent(Intent.ACTION_VIEW,myfb);
                startActivity(intent);
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri myinsta=Uri.parse(INSTA_ACCOUNT);
                Intent intent2=new Intent(Intent.ACTION_VIEW,myinsta);
                startActivity(intent2);
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri mytwitter=Uri.parse(TWITTER_ACCOUNT);
                Intent intent3=new Intent(Intent.ACTION_VIEW,mytwitter);
                startActivity(intent3);
            }
        });

    }
}
