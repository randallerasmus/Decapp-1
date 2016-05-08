package za.ac.cput.decapp.Repositories.Impl;

/**
 * Created by User on 2016/05/04.
 */
public class TransferRepositoryImpl {
    public static final String TABLE_NAME = "Transfer";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TransferID = "TransferId";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_TransferIMAGE = "TransferImage";
    public static final String COLUMN_SYMBOLIMAGE = "symbolImage";
    public static final String COLUMN_ELECTIONTYPEID = "electionTypeId";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TransferID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_TransferIMAGE + " BLOB , "
            + COLUMN_SYMBOLIMAGE + " BLOB , "
            + COLUMN_ELECTIONTYPEID + " TEXT NOT NULL );";


    public TransferRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
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
                        COLUMN_TransferID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_TransferIMAGE,
                        COLUMN_SYMBOLIMAGE,
                        COLUMN_ELECTIONTYPEID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Transfer Transfer = new Transfer.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .symbolImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_SYMBOLIMAGE)))
                    .firstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .TransferImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_TransferIMAGE)))
                    .TransferId(cursor.getString(cursor.getColumnIndex(COLUMN_TransferID)))
                    .electionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTIONTYPEID)))
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
        values.put(COLUMN_FIRSTNAME, entity.getFirstname());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_TransferIMAGE, entity.getTransferImage());
        values.put(COLUMN_SYMBOLIMAGE, entity.getSymbolImage());
        values.put(COLUMN_ELECTIONTYPEID, entity.getElectionTypeId());
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
        values.put(COLUMN_FIRSTNAME, entity.getFirstname());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_TransferIMAGE, entity.getTransferImage());
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
                        .symbolImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_SYMBOLIMAGE)))
                        .firstname(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .TransferImage(cursor.getBlob(cursor.getColumnIndex(COLUMN_TransferIMAGE)))
                        .TransferId(cursor.getString(cursor.getColumnIndex(COLUMN_TransferID)))
                        .electionTypeId(cursor.getString(cursor.getColumnIndex(COLUMN_ELECTIONTYPEID)))
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
}

}
