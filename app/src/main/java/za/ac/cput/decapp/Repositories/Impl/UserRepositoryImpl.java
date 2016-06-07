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
import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;

/**
 * Created by User on 2016/05/04.
 */
public abstract class UserRepositoryImpl extends SQLiteOpenHelper implements UserRepository {
    public static final String TABLE_NAME = "User";
    private SQLiteDatabase db;
   


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_AUTHORIZATIONNUMBER = "obnumber";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL, "
            + COLUMN_AUTHORIZATIONNUMBER + " TEXT NOT NULL );";


    public UserRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public User findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD,
                        COLUMN_AUTHORIZATIONNUMBER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User User = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .authorizationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHORIZATIONNUMBER)))
                    .build();

            return User;
        } else {
            return null;
        }
    }

    @Override
    public User save(User UserEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, UserEntity.getId());
        values.put(COLUMN_USERNAME, UserEntity.getUsername());
        values.put(COLUMN_PASSWORD, UserEntity.getPassword());
       values.put(COLUMN_AUTHORIZATIONNUMBER, UserEntity.getAuthorizationNumber());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        User insertedEntity = new User.Builder()
                .copy(UserEntity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User UserEntity) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, UserEntity.getId());
        values.put(COLUMN_USERNAME, UserEntity.getUsername());
        values.put(COLUMN_PASSWORD, UserEntity.getPassword());
        values.put(COLUMN_AUTHORIZATIONNUMBER, UserEntity.getAuthorizationNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(UserEntity.getId())}
        );
        return UserEntity;
    }

    @Override
    public User delete(User entity) {
        db = this.getWritableDatabase();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<User> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<User> Users = new HashSet<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final User User = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .authorizationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHORIZATIONNUMBER)))
                        .build();
                Users.add(User);
            } while (cursor.moveToNext());
        }
        return Users;
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
}


