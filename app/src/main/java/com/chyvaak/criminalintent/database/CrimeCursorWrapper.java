package com.chyvaak.criminalintent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.chyvaak.criminalintent.util.Crime;


import java.sql.Date;
import java.util.UUID;

/**
 * Created by chyvaak on 27.03.2016.
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

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setmTitle(title);
        crime.setmDate(new Date(date));
        crime.setmSolved(isSolved != 0);
        return crime;
    }
}
