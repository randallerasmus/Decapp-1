package za.ac.cput.decapp.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Domain.Comment;
import za.ac.cput.decapp.Repositories.Interfaces.CommentRepository;

//**
// * Created by User on 2016/05/04.

// this is like my database helper class
public class CommentRepositoryImpl extends SQLiteOpenHelper implements CommentRepository
{
    public static final String TABLE_NAME = "UpdateActivity";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INFO  = "information";
    public static final String COLUMN_COMMENTOFFICIAL = "commentOfficial";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_INFO + " TEXT NOT NULL , "
            + COLUMN_COMMENTOFFICIAL + " TEXT NOT NULL ); ";


    public CommentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open()
            throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public za.ac.cput.decapp.Domain.Comment findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_INFO,
                        COLUMN_COMMENTOFFICIAL
                        },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
                    final Comment comment = new Comment.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .info(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)))
                    .commentOfficial(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENTOFFICIAL)))
                    .build();

            return comment;
        } else {
            return null;
        }
    }

    @Override
    public Comment save(Comment commentEntity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,commentEntity.getId());
       values.put(COLUMN_INFO,commentEntity.getInfo());
        values.put(COLUMN_COMMENTOFFICIAL,commentEntity.getCommentOfficial());
       long id = db.insertOrThrow(TABLE_NAME, null, values);
        Comment commentInserted = new Comment.Builder()
                .copy(commentEntity)
                .id(new Long(id))
                .build();
        return commentInserted;
    }

    @Override
    public Comment update(Comment commentEntity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, commentEntity.getId());
        values.put(COLUMN_INFO, commentEntity.getInfo());
        values.put(COLUMN_COMMENTOFFICIAL,commentEntity.getCommentOfficial());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(commentEntity.getId())}
        );
        return commentEntity;
    }

    @Override
    public Comment delete(Comment commentEntity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(commentEntity.getId())});
        return commentEntity;
    }

    @Override
    public Set<Comment> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Set<Comment> comments = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Comment comment = new Comment.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .info(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)))
                        .commentOfficial(cursor.getString(cursor.getColumnIndex(COLUMN_COMMENTOFFICIAL)))
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




