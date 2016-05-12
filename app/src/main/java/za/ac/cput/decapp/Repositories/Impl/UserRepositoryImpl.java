package za.ac.cput.decapp.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Domain.User;

/**
 * Created by User on 2016/05/04.
 */
public class UserRepositoryImpl {
    public static final String TABLE_NAME = "User";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_UserID = "UserId";
    public static final String COLUMN_screenName = "screenName";
    public static final String COLUMN_email = "email";
    public static final String COLUMN_password = "password";
    public static final String COLUMN_obNumber = "obNumber";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_UserID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_screenName + " TEXT NOT NULL , "
            + COLUMN_email + " TEXT NOT NULL , "
            + COLUMN_password + " TEXT NOT NULL, "
            + COLUMN_obNumber + " TEXT NOT NULL );";


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
                        COLUMN_UserID,
                        COLUMN_screenName,
                        COLUMN_email,
                        COLUMN_password,
                        COLUMN_obNumber},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User User = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .screenName(cursor.getString(cursor.getColumnIndex(COLUMN_screenName)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_email)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_password)))
                    .UserId(cursor.getString(cursor.getColumnIndex(COLUMN_UserID)))
                    .obNumber(cursor.getString(cursor.getColumnIndex(COLUMN_obNumber)))
                    .build();

            return User;
        } else {
            return null;
        }
    }

    @Override
    public User save(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_UserID, entity.getUserId());
        values.put(COLUMN_screenName, entity.getscreenName());
        values.put(COLUMN_email, entity.getemail());
        values.put(COLUMN_password, entity.getString());
       values.put(COLUMN_obNumber, entity.getobNumber());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        User insertedEntity = new User.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public User update(User entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_UserID, entity.getUserId());
        values.put(COLUMN_screenName, entity.getscreenName());
        values.put(COLUMN_email, entity.getemail());
        values.put(COLUMN_password, entity.getString());
       values.put(COLUMN_obNumber, entity.getobNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public User delete(User entity) {
        open();
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
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final User User = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .screenName(cursor.getString(cursor.getColumnIndex(COLUMN_screenName)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_email)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_password)))
                        .UserId(cursor.getString(cursor.getColumnIndex(COLUMN_UserID)))
                        .obNumber(cursor.getString(cursor.getColumnIndex(COLUMN_obNumber)))
                        .build();
                Users.add(User);
            } while (cursor.moveToNext());
        }
        return Users;
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
}

}
