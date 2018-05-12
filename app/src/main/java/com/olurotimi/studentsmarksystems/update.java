package com.olurotimi.studentsmarksystems;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class update extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        TextView txtView = (TextView)findViewById(R.id.updates);
        txtView.setText(R.string.updated);
    }
}
