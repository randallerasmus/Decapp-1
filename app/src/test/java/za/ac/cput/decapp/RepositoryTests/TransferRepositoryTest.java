package za.ac.cput.decapp.RepositoryTests;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.Transfer;
import za.ac.cput.decapp.Repositories.Impl.TransferRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.TransferRepository;

public class TransferRepositoryTest extends AndroidTestCase {
    private static final String TAG="TRANSFER TEST";
    private Long id;

    @Test
    public void testTransferCRUD() throws Exception{
        TransferRepository repo = new TransferRepositoryImpl(App.getAppContext());

        Transfer upload = new Transfer.Builder()
                .id(id)
                .TransferId("Jason")
                .build();

        Transfer insertedEntity = repo.save(upload);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Transfer> Transfers = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",Transfers.size()>0);

        //READ ENTITY
        Transfer entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Transfer updateEntity = new Transfer.Builder()
                .copy(entity)
                .TransferId("12")
                .build();
        repo.update(updateEntity);
        Transfer newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","12",newEntity.getTransferId());

        // DELETE ENTITY
        //repo.delete(updateEntity);
        repo.delete(insertedEntity);
        Transfer deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
