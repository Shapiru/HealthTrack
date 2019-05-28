package com.example.healthtrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView txt;
    ImageView imageView;
    EditText usern,emailn,passwordn;
    Button login;
    String username,password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txt = (TextView) findViewById(R.id.create);
        usern = (EditText) findViewById(R.id.user);
        emailn = (EditText) findViewById(R.id.email);
        passwordn = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);

       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent data = new Intent(Main2Activity.this, dataActivity.class);
                startActivity(data);
            }});}}
