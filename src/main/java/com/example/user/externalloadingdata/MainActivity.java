package com.example.user.externalloadingdata;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText readData;
    Button addBtn,deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.writeText);
        readData=findViewById(R.id.editAdd);
        addBtn=findViewById(R.id.addBtn);
        deleteBtn=findViewById(R.id.delete);
      //  textView.setText("hii");


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aData="";
                String aBuffer="";
                String readFileData=readData.getText().toString();
                File myFile=new File(Environment.getExternalStorageDirectory()+"/"+"MyFile.txt");
                try {
                    FileOutputStream fileOutputStream=new FileOutputStream(myFile);
                    OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                    outputStreamWriter.append(readFileData);
                    textView.setText(readFileData);
                    outputStreamWriter.close();
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(),"Saved",Toast.LENGTH_SHORT).show();
                    FileInputStream fileInputStream=new FileInputStream(myFile);
                    BufferedReader myReader=new BufferedReader(new InputStreamReader(fileInputStream));
                    while ((aData=myReader.readLine())!=null)
                    {
                        aBuffer+=aData;

                    }
                    textView.setText(aBuffer);
                    myReader.close();
                    fileInputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });



    }
}
