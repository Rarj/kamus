package dev.arjuna.ri0.projetckamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "kamus";

    public static String TABLE_INGGRIS = "inggris";
    public static String TABLE_INDONESIA = "indonesia";

    public static String FIELD_ID = "id";
    public static String FIELD_KATA = "kata";
    public static String FIELD_TRANSLATE = "translate";

    private static final int DATABASE_VERSION = 1;

    private static String CREATE_TABLE_ENGLISH = "create table " + TABLE_INGGRIS + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_KATA + " text not null, " +
            FIELD_TRANSLATE + " text not null);";

    private static String CREATE_TABLE_INDONESIA = "create table " + TABLE_INDONESIA + " (" +
            FIELD_ID + " integer primary key autoincrement, " +
            FIELD_KATA + " text not null, " +
            FIELD_TRANSLATE + " text not null);";

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENGLISH);
        db.execSQL(CREATE_TABLE_INDONESIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGGRIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INDONESIA);
        onCreate(db);
    }
}
