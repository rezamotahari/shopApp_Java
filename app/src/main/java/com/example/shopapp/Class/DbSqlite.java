package com.example.shopapp.Class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.shopapp.Model.ModelFav;

import java.util.ArrayList;
public class DbSqlite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="db_shop";
    private static final String TABLE_NAME="tbl_favorite";

    private static final String ID="id";
    private static final String IMAGE="image";
    private static final String TITLE="title";
    private static final String VIISIT="visit";
    private static final String FREEPRICE="freeprice";
    private static final String PRICE="price";
    private static final String LABEL="label";

    private static final String CREATETABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            " " + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
            " " + IMAGE+ " TEXT NOT NULL , " +
            " " + TITLE + " TEXT NOT NULL ," +
            " " + VIISIT + " TEXT NOT NULL ," +
            " " + FREEPRICE + " TEXT NOT NULL ," +
            " " + PRICE + " TEXT NOT NULL ," +
            " " + LABEL + " TEXT NOT NULL " +
            ");";

     Context contextt;


    public DbSqlite(Context context) {
        super(context, DATABASE_NAME, null, 1);
        contextt = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertFav(ModelFav modelFav)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID,modelFav.getId());
        values.put(IMAGE,modelFav.getImage());
        values.put(TITLE,modelFav.getTitle());
        values.put(VIISIT,modelFav.getVisit());
        values.put(FREEPRICE,modelFav.getFreeprice());
        values.put(PRICE,modelFav.getPrice());
        values.put(LABEL,modelFav.getLabel());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public ArrayList<ModelFav> showData() {
        ArrayList<ModelFav> favs = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ModelFav note = new ModelFav();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setImage(cursor.getString(1));
                note.setTitle(cursor.getString(2));
                note.setVisit(cursor.getString(3));
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
      db.delete(TABLE_NAME,"id="+id,null);

  }
  public Cursor cu(int id)
  {
      SQLiteDatabase db = this.getWritableDatabase();

      Cursor cursor = db.rawQuery("SELECT * FROM tbl_favorite WHERE id=" + id, null);
      return cursor;

  }
}
