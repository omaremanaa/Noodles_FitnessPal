package com.example.noodles_fitnesspal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Contact Us");
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        TextView mailfeedback = (TextView) findViewById(R.id.gmailHyperLink);
        mailfeedback.setText(Html.fromHtml("<a href=\"mailto:omarmanaabon@gmail.com\">fitnesspal@info.com</a>"));
        mailfeedback.setMovementMethod(LinkMovementMethod.getInstance());

        TextView instaAccount = (TextView) findViewById(R.id.instaHyperLink);
        instaAccount.setMovementMethod(LinkMovementMethod.getInstance());

        TextView facebookPage = (TextView) findViewById(R.id.faceHyperLink);
        facebookPage.setMovementMethod(LinkMovementMethod.getInstance());


    }

}
