package com.chyvaak.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.chyvaak.criminalintent.util.Crime;


import java.sql.Date;
import java.util.UUID;

/**
 * Child class of Cursor where was realised method witch helping to wrate to DB.
 */
public class CrimeCursorWrapper extends CursorWrapper {

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeDbSchema.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.Cols.TITLE));
        long date = getLong(getColumnIndex(CrimeDbSchema.Cols.DATE));
        int isSolved = getInt(getColumnIndex(CrimeDbSchema.Cols.SOLVED));
        String suspect = getString(getColumnIndex(CrimeDbSchema.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setmTitle(title);
        crime.setmDate(new Date(date));
        crime.setmSolved(isSolved != 0);
        crime.setmSuspect(suspect);
        return crime;
    }
}
