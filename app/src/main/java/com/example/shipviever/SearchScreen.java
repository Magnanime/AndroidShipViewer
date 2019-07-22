package com.example.shipviever;

import android.app.Activity;
import android.content.Intent;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class SearchScreen extends Activity {

    String desc1 = "USS Constitution, also known as Old Ironsides, is a wooden-hulled, three-masted heavy frigate of the United States Navy named by President George Washington after the United States Constitution. She is the world's oldest commissioned naval vessel still afloat.";
    String desc2 = "HMS Victory is a 104-gun first-rate ship of the line of the Royal Navy, ordered in 1758, laid down in 1759 and launched in 1765. She is best known for her role as Lord Nelson's flagship at the Battle of Trafalgar on 21 October 1805.";
    String desc3 = "USS Enterprise (CV-6) was the seventh U.S. Navy vessel to bear the name. Colloquially called The Big E, she was the sixth aircraft carrier of the United States Navy. A Yorktown-class carrier, she was launched in 1936 and was one of only three American carriers commissioned before World War II to survive the war (the others being Saratoga and Ranger). She participated in more major actions of the war against Japan than any other United States ship. These actions included the Attack on Pearl Harbor (18 dive bombers of VS-6 were over the harbor; 6 were shot down with a loss of 11 men—she was the only American aircraft carrier with men at Pearl Harbor during the attack and the first to sustain casualties during the Pacific War), the Battle of Midway, the Battle of the Eastern Solomons, the Battle of the Santa Cruz Islands, various other air-sea engagements during the Guadalcanal Campaign, the Battle of the Philippine Sea, and the Battle of Leyte Gulf.";

    Button bck_btn;
    Button src_btn;
    Button fav1;
    Button fav2;
    int lno_1;
    int lno_2;
    String no_1;
    String no_2;
    String path_1;
    String path_2;
    TextView tno_1;
    TextView tno_2;
    ImageView im_1;
    ImageView im_2;
    String searched;
    EditText search_bar;
    DBHelper MyDataBase = new DBHelper(this);

    public String getSearched(){
        return searched;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        MyDataBase.addShip("USS Constitution", "USA", desc1, "@drawable/constitution");
        MyDataBase.addShip("HMS Victory", "UK", desc2, "@drawable/victory");
        MyDataBase.addShip("USS Enterprise", "USA", desc3, "@drawable/enterprise");

        //Function to set favourite ships

        Cursor wsk = MyDataBase.getAll();
        while (wsk.moveToNext() && lno_1 != 1){
            lno_1 = wsk.getInt(0);
            no_1 = wsk.getString(1);
            path_1 = wsk.getString(4);
        }
        wsk.moveToFirst();
        while (wsk.moveToNext() && lno_2 != 2){
            lno_2 = wsk.getInt(0);
            no_2 = wsk.getString(1);
            path_2 = wsk.getString(4);
        }
        wsk.moveToFirst();


        Bitmap bmp = BitmapFactory.decodeFile(path_1);

        int imageResource = getResources().getIdentifier(path_1, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);

        int imageResource1 = getResources().getIdentifier(path_2, null, getPackageName());
        Drawable res1 = getDrawable(imageResource1);

        tno_1 = findViewById(R.id.ship_name_1);
        tno_1.setText(no_1);

        tno_2 = findViewById(R.id.ship_name_2);
        tno_2.setText(no_2);

        im_1 = findViewById(R.id.viev_1);
        im_1.setImageDrawable(res);

        im_2 = findViewById(R.id.viev_2);
        im_2.setImageDrawable(res1);


        fav1 = findViewById(R.id.fav_1);
        fav1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searched = search_bar.getText().toString();
                Intent myIntent = new Intent(view.getContext(), Info.class);
                myIntent.putExtra("searched", no_1);
                startActivityForResult(myIntent, 0);
            }
        });

        fav2 = findViewById(R.id.fav_2);
        fav2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searched = search_bar.getText().toString();
                Intent myIntent = new Intent(view.getContext(), Info.class);
                myIntent.putExtra("searched", no_2);
                startActivityForResult(myIntent, 0);
            }
        });

        search_bar = findViewById(R.id.editText2);

        src_btn = findViewById(R.id.search_button);
        src_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searched = search_bar.getText().toString();
                Intent myIntent = new Intent(view.getContext(), Info.class);
                myIntent.putExtra("searched", searched);
                startActivityForResult(myIntent, 0);
            }
        });

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