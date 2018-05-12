package com.olurotimi.studentsmarksystems;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class create extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        TextView txtView = (TextView)findViewById(R.id.creates);
        txtView.setText(R.string.created);
    }
}
