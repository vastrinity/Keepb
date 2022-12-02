package com.example.keepb.Imera;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

public class ImeraHelper extends SQLiteOpenHelper {
    public static final String DATABASE_IMERA = "Imeres.db";
    public static final String TABLE_NAME_IMERES = "Imeres_table";
    public static final String IDDAY = "IDDAY";
    public static final String IMERA = "IMERA";
    public static final String BREAKTIME = "BREAKTIME";
    public static final String BREAKWHAT = "BREAKWHAT";
    public static final String DECTIME = "DECTIME";
    public static final String DECWHAT = "DECWHAT";
    public static final String MESITIME = "MESITIME";
    public static final String MESIWHAT = "MESIWHAT";
    public static final String APOGTIME = "APOGTIME";
    public static final String APOGWHAT = "APOGWHAT";
    public static final String LAUNCHTIME = "LAUNCHTIME";
    public static final String LAUNCHWHAT = "LAUNCHWHAT";
    public static final String WATER = "WATER";
    public static final String ALCOOL = "ALCOOL";
    public static final String ANAPSIKTIKO = "ANAPSIKTIKO";
    public static final String GYM = "GYM";
    public static final String YPNOS = "YPNOS";
    public static final String EPIPLEON = "EPIPLEON";


    public ImeraHelper(Context daycontext) {
        super(daycontext, DATABASE_IMERA, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase dbimera) {
        dbimera.execSQL("create table " + TABLE_NAME_IMERES +
                "(IDDAY INTEGER  PRIMARY KEY AUTOINCREMENT,IMERA INTEGER , BREAKTIME INTEGER,BREAKWHAT INTEGER,DECTIME INTEGER,DECWHAT INTEGER,MESITIME INTEGER,MESIWHAT INTEGER," +
                "APOGTIME INTEGER,APOGWHAT INTEGER,LAUNCHTIME INTEGER,LAUNCHWHAT INTEGER,WATER INTEGER,ALCOOL INTEGER,ANAPSIKTIKO INTEGER,GYM INTEGER,YPNOS INTEGER,EPIPLEON TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase dbimera, int oldVersion, int newVersion) {
        dbimera.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_IMERES);
        this.onCreate(dbimera);


    }

    public boolean insertImera(ImeraClass day) {

        SQLiteDatabase dbday = this.getWritableDatabase();


        ContentValues contentValuesDay = new ContentValues();
        contentValuesDay.put("IMERA", day.getImera());
        contentValuesDay.put("BREAKTIME", day.getBreaktime());
        contentValuesDay.put("BREAKWHAT", day.getBreakwhat());
        contentValuesDay.put("DECTIME", day.getDectime());
        contentValuesDay.put("DECWHAT", day.getDecwhat());
        contentValuesDay.put("MESITIME", day.getMesitime());
        contentValuesDay.put("MESIWHAT", day.getMesiwhat());
        contentValuesDay.put("APOGTIME", day.getApotime());
        contentValuesDay.put("APOGWHAT", day.getApowhat());
        contentValuesDay.put("LAUNCHTIME", day.getLaunchtime());
        contentValuesDay.put("LAUNCHWHAT", day.getLaunchwhat());
        contentValuesDay.put("WATER", day.getWater());
        contentValuesDay.put("ALCOOL", day.getAlcool());
        contentValuesDay.put("ANAPSIKTIKO",day.getAnapsiktiko());
        contentValuesDay.put("GYM", day.getGym());
        contentValuesDay.put("YPNOS", day.getYpnos());
        contentValuesDay.put("EPIPLEON", day.getEpileon());


        long resultday = dbday.insert(TABLE_NAME_IMERES, null, contentValuesDay);
        dbday.close();
        if (resultday == -1)
            return false;

        else
            return true;

    }

    public List<ImeraClass> dayList(String filterday) {
        String queryday;
        if (filterday.equals("")) {
            //regular query
            queryday = "SELECT  * FROM " + TABLE_NAME_IMERES;
        } else {
            //filter results by filter option provided
            queryday = "SELECT  * FROM " + TABLE_NAME_IMERES + " ORDER BY " + filterday;
        }

        List<ImeraClass> dayLinkedList = new LinkedList<>();
        SQLiteDatabase dbday = this.getWritableDatabase();
        Cursor cursorday = dbday.rawQuery(queryday, null);
        ImeraClass day;

        if (cursorday.moveToFirst()) {
            do {
                day = new ImeraClass();

                day.setIdday(cursorday.getLong(cursorday.getColumnIndex(IDDAY)));
                day.setImera(cursorday.getString(cursorday.getColumnIndex(IMERA)));
                day.setBreaktime(cursorday.getString(cursorday.getColumnIndex(BREAKTIME)));
                day.setBreakwhat(cursorday.getString(cursorday.getColumnIndex(BREAKWHAT)));
                day.setDectime(cursorday.getString(cursorday.getColumnIndex(DECTIME)));
                day.setDecwhat(cursorday.getString(cursorday.getColumnIndex(DECWHAT)));
                day.setMesitime(cursorday.getString(cursorday.getColumnIndex(MESITIME)));
                day.setMesiwhat(cursorday.getString(cursorday.getColumnIndex(MESIWHAT)));
                day.setApotime(cursorday.getString(cursorday.getColumnIndex(APOGTIME)));

                day.setApowhat(cursorday.getString(cursorday.getColumnIndex(APOGWHAT)));
                day.setLaunchtime(cursorday.getString(cursorday.getColumnIndex(LAUNCHTIME)));
                day.setLaunchwhat(cursorday.getString(cursorday.getColumnIndex(LAUNCHWHAT)));
                day.setWater(cursorday.getString(cursorday.getColumnIndex(WATER)));
                day.setAlcool(cursorday.getString(cursorday.getColumnIndex(ALCOOL)));
                day.setAnapsiktiko(cursorday.getString(cursorday.getColumnIndex(ANAPSIKTIKO)));
                day.setGym(cursorday.getString(cursorday.getColumnIndex(GYM)));

                day.setYpnos(cursorday.getString(cursorday.getColumnIndex(YPNOS)));
                day.setEpileon(cursorday.getString(cursorday.getColumnIndex(EPIPLEON)));

                dayLinkedList.add(day);
            } while (cursorday.moveToNext());
        }


        return dayLinkedList;
    }


    /**
     * Query only 1 record
     **/
    public ImeraClass getImeraClass(long idday) {
        SQLiteDatabase dbimera = this.getWritableDatabase();
        String queryday = "SELECT  * FROM " + TABLE_NAME_IMERES + " WHERE IDDAY=" + idday;
        Cursor cursorday = dbimera.rawQuery(queryday, null);

        ImeraClass receivedDay = new ImeraClass();
        if (cursorday.getCount() > 0) {
            cursorday.moveToFirst();


            receivedDay.setIdday(cursorday.getLong(cursorday.getColumnIndex(IDDAY)));
            receivedDay.setImera(cursorday.getString(cursorday.getColumnIndex(IMERA)));
            receivedDay.setBreaktime(cursorday.getString(cursorday.getColumnIndex(BREAKTIME)));
            receivedDay.setBreakwhat(cursorday.getString(cursorday.getColumnIndex(BREAKWHAT)));
            receivedDay.setDectime(cursorday.getString(cursorday.getColumnIndex(DECTIME)));
            receivedDay.setDecwhat(cursorday.getString(cursorday.getColumnIndex(DECWHAT)));
            receivedDay.setMesitime(cursorday.getString(cursorday.getColumnIndex(MESITIME)));
            receivedDay.setMesiwhat(cursorday.getString(cursorday.getColumnIndex(MESIWHAT)));
            receivedDay.setApotime(cursorday.getString(cursorday.getColumnIndex(APOGTIME)));

            receivedDay.setApowhat(cursorday.getString(cursorday.getColumnIndex(APOGWHAT)));
            receivedDay.setLaunchtime(cursorday.getString(cursorday.getColumnIndex(LAUNCHTIME)));
            receivedDay.setLaunchwhat(cursorday.getString(cursorday.getColumnIndex(LAUNCHWHAT)));

            receivedDay.setWater(cursorday.getString(cursorday.getColumnIndex(WATER)));
            receivedDay.setAlcool(cursorday.getString(cursorday.getColumnIndex(ALCOOL)));
            receivedDay.setAnapsiktiko(cursorday.getString(cursorday.getColumnIndex(ANAPSIKTIKO)));
            receivedDay.setGym(cursorday.getString(cursorday.getColumnIndex(GYM)));


            receivedDay.setYpnos(cursorday.getString(cursorday.getColumnIndex(YPNOS)));
            receivedDay.setEpileon(cursorday.getString(cursorday.getColumnIndex(EPIPLEON)));


        }


        return receivedDay;


    }

    /**
     * delete record
     **/
    public void deleteDayRecord(long idday, Context daycontext) {
        SQLiteDatabase dbday = this.getWritableDatabase();

        dbday.execSQL("DELETE FROM " + TABLE_NAME_IMERES + " WHERE IDDAY='" + idday + "'");
        Toast.makeText(daycontext, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }


    /**
     * update record
     **/
    public void updateDayRecord(long idDay, Context context, ImeraClass updatedDay) {
        SQLiteDatabase dbday = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        dbday.execSQL("UPDATE  " + TABLE_NAME_IMERES + " SET IMERA ='" + updatedDay.getImera() +
                "', BREAKTIME ='" + updatedDay.getBreaktime() +
                "', BREAKWHAT ='" + updatedDay.getBreakwhat() +
                "', DECTIME ='" + updatedDay.getDectime() +
                "', DECWHAT ='" + updatedDay.getDecwhat() +
                "', MESITIME ='" + updatedDay.getMesitime() +
                "', MESIWHAT ='" + updatedDay.getMesiwhat() +

                "', APOGTIME ='" + updatedDay.getApotime() +
                "', APOGWHAT ='" + updatedDay.getApowhat() +
                "', LAUNCHTIME ='" + updatedDay.getLaunchtime() +
                "', LAUNCHWHAT ='" + updatedDay.getLaunchwhat() +
                "',WATER ='" + updatedDay.getWater() +
                "', ALCOOL ='" + updatedDay.getAlcool() +

                "',ANAPSIKTIKO ='" + updatedDay.getAnapsiktiko() +

                "',GYM ='"+updatedDay.getGym()+

                "', YPNOS ='" + updatedDay.getYpnos() +
                "', EPIPLEON ='" + updatedDay.getEpileon() +
                " 'WHERE IDDAY=" + idDay);
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();

    }
    public int getImeraLast() {
        SQLiteDatabase dbday = this.getReadableDatabase();
        String squery = "SELECT (" + IMERA + ") FROM Imeres_table  ";


        Cursor c = dbday.rawQuery(squery, null);
        int lastimera;

        if (c.getCount() > 0) {
            c.moveToPosition(c.getCount() - 1);

            lastimera = c.getInt(0);
        } else {
            lastimera = 0;

        }
        return lastimera;
    }




    public float get_Avg(String column) {
        SQLiteDatabase dbday = this.getReadableDatabase();


        String query = "SELECT AVG(" + column + ") FROM Imeres_table";
        Cursor c = dbday.rawQuery(query, null);
//Add in the movetofirst etc here?
        c.moveToFirst();
        float AverageValue = c.getFloat(0);


        return AverageValue;
    }

    public int countDays() {
        SQLiteDatabase dbimera = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME_IMERES ;
        Cursor cursorday = dbimera.rawQuery(query, null);
        int days = cursorday.getCount();


        return days;


    }
}
