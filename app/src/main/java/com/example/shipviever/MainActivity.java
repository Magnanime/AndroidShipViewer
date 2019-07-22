package com.example.shipviever;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b_exit;
    Button about;
    Button search_library;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_exit = findViewById(R.id.exit_button);
        b_exit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        search_library = findViewById(R.id.library);
        search_library.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SearchScreen.class);
                startActivityForResult(myIntent, 0);
            }
        });

        about = findViewById(R.id.about_button);
        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), About.class);
                startActivityForResult(myIntent, 0);
            }

        });
    }

}
