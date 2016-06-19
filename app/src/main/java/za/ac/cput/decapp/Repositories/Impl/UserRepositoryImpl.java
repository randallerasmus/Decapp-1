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
import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;


/**
 * Created by User on 2016/05/04.
 */
public class UserRepositoryImpl extends SQLiteOpenHelper implements UserRepository
{
    public static final String TABLE_NAME = "User";
    public SQLiteDatabase db;


    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_PASSWORDCONFIRMATION = "passwordconfirmation";
    public static final String COLUMN_AUTHORIZATIONNUMBER = "authorizationNumber";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL , "
            + COLUMN_PASSWORDCONFIRMATION + " TEXT NOT NULL , "
            + COLUMN_AUTHORIZATIONNUMBER + " TEXT );";


    public UserRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    public void open()  {
        db = this.getWritableDatabase();

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
                        COLUMN_PASSWORDCONFIRMATION,
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
                    .passwordConfirmation(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORDCONFIRMATION)))
                    .authorizationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHORIZATIONNUMBER)))
                    .build();

            return User;
        } else {
            return null;
        }
    }

    @Override
    public User save(User UserEntity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,UserEntity.getId());
        values.put(COLUMN_USERNAME,UserEntity.getUsername());
        values.put(COLUMN_PASSWORD, UserEntity.getPassword());
        values.put(COLUMN_PASSWORDCONFIRMATION,UserEntity.getPasswordConfirmation());
        values.put(COLUMN_AUTHORIZATIONNUMBER,UserEntity.getAuthorizationNumber());
        Long id = db.insertOrThrow(TABLE_NAME,null,values);
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
        values.put(COLUMN_PASSWORDCONFIRMATION,UserEntity.getPasswordConfirmation());
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
        Set<User> users = new HashSet<>();
        db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                User User = new User.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .passwordConfirmation(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORDCONFIRMATION)))
                        .authorizationNumber(cursor.getString(cursor.getColumnIndex(COLUMN_AUTHORIZATIONNUMBER)))
                        .build();
                users.add(User);
            } while (cursor.moveToNext());
        }
        return users;
    }

    @Override
    public int deleteAll() {
        db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }

    @Override
    public String getUserEntry(String usernameString) {
        db = this.getWritableDatabase();
        Cursor cursor = db.query("DATABASE_NAME.TABLE_NAME", null,  "COLUMN_USERNAME=?", new String[]{usernameString},null ,null, null);

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
    public void close() {
        this.close();
    }

}



