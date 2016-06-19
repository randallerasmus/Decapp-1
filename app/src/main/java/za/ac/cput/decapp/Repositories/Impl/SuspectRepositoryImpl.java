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
import za.ac.cput.decapp.Domain.Suspect;
import za.ac.cput.decapp.Repositories.Interfaces.SuspectRepository;

/**
 * Created by User on 2016/05/04.
 */
public class SuspectRepositoryImpl extends SQLiteOpenHelper implements SuspectRepository{
    public static final String TABLE_NAME = "Suspect";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , ";



    public SuspectRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void close() {
        this.close();
    }

    @Override
    public Suspect findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME
                },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Suspect Suspect = new Suspect.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .build();

            return Suspect;
        } else {
            return null;
        }
        }
    @Override
    public Suspect save(Suspect suspectEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, suspectEntity.getId());
        values.put(COLUMN_FIRSTNAME, suspectEntity.getName());
        values.put(COLUMN_LASTNAME, suspectEntity.getSurname());
       long id = db.insertOrThrow(TABLE_NAME, null, values);
        Suspect suspectInserted = new Suspect.Builder()
                .copy(suspectEntity)
                .id(new Long(id))
                .build();
        return suspectInserted;
    }

    @Override
    public Suspect update(Suspect suspectEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, suspectEntity.getId());
        values.put(COLUMN_FIRSTNAME, suspectEntity.getName());
        values.put(COLUMN_LASTNAME, suspectEntity.getSurname());
              db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(suspectEntity.getId())}
        );
        return suspectEntity;
    }

    @Override
    public Suspect delete(Suspect suspectEntity) {
        db = this.getWritableDatabase();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(suspectEntity.getId())});
        return suspectEntity;
    }

    @Override
    public Set<Suspect> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Suspect> Suspects = new HashSet<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Suspect Suspect = new Suspect.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                         .build();
                Suspects.add(Suspect);
            } while (cursor.moveToNext());
        }
        return Suspects;
    }

    @Override
    public int deleteAll() {
        db = this.getWritableDatabase();
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
                        + newVersion + ", which will destroy all old data");
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


