package com.example.shipviever;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static String tableName = "ships";
    private static String nameDB = "ship.db";
    private static String name_col = "NAME";
    private static String nationality_col  = "NATIONALITY";
    private static String pic_path  = "PATH";
    private static String description  = "DESCRIPTION";
    private static String id_col = "ID";

    public DBHelper(Context context) {
        super(context, nameDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + tableName + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, NATIONALITY TEXT, DESCRIPTION TEXT, PATH TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addShip (String name, String nationality, String desc, String path){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(name_col,name);
        values.put(nationality_col, nationality);
        values.put(pic_path, path);
        values.put(description, desc);
        db.insertOrThrow(tableName,null,values);
    }

    public Cursor getAll(){
        String [] col = {id_col,name_col,nationality_col,description,pic_path};
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tableName,col,null,null,null,null,null);
        return cursor;
    }

}