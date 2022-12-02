package com.example.keepb.RecyclerViewClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE2_="metriseis2.db";
    public static final String TABLE_NAME2="metriseis2_table";
    public static final String ID="ID";
    public static final String DATE="DATE";
    public static final String KILA="KILA";
    public static final String LIPOS="LIPOS";
    public static final String YGRA="YGRA";
    public static final String MYS="MYS";
    public static final String META_AGE="META_AGE";
    public static final String FAT_LEVEL="FAT_LEVEL";








    public DatabaseHelper( Context context) {
        super(context, DATABASE2_, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME2 +
               "( ID INTEGER  PRIMARY KEY AUTOINCREMENT,DATE TEXT ,KILA TEXT  ,LIPOS TEXT , YGRA TEXT , MYS TEXT , META_AGE  TEXT , FAT_LEVEL TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);
        this.onCreate(db);

    }
    public boolean insertData(ModelClass metrisi){

        SQLiteDatabase db=this.getWritableDatabase();


        ContentValues contentValues =new ContentValues();

        contentValues.put("DATE",metrisi.getDate());
        contentValues.put("KILA",metrisi.getKila());
        contentValues.put("LIPOS",metrisi.getLipos());
        contentValues.put("YGRA",metrisi.getYgra());
        contentValues.put("MYS",metrisi.getMys());
        contentValues.put("META_AGE",metrisi.getMeta_age());
        contentValues.put("FAT_LEVEL",metrisi.getFat_level());






        long result =db.insert(TABLE_NAME2,null,contentValues);
        db.close();
        if(result==-1)
            return false;

        else
            return  true;






    }

    public List<ModelClass> metrsiList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME2;
        }else{
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME2 + " ORDER BY "+ filter;
        }

        List<ModelClass> metrisiLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ModelClass metrisi;

        if (cursor.moveToFirst()) {
            do {
                metrisi = new ModelClass();

                metrisi.setId(cursor.getLong(cursor.getColumnIndex(ID)));
                metrisi.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                metrisi.setKila(cursor.getString(cursor.getColumnIndex(KILA)));
                metrisi.setLipos(cursor.getString(cursor.getColumnIndex(LIPOS)));
                metrisi.setYgra(cursor.getString(cursor.getColumnIndex(YGRA)));
                metrisi.setMys(cursor.getString(cursor.getColumnIndex(MYS)));
                metrisi.setMeta_age(cursor.getString(cursor.getColumnIndex(META_AGE)));
                metrisi.setFat_level(cursor.getString(cursor.getColumnIndex(FAT_LEVEL)));
               metrisiLinkedList.add(metrisi);
            } while (cursor.moveToNext());
        }


        return metrisiLinkedList;
    }


    /**Query only 1 record**/
    public ModelClass getModelClass(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME2 + " WHERE id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        ModelClass receivedMetrisi = new ModelClass();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedMetrisi.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            receivedMetrisi.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
            receivedMetrisi.setKila(cursor.getString(cursor.getColumnIndex(KILA)));
            receivedMetrisi.setLipos(cursor.getString(cursor.getColumnIndex(LIPOS)));
            receivedMetrisi.setYgra(cursor.getString(cursor.getColumnIndex(YGRA)));
            receivedMetrisi.setMys(cursor.getString(cursor.getColumnIndex(MYS)));
            receivedMetrisi.setMeta_age(cursor.getString(cursor.getColumnIndex(META_AGE)));
            receivedMetrisi.setFat_level(cursor.getString(cursor.getColumnIndex(FAT_LEVEL)));
        }



        return receivedMetrisi;


    }


    /**delete record**/
    public void deleteMetrisiRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME2+" WHERE id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }


    /**update record**/
    public void updateMetrisiRecord(long metrisiId, Context context, ModelClass updatedmetrisi) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        db.execSQL("UPDATE  "+TABLE_NAME2+" SET date ='"+ updatedmetrisi.getDate()+
                "', kila ='" + updatedmetrisi.getKila()+
                "', lipos ='"+ updatedmetrisi.getLipos() +
                "', ygra ='"+ updatedmetrisi.getYgra() +
                "', mys ='"+ updatedmetrisi.getMys() +
                "', meta_age ='"+ updatedmetrisi.getMeta_age() +
                "', fat_level ='"+ updatedmetrisi.getFat_level() +

                "'  WHERE id='" + metrisiId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }
   public float getKilaLast() {
       SQLiteDatabase db = this.getReadableDatabase();
       String squery = "SELECT (" + KILA + ") FROM metriseis2_table  ";


       Cursor c = db.rawQuery(squery, null);
       float lastKila;

       if (c.getCount() > 0) {
           c.moveToPosition(c.getCount() - 1);

           lastKila = c.getFloat(0);
       } else {
           lastKila = 0;

       }
       return lastKila;
   }
       public float getKilaFirst(){
           SQLiteDatabase dbf = this.getReadableDatabase();
           String fquery = "SELECT (" + KILA + ") FROM metriseis2_table  ";


           Cursor cf = dbf.rawQuery(fquery, null);
           float firstKila;

           if (cf.getCount() > 0) {
               cf.moveToPosition(0);

               firstKila = cf.getFloat(0);
           } else {
               firstKila = 0;

           }


           return firstKila;

       }





}
