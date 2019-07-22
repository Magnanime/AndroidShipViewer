package com.example.shipviever;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends Activity {
    Button bck_btn;
    String searched;
    String name;
    String path;
    String nation;
    String desc;
    ImageView img;
    TextView nametxt;
    TextView nametxt2;
    TextView descf;
    int count;
    DBHelper MyDataBase = new DBHelper(this);
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Bundle bundle = getIntent().getExtras();
        searched = bundle.getString("searched");

        Cursor wsk = MyDataBase.getAll();
        while (wsk.moveToNext() && !searched.equals(name)){
            count = wsk.getInt(0);
            name = wsk.getString(1);
            nation = wsk.getString(2);
            desc = wsk.getString(3);
            path = wsk.getString(4);
        }
        wsk.moveToFirst();

        nametxt = findViewById(R.id.textView2);
        nametxt.setText("Name: " + name);

        nametxt2 = findViewById(R.id.textView4);
        nametxt2.setText("Nationality: " + nation);

        int imageResource = getResources().getIdentifier(path, null, getPackageName());
        Drawable res = getDrawable(imageResource);

        img = findViewById(R.id.imageView);
        img.setImageDrawable(res);

        descf = findViewById(R.id.textView5);
        descf.setText(desc);

        bck_btn = findViewById(R.id.back_button);
        bck_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              Intent intent = new Intent();
              setResult(RESULT_OK, intent);
               finish();
            }
       });

    }
}
