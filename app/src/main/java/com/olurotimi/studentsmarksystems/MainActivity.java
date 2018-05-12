package com.olurotimi.studentsmarksystems;

import java.io.Serializable;
import java.security.PublicKey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.olurotimi.studentsmarksystems.R;

public class MainActivity extends AppCompatActivity {
    EditText student_name,student_id,student_marks;
    Button create,view,viewall,about,delete,update;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student_name=(EditText)findViewById(R.id.name);
        student_id=(EditText)findViewById(R.id.stud_id);
        student_marks=(EditText)findViewById(R.id.marks);
        create=(Button)findViewById(R.id.creatbtn);
        view=(Button)findViewById(R.id.viewbtn);
        viewall=(Button)findViewById(R.id.viewallbtn);
        delete=(Button)findViewById(R.id.deletebtn);
        about=(Button)findViewById(R.id.aboutbtn);
        update=(Button)findViewById(R.id.updatebtn);

        db=openOrCreateDatabase("Student_Marks", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(studentid INTEGER,name VARCHAR,marks INTEGER);");


        create.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(student_id.getText().toString().trim().length()==0||
                        student_name.getText().toString().trim().length()==0||
                        student_marks.getText().toString().trim().length()==0)
                {
                    displayMsg("Error", "Please input the three mandatory fields");
                    return;
                }
                db.execSQL("INSERT INTO student VALUES('"+student_id.getText()+"','"+student_name.getText()+
                        "','"+student_marks.getText()+"');");
                Intent intent = new Intent(getApplicationContext(), create.class);
                //intent.putExtra("student_id", (Serializable) student_id);
                startActivity(intent);
                cleanScreen();
            }
        });
        delete.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(student_id.getText().toString().trim().length()==0)
                {
                    displayMsg("Error", "Please enter the Student_ID");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE studentid='"+student_id.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE studentid='"+student_id.getText()+"'");
                    //displayMsg("Success", "Record Deleted");
                    Intent intent = new Intent(getApplicationContext(), delete.class);
                    //intent.putExtra("student_id", (Serializable) student_id);
                    startActivity(intent);
                }
                else
                {
                    displayMsg("Error", "Invalid Studentid");
                }
                cleanScreen();
            }
        });
        update.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(student_id.getText().toString().trim().length()==0)
                {
                    displayMsg("Error", "Please enter Studentid");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE studentid='"+student_id.getText()+"'", null);
                if(c.moveToFirst())
                {
                    db.execSQL("UPDATE student SET name='"+student_name.getText()+"',marks='"+student_marks.getText()+
                            "' WHERE studentid='"+student_id.getText()+"'");
                    //displayMsg("Success", "Record Modified");
                    Intent intent = new Intent(getApplicationContext(), update.class);
                    //intent.putExtra("student_id", (Serializable) student_id);
                    startActivity(intent);
                }
                else
                {
                    displayMsg("Error", "Invalid Studentid");
                }
                cleanScreen();
            }
        });
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(student_id.getText().toString().trim().length()==0)
                {
                    displayMsg("Error", "Please enter Studentid");
                    return;
                }
                Cursor c=db.rawQuery("SELECT * FROM student WHERE studentid='"+student_id.getText()+"'", null);
                if(c.moveToFirst())
                {
                    student_name.setText(c.getString(1));
                    student_marks.setText(c.getString(2));
                }
                else
                {
                    displayMsg("Error", "Invalid Studentid");
                    cleanScreen();
                }
            }
        });
        viewall.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor c=db.rawQuery("SELECT * FROM student", null);
                if(c.getCount()==0)
                {
                    displayMsg("Error", "No records found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("Studentid: "+c.getString(0)+"\n");
                    buffer.append("Name: "+c.getString(1)+"\n");
                    buffer.append("Marks: "+c.getString(2)+"\n\n");
                }
                displayMsg("Student Details", buffer.toString());
            }
        });
        about.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //displayMsg("Student Marks Record System", "Developed by Olurotimi Balogun");
                Intent intent = new Intent(getApplicationContext(), about.class);
                //intent.putExtra("student_id", (Serializable) student_id);
                startActivity(intent);
            }
        });

    }
    public void displayMsg(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void cleanScreen()
    {
        student_id.setText("");
        student_name.setText("");
        student_marks.setText("");
        student_id.requestFocus();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_activity_main, menu);
        return true;

    }
}
