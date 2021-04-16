package com.example.databasefinaltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.annotation.Target;
import java.util.concurrent.TransferQueue;

public class DBHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "info";
    private static String TABLE_NAME = "information";
    private static String COLUMN_USERID = "userId";
    private static String COLUMN_PASSWORD = "password";

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"(id INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_USERID+" varchar,"+COLUMN_PASSWORD+" varchar);";
        db.execSQL(Query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(Query);
        onCreate(db);
    }

    public void addInfo(InfoPojo info)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID,info.getUserId());
        values.put(COLUMN_PASSWORD,info.getPassword());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public String getData()
    {
        String result="";
        String Query = "select * from "+TABLE_NAME;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);
        while (cursor.moveToNext())
        {
            int id = cursor.getInt(0);
            String userid = cursor.getString(1);
            String pass = cursor.getString(2);
            result += id+" "+ userid +" "+pass +"\n";
        }
        cursor.close();
        db.close();
        return result;
    }
    public boolean deleteData(int id)
    {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NAME,"id ="+id,null)>0;
    }

    public void updateData(int id,String userId, String pass)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERID,userId);
        values.put(COLUMN_PASSWORD,pass);
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME,values,"id ="+id,null);
        db.close();
    }
    public String findData(int ID)
    {
        String result = "";
        String Query = "select * from "+TABLE_NAME + " where id ="+ ID ;
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(Query,null);

        while (cursor.moveToNext())
        {
            if(result.equals(null) || result.equals(""))
            {
                int Id = cursor.getInt(0);
                String userId = cursor.getString(1);
                String password = cursor.getString(2);
                result += Id+" "+userId+" "+password+"\n";
            }
        }
        cursor.close();
        db.close();
        return result;
    }

}
