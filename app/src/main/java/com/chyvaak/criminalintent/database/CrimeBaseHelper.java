package com.chyvaak.criminalintent.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.chyvaak.criminalintent.database.CrimeDbSchema.CrimeTable;

/**
 * Created by chyvaak on 27.03.2016.
 */
public class CrimeBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "crimeBase.db";

    public CrimeBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + CrimeTable.NAME +
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        CrimeDbSchema.Cols.UUID + ", " +
                        CrimeDbSchema.Cols.TITLE + ", " +
                        CrimeDbSchema.Cols.DATE + ", " +
                        CrimeDbSchema.Cols.SOLVED + ", " +
                        CrimeDbSchema.Cols.SUSPECT + ");"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
