package za.ac.cput.decapp.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Domain.Case;
import za.ac.cput.decapp.Repositories.Interfaces.CaseRepository;
//import za.ac.cput.decapp.Repositories.Interfaces.CaseRepository;

/**
 * Created by User on 2016/05/04.
 */
public class CaseRepositoryImpl extends SQLiteOpenHelper implements CaseRepository
{
    public static final String TABLE_NAME = "Case";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_OFFENSE  = "offense";
    public static final String COLUMN_OFFENSELOCATION = "offenseLocation";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_OFFENSE + " TEXT NOT NULL , "
            + COLUMN_OFFENSELOCATION + " TEXT NOT NULL ); ";

    public CaseRepositoryImpl (Context context)
    {
        super (context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()
    {
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void close()
    {
        this.close();
    }
    @Override
    public za.ac.cput.decapp.Domain.Case findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_OFFENSE,
                        COLUMN_OFFENSELOCATION
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Case cases = new Case.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .offense(cursor.getString(cursor.getColumnIndex(COLUMN_OFFENSE)))
                    .offenseLocation(cursor.getString(cursor.getColumnIndex(COLUMN_OFFENSELOCATION)))
                    .build();

            return cases;
        } else {
            return null;
        }
    }
    @Override
    public Case save(Case caseEntity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,caseEntity.getId());
        values.put(COLUMN_OFFENSE,caseEntity.getOffense());
        values.put(COLUMN_OFFENSELOCATION,caseEntity.getOffenseLocation());
        long id = db.insert(TABLE_NAME, null, values);
        Case caseInserted = new Case.Builder()
                .copy(caseEntity)
                .id(new Long(id))
                .build();
        return caseInserted;
    }
    @Override
    public Case update(Case caseEntity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, caseEntity.getId());
        values.put(COLUMN_OFFENSE, caseEntity.getOffense());
        values.put(COLUMN_OFFENSELOCATION,caseEntity.getOffenseLocation());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(caseEntity.getId())}
        );
        return caseEntity;
    }

    @Override
    public Case delete(Case caseEntity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(caseEntity.getId())});
        return caseEntity;
    }

    @Override
    public Set<Case> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Set<Case> comments = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Case comment = new Case.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .offense(cursor.getString(cursor.getColumnIndex(COLUMN_OFFENSE)))
                        .offenseLocation(cursor.getString(cursor.getColumnIndex(COLUMN_OFFENSELOCATION)))
                        .build();
                comments.add(comment);
            } while (cursor.moveToNext());
        }
        return comments;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", THIS WILL OVERWRITE OLD DATE");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    @Override
    public String getUserEntry(String username) {
        Cursor cursor = db.query("User", null, " COLUMN_USERNAME=?", new String[]{username}, null, null, null);

        if (cursor.getCount()<1)
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }
}