package za.ac.cput.decapp.Conf.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import za.ac.cput.decapp.Repositories.Impl.CaseRepositoryImpl;
import za.ac.cput.decapp.Repositories.Impl.CommentRepositoryImpl;
import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
import za.ac.cput.decapp.Repositories.Impl.TransferRepositoryImpl;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
import za.ac.cput.decapp.Repositories.Impl.VictimRepositoryImpl;

//import za.ac.cput.decapp.Repositories.Impl.CaseRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Impl.CommentRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Impl.TransferRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
//import za.ac.cput.decapp.Repositories.Impl.VictimRepositoryImpl;

/**
 * Created by User on 2016/06/04.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String cases = " CREATE TABLE " + CaseRepositoryImpl.TABLE_NAME
                + "("
                + CaseRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + CaseRepositoryImpl.COLUMN_OFFENSE + " TEXT  NOT NULL , "
                + CaseRepositoryImpl.COLUMN_OFFENSELOCATION + " TEXT NOT NULL, "
                + ")";


        // Database creation sql statement
        String comments = " CREATE TABLE " + CommentRepositoryImpl.TABLE_NAME
                + "("
                + CommentRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + CommentRepositoryImpl.COLUMN_INFO + " TEXT UNIQUE NOT NULL , "
                + CommentRepositoryImpl.COLUMN_COMMENTOFFICIAL + " TEXT UNIQUE NOT NULL , "
                + ")";

        String suspects = " CREATE TABLE " + SuspectRepositoryImpl.TABLE_NAME
                + "("
                + SuspectRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + SuspectRepositoryImpl.COLUMN_FIRSTNAME + " TEXT NOT NULL , "
                + SuspectRepositoryImpl.COLUMN_LASTNAME + " TEXT NOT NULL , "
                + ")";


        String transfer = " CREATE TABLE " + TransferRepositoryImpl.TABLE_NAME
                + "("
                + TransferRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + TransferRepositoryImpl.COLUMN_TransferID + " TEXT  NOT NULL , "
                + TransferRepositoryImpl.COLUMN_TransferIMAGE + " BLOB , "
                + ")";

        String user = " CREATE TABLE " + UserRepositoryImpl.TABLE_NAME
                + "("
                + UserRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + UserRepositoryImpl.COLUMN_USERNAME + " TEXT UNIQUE NOT NULL , "
                + UserRepositoryImpl.COLUMN_AUTHORIZATIONNUMBER + " TEXT UNIQUE NOT NULL , "
                + UserRepositoryImpl.COLUMN_PASSWORD + " TEXT NOT NULL "
                + ")";

        String victim = " CREATE TABLE " + VictimRepositoryImpl.TABLE_NAME
                + "("
                + VictimRepositoryImpl.COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
                + VictimRepositoryImpl.COLUMN_FIRSTNAME + " TEXT  NOT NULL , "
                + VictimRepositoryImpl.COLUMN_LASTNAME + " INTEGER  NOT NULL , "
                + ")";



        Log.d(TAG, "onCreate with SQL: " + cases);
        Log.d(TAG, "onCreate with SQL: " + comments);
        Log.d(TAG, "onCreate with SQL: " + suspects);
        Log.d(TAG, "onCreate with SQL: " + transfer);
        Log.d(TAG, "onCreate with SQL: " + user);
        Log.d(TAG, "onCreate with SQL: " + victim);


        db.execSQL(cases);
        db.execSQL(comments);
        db.execSQL(suspects);
        db.execSQL(transfer);
        db.execSQL(user);
        db.execSQL(victim);
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + CaseRepositoryImpl.TABLE_NAME);
        db.execSQL("drop table if exists " + CommentRepositoryImpl.TABLE_NAME);
        db.execSQL("drop table if exists " + SuspectRepositoryImpl.TABLE_NAME);
        db.execSQL("drop table if exists " + TransferRepositoryImpl.TABLE_NAME);
        db.execSQL("drop table if exists " + UserRepositoryImpl.TABLE_NAME);
        db.execSQL("drop table if exists " + VictimRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
