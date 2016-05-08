package za.ac.cput.decapp.Factories;



import java.sql.Blob;
import java.util.Date;

import za.ac.cput.decapp.Domain.Transfer;

/**
 * Created by User on 2016/04/24.
 */
public class TransferFactory {
    public static Transfer getTransfer (Blob pic, Date date
                                           ) {
        Transfer trnsfer = new Transfer
                .Builder(pic)
                .date(date)
                .build();

        return trnsfer;
    }
}
