package za.ac.cput.decapp.RepositoryTests;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Domain.Victim;
import za.ac.cput.decapp.Repositories.Impl.VictimRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.VictimRepository;

public class VictimRepositoryTest extends AndroidTestCase {
    private static final String TAG="VICTIM TEST";
    private Long id;

    @Test
    public void testVictimCRUD() throws Exception{
        VictimRepository repo = new VictimRepositoryImpl(this.getContext()) {
        };

        Victim scheduleType = new Victim.Builder()
                .id(id)
                .name("khayelethu")
                .surname("jones")
               .build();
        Victim insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);
        Set<Victim> Victims = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",Victims.size()>0);
       Victim entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);
        Victim updateEntity = new Victim.Builder()
                .copy(entity)
                .name("Randall")
                .surname("Eramsus")
                .build();
        repo.update(updateEntity);
        Victim newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Randall",newEntity.getName());
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Erasmus",newEntity.getSurname());
       repo.delete(insertedEntity);
        Victim deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
