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
import za.ac.cput.decapp.Domain.Transfer;
import za.ac.cput.decapp.Repositories.Interfaces.TransferRepository;

/**
 * Created by User on 2016/05/04.
 */
public class TransferRepositoryImpl extends SQLiteOpenHelper implements TransferRepository{
    public static final String TABLE_NAME = "Transfer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TransferID = "TransferId";
    public static final String COLUMN_TransferIMAGE = "TransferImage";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TransferID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_TransferIMAGE + " BLOB , ";



    public TransferRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Transfer findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TransferIMAGE,
                        COLUMN_TransferID
                },
                        COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Transfer Transfer = new Transfer.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .suspectImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_TransferIMAGE)))
                    .TransferId(cursor.getString(cursor.getColumnIndex(COLUMN_TransferID)))
                    .build();

            return Transfer;
        } else {
            return null;
        }
    }

    @Override
    public Transfer save(Transfer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TransferID, entity.getTransferId());
       values.put(COLUMN_TransferIMAGE, entity.getsuspectImage());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Transfer insertedEntity = new Transfer.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Transfer update(Transfer entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TransferID, entity.getTransferId());
        values.put(COLUMN_TransferIMAGE, entity.getsuspectImage());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Transfer delete(Transfer entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Transfer> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Transfer> Transfers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Transfer Transfer = new Transfer.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .suspectImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_TransferIMAGE)))
                        .TransferId(cursor.getString(cursor.getColumnIndex(COLUMN_TransferID)))
                        .build();
                Transfers.add(Transfer);
            } while (cursor.moveToNext());
        }
        return Transfers;
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


