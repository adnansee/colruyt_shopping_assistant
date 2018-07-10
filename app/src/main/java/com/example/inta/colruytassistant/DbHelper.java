package com.example.inta.colruytassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "DBLIST";
    private static final int DB_VER = 1;
    private static final String DB_TABLE = "ShoppingList";
    private static final String DB_COLUM = "ListName";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL);", DB_TABLE,DB_COLUM);
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = String.format("DELETE TABLE IF EXISTS %s", DB_TABLE);
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);


    }




    public void insertList(String list){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUM,list);
        sqLiteDatabase.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        sqLiteDatabase.close();

    }

    public void deleteList(String list){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE,DB_COLUM + " = ?", new String[]{list});
        sqLiteDatabase.close();
    }

    public ArrayList<String> getList (){
        ArrayList<String> shoppingLists = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(DB_TABLE, new String[] {DB_COLUM}, null,null,null,null,null);
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DB_COLUM);
            shoppingLists.add(cursor.getString(index));

        }
        cursor.close();
        sqLiteDatabase.close();
        return shoppingLists;
    }
}
