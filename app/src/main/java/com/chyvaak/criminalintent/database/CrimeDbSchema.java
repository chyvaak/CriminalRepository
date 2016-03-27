package com.chyvaak.criminalintent.database;

/**
 * Created by chyvaak on 27.03.2016.
 */
public class CrimeDbSchema {
    public static final class CrimeTable{
        public static final String NAME = "Crimes";
    }

    public static final class Cols{
        public static final String UUID = "uuid";
        public static final String TITLE = "title";
        public static final String DATE = "date";
        public static final String SOLVED = "solved";
    }
}
