package dev.arjuna.ri0.projetckamus.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;

import dev.arjuna.ri0.projetckamus.model.KamusModel;

public class KamusHelper {
    private static String INGGRIS = DatabaseHelper.TABLE_INGGRIS;
    private static String INDONESIA = DatabaseHelper.TABLE_INDONESIA;

    private Context context;
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        databaseHelper.close();
    }

    private Cursor searchQueryByName(String query, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS : INDONESIA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE +
                " WHERE " + DatabaseHelper.FIELD_KATA + " LIKE '%" + query.trim() + "%'", null);
    }

    public ArrayList<KamusModel> getDataByName(String search, boolean isEnglish) {
        KamusModel kamusModel;

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        Cursor cursor = searchQueryByName(search, isEnglish);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_KATA)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TRANSLATE)));
                arrayList.add(kamusModel);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public String getData(String search, boolean isEnglish) {
        String result = "";
        Cursor cursor = searchQueryByName(search, isEnglish);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            result = cursor.getString(2);
            for (; !cursor.isAfterLast(); cursor.moveToNext()) {
                result = cursor.getString(2);
            }
        }
        cursor.close();
        return result;
    }

    private Cursor queryAllData(boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS : INDONESIA;
        return database.rawQuery("SELECT * FROM " + DATABASE_TABLE + " ORDER BY " + DatabaseHelper.FIELD_ID + " ASC", null);
    }

    public ArrayList<KamusModel> getAllData(boolean isEnglish) {
        KamusModel kamusModel;

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        Cursor cursor = queryAllData(isEnglish);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_ID)));
                kamusModel.setKata(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_KATA)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.FIELD_TRANSLATE)));
                arrayList.add(kamusModel);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insert(KamusModel kamusModel, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS : INDONESIA;
        ContentValues initialValues = new ContentValues();
        initialValues.put(DatabaseHelper.FIELD_KATA, kamusModel.getKata());
        initialValues.put(DatabaseHelper.FIELD_TRANSLATE, kamusModel.getTranslate());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public void insertTransaction(ArrayList<KamusModel> kamusModels, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS : INDONESIA;
        String sql = "INSERT INTO " + DATABASE_TABLE + " (" +
                DatabaseHelper.FIELD_KATA + ", " +
                DatabaseHelper.FIELD_TRANSLATE + ") VALUES (?, ?)";

        database.beginTransaction();

        SQLiteStatement stmt = database.compileStatement(sql);
        for (int i = 0; i < kamusModels.size(); i++) {
            stmt.bindString(1, kamusModels.get(i).getKata());
            stmt.bindString(2, kamusModels.get(i).getTranslate());
            stmt.execute();
            stmt.clearBindings();
        }

        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public void update(KamusModel kamusModel, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS : INDONESIA;
        ContentValues args = new ContentValues();
        args.put(DatabaseHelper.FIELD_KATA, kamusModel.getKata());
        args.put(DatabaseHelper.FIELD_TRANSLATE, kamusModel.getTranslate());
        database.update(DATABASE_TABLE, args, DatabaseHelper.FIELD_ID + "= '" + kamusModel.getId() + "'", null);
    }

    public void delete(int id, boolean isEnglish) {
        String DATABASE_TABLE = isEnglish ? INGGRIS: INDONESIA;
        database.delete(DATABASE_TABLE, DatabaseHelper.FIELD_ID + " = '" + id + "'", null);
    }
}
