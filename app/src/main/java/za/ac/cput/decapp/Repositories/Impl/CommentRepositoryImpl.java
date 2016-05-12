package za.ac.cput.decapp.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Comment;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Repositories.CommentRepository;

/**
 * Created by User on 2016/05/04.
 */
// this is like my database helper class
public class CommentRepositoryImpl extends SQLiteOpenHelper implements CommentRepository
{
    public static final String TABLE_NAME = "Comment";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INFO  = "information";
    public static final String COLUMN_DATE = "date";
   

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_INFO + " TEXT NOT NULL , "
            + COLUMN_DATE + " TEXT NOT NULL ); ";


    public CommentRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Comment findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_INFO,
                        COLUMN_DATE
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
                    .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                    .build();

            return comment;
        } else {
            return null;
        }
    }
//    public boolean insertData(String name, String surname)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contenValues = new ContentValues();
//        contenValues.put(COL_2,name);
//        contenValues.put(COL_3,surname);
//        long result = db.insert(TABLE_NAME,null,contenValues);
//        if(result==-1)
//            return false;
//        else
//            return true;
//    }
//}

    @Override
    public Comment save(String offense,Date date) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id.getId());
       values.put(COLUMN_INFO, entity.getInfo());
        values.put(COLUMN_DATE, entity.getDate());
       long id = db.insertOrThrow(TABLE_NAME, null, values);
        Comment insertedEntity = new Comment.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Comment update(Comment entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CommentID, entity.getCommentId());
        values.put(COLUMN_INFO, entity.getFirstname());
        values.put(COLUMN_DATE, entity.getLastName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Comment delete(Comment entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Comment> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();

        Set<Comment> Comments = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Comment Comment = new Comment.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .info(cursor.getString(cursor.getColumnIndex(COLUMN_INFO)))
                        .date(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)))
                        .build();
                Comments.add(Comment);
            } while (cursor.moveToNext());
        }
        return Comments;
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
}



}
