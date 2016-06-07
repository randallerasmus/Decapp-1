package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Blob;
import java.sql.Date;

import za.ac.cput.decapp.Domain.Transfer;

/**
 * Created by User on 2016/05/05.
 */
//.THIS IS THE CLASS THAT IM STRUGGLING WITH
public class TransferFactoryTest  {

        @Test
        public void testCreateTransfer() throws Exception {

            Transfer Transfer = TransferFactory.getTransfer(Pic.jpeg,new Date(8-12-2016));
            Assert.assertEquals(Pic.jpeg,new Date(8-12-2016),Transfer);
        }
        @Test
        public void testUpdate () throws Exception
        {
            Transfer transfer = TransferFactory.getTransfer(Pic.jpeg,new Date(8-12-2016));
            Transfer newTransfer = new Transfer
                    .Builder()
                    .copy(transfer)
                    .pic(Pic.jpeg)
                    .date(new Date(8-12-2016))
                    .build();

            Assert.assertEquals(Pic.jpeg,newTransfer.getPic());
            Assert.assertEquals(8-12-2016,newTransfer.getDate());
        }
    }
