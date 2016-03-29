package com.chyvaak.criminalintent.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.chyvaak.criminalintent.database.CrimeBaseHelper;
import com.chyvaak.criminalintent.database.CrimeCursorWrapper;
import com.chyvaak.criminalintent.database.CrimeDbSchema;
import com.chyvaak.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Singleton to save array-list of crimes
 */
public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static CrimeLab get(Context context) {
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;

    }

    private CrimeLab(Context context){
        mContext =context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public List<Crime> getCrimes(){
       List<Crime> crimes = new ArrayList<>();
       CrimeCursorWrapper cursor = queryCrimes(null, null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrimes(CrimeDbSchema.Cols.UUID + " =?",
                new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void addCrime(Crime c){
        ContentValues contentValues = getContentValues(c);
        mDatabase.insert(CrimeTable.NAME, null, contentValues);
    }
    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeDbSchema.Cols.UUID, crime.getmId().toString());
        values.put(CrimeDbSchema.Cols.TITLE, crime.getmTitle());
        values.put(CrimeDbSchema.Cols.DATE, crime.getmDate().getTime());
        values.put(CrimeDbSchema.Cols.SOLVED, crime.ismSolved() ? 1 : 0);
        values.put(CrimeDbSchema.Cols.SUSPECT, crime.getmSuspect());

        return values;
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getmId().toString();
        ContentValues contentValues = getContentValues(crime);
        mDatabase.update(CrimeTable.NAME, contentValues,
                CrimeDbSchema.Cols.UUID + " = ?", new String[]{uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null, null, null
        );
        return new CrimeCursorWrapper(cursor);
    }

    public File getPhotoFile(Crime crime){
    File externalFilesDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (externalFilesDir == null) {
            return null;
        }
        return new File(externalFilesDir, crime.getPhotoFilename());
    }

}
