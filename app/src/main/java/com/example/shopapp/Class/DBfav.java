package com.example.shopapp.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shopapp.Model.FavModel;

import java.util.ArrayList;


public class DBfav extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="db_shop";
    private static final String TBL_NAME="tbl_name";

    private static final String ID="id";
    private static final String NAME="name";
    private static final String VIEW="visit";
    private static final String PRICE="price";
    private static final String FREEPLRICE="freeprice";
    private static final String IMAGE="image";
    private static final String LABEL="label";

    private static final String CREATTABLE="CREATE TABLE "+TBL_NAME+"("+
            " "+ID+" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, "+
            " "+IMAGE+" TEXT NOT NULL, "+
            " "+NAME+" TEXT NOT NULL, "+
            " "+PRICE+" TEXT NOT NULL, "+
            " "+FREEPLRICE+" TEXT NOT NULL, "+
            " "+VIEW+" TEXT NOT NULL, "+
            " " + LABEL + " TEXT NOT NULL " +
            ");";





    private Context context;

    public DBfav(Context context) {
        super(context, DATABASE_NAME, null, 1);
        context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATTABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void insertFav(FavModel modelFav)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,modelFav.getId());
        values.put(IMAGE,modelFav.getImage());
        values.put(NAME,modelFav.getName());
        values.put(VIEW,modelFav.getView());
        values.put(FREEPLRICE,modelFav.getFreeprice());
        values.put(PRICE,modelFav.getPrice());
       values.put(LABEL,modelFav.getLabel());
        db.insert(TBL_NAME,null,values);
        db.close();
    }

    public ArrayList<FavModel> showData() {
        ArrayList<FavModel> favs = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TBL_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FavModel note = new FavModel();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setImage(cursor.getString(1));
                note.setName(cursor.getString(2));
                note.setView(cursor.getString(3));
                note.setFreeprice(cursor.getString(4));
                note.setPrice(cursor.getString(5));
                note.setLabel(cursor.getString(6));
                favs.add(note);
            } while (cursor.moveToNext());


        }
        return favs;
    }
    public void deleteFav(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_NAME,"id="+id,null);

    }
    public Cursor cu(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM tbl_favorite WHERE id=" + id, null);
        return cursor;

    }
}
