package za.ac.cput.decapp.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Domain.Victim;
import za.ac.cput.decapp.Repositories.Interfaces.VictimRepository;

/**
 * Created by User on 2016/05/04.
 */
public class VictimRepositoryImpl extends SQLiteOpenHelper implements VictimRepository{
    public static final String TABLE_NAME = "Victim";
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



    public VictimRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Victim findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                },
                         COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Victim Victim = new Victim.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .build();

            return Victim;
        } else {
            return null;
        }
    }

    @Override
    public Victim save(Victim victimEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, victimEntity.getId());
       values.put(COLUMN_FIRSTNAME, victimEntity.getName());
        values.put(COLUMN_LASTNAME, victimEntity.getSurname());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Victim insertedVictimEntity = new Victim.Builder()
                .copy(victimEntity)
                .id(new Long(id))
                .build();
        return insertedVictimEntity;
    }

    @Override
    public Victim update(Victim victimEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, victimEntity.getId());
        values.put(COLUMN_FIRSTNAME, victimEntity.getName());
        values.put(COLUMN_LASTNAME, victimEntity.getSurname());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(victimEntity.getId())}
        );
        return victimEntity;
    }

    @Override
    public Victim delete(Victim entity) {
        db = this.getWritableDatabase();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Victim> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Victim> Victims = new HashSet<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Victim Victim = new Victim.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .build();
                Victims.add(Victim);
            } while (cursor.moveToNext());
        }
        return Victims;
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


