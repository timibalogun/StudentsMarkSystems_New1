package com.olurotimi.studentsmarksystems;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class about extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        TextView txtView = (TextView)findViewById(R.id.abouts);
        txtView.setText(R.string.about_developer);
    }
}
