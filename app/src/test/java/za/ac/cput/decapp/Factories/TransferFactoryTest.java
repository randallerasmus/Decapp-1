package za.ac.cput.decapp.Factories;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import za.ac.cput.decapp.Domain.Transfer;

/**
 * Created by User on 2016/05/05.
 */
//.THIS IS THE CLASS THAT IM STRUGGLING WITH
public class TransferFactoryTest  {

        @Test
        public void testCreateTransfer() throws Exception {


        }
        @Test
        public void testUpdate () throws Exception
        {
//            Transfer transfer = TransferFactory.getTransfer(Pic.jpeg,new Date(8-12-2016));
            Transfer newTransfer = new Transfer
                    .Builder()
//                    .copy(transfer)
//                    .pic(Pic.jpeg)
                    .date(new Date(8-12-2016))
                    .build();

//            Assert.assertEquals(Pic.jpeg,newTransfer.getPic());
            Assert.assertEquals(8-12-2016,newTransfer.getDate());
        }
    }
