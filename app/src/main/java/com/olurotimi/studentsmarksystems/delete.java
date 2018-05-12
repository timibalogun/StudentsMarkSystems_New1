package com.olurotimi.studentsmarksystems;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class delete extends AppCompatActivity{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);

        TextView txtView = (TextView)findViewById(R.id.deletes);
        int del_id = getIntent().getIntExtra("student_id", 1);

        txtView.setText(R.string.deleted);
    }
}
