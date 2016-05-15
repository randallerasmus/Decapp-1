package za.ac.cput.decapp.Repositories.Impl;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import za.ac.cput.decapp.Conf.databases.DBConstants;
import za.ac.cput.decapp.Domain.Case;
import za.ac.cput.decapp.Repositories.CaseRepository;

/**
 * Created by User on 2016/05/04.
 */
public class CaseRepositoryImpl  extends SQLiteOpenHelper implements CaseRepository
{
    public static final String DATABASE_NAME = "case.db";
    public static final String TABLE_NAME = "case";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_OFFENSE = "offense";
    public static final String COLUMN_DATE = "date";

    private SQLiteDatabase db;


    public CaseRepositoryImpl (Context context)
    {
        super (context, DBConstants.DATABASE_NAME,null,DBConstants.DATABASE_VERSION);
    }

    public void open()throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void close()
    {this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("CREATE TABLE"+ TABLE_NAME + "("+ COLUMN_ID +"INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_OFFENSE + "OFFENSE TEXT ,"+ COLUMN_DATE + "DATE NOT NULL FORMAT 'YYYY-MM-DD');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXIST" + TABLE_NAME);
        onCreate(db);
    }

   }
