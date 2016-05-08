package za.ac.cput.decapp.Repositories.Impl;

/**
 * Created by User on 2016/05/04.
 */
public class UserRepositoryImpl {
    public static final String TABLE_NAME = "User";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_UserID = "UserId";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_UserIMAGE = "UserImage";
    public static final String COLUMN_SYMBOLIMAGE = "symbolImage";
    public static final String COLUMN_ELECTIONTYPEID = "electionTypeId";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_UserID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_UserIMAGE + " BLOB , "
            + COLUMN_SYMBOLIMAGE + " BLOB , "
            + COLUMN_ELECTIONTYPEID + " TEXT NOT NULL );";


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
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_UserIMAGE,
                        COLUMN_SYMBOLIMAGE,
                        COLUMN_ELECTIONTYPEID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final User User = new User.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .symbolImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_SYMBOLIMAGE)))
                    .firstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .UserImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_UserIMAGE)))
                    .UserId(cursor.getString(cursor.getColumnIndex(COLUMN_UserID)))
                    .electionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTIONTYPEID)))
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
        values.put(COLUMN_FIRSTNAME, entity.getFirstname());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_UserIMAGE, entity.getUserImage());
        values.put(COLUMN_SYMBOLIMAGE, entity.getSymbolImage());
        values.put(COLUMN_ELECTIONTYPEID, entity.getElectionTypeId());
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
        values.put(COLUMN_FIRSTNAME, entity.getFirstname());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_UserIMAGE, entity.getUserImage());
        values.put(COLUMN_SYMBOLIMAGE, entity.getSymbolImage());
        values.put(COLUMN_ELECTIONTYPEID, entity.getElectionTypeId());
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
                        .symbolImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_SYMBOLIMAGE)))
                        .firstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .UserImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_UserIMAGE)))
                        .UserId(cursor.getString(cursor.getColumnIndex(COLUMN_UserID)))
                        .electionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTIONTYPEID)))
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
