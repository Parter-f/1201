package com.example.a1201;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {

    private static final String DB_Name = "tanulok.db";
    private static final int DB_VERSION = 1;

    private static final String Table_Name = "tanulok";
    private static final String Col_ID = "id";
    private static final String Col_VEZNEV = "vezeteknev";
    private static final String Col_KERNEV = "keresztnev";
    private static final String Col_JEGY = "jegy";

    public Dbhelper(Context context){
        super(context, DB_Name , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + Table_Name + "(" + Col_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Col_VEZNEV + " TEXT NOT NULL, "
                + Col_KERNEV + " TEXT NOT NULL, "
                + Col_JEGY + " INTEGER NOT NULL"
                + ");";

        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(sqLiteDatabase);

    }

    public boolean rogzites(String vezeteknev , String keresztnev , int jegy){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_VEZNEV , vezeteknev);
        values.put(Col_KERNEV , keresztnev);
        values.put(Col_JEGY , jegy);
        return db.insert(Table_Name, null , values) != -1;
    }

    public Cursor listaz(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(Table_Name ,
                new String[]{Col_ID , Col_VEZNEV , Col_KERNEV , Col_JEGY},
                null , null , null , null , null);
    }

}
