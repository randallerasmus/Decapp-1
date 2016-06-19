package za.ac.cput.decapp.RepositoryTests;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.Suspect;
import za.ac.cput.decapp.Repositories.Impl.SuspectRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.SuspectRepository;

public class SuspectRepositoryTest extends TestCaseSuite {
    private static final String TAG="SUSPECT TEST";
    private Long id;

    @Test
    public void testSuspectCRUD() throws Exception{
        SuspectRepository repo = new SuspectRepositoryImpl(App.getAppContext());
        Suspect suspectInfo = new Suspect.Builder()
                .id(id)
                .name("Roller")
                .surname("Coaster")
                .build();
        Suspect insertedEntity = repo.save(suspectInfo);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);


        Set<Suspect> Suspects = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",Suspects.size()>0);


        Suspect entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        Suspect updateEntity = new Suspect.Builder()
                .copy(entity)
                .name("Sir Alex")
                .surname("jones")
                .build();
        repo.update(updateEntity);
        Suspect newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Alex",newEntity.getSurname());
       repo.delete(insertedEntity);
        Suspect deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
