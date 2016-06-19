package za.ac.cput.decapp.RepositoryTests;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Domain.Case;
import za.ac.cput.decapp.Repositories.Impl.CaseRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.CaseRepository;

public class CaseRepositoryTests extends AndroidTestCase {
    private static final String TAG="Case TEST";
    private Long id;
    CaseRepository repo = new CaseRepositoryImpl(null);
    @Test
    public void testCaseCRUD() throws Exception{
         {
        };
      Case aCase = new Case.Builder()
                .id(id)
                .offense("Robbery")
                .offenseLocation("Cape Town")
                .build();
        Case insertedEntity = repo.save(aCase);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);


        Set<Case> Cases = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",Cases.size()>0);


        Case entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        Case updateEntity = new Case.Builder()
                .copy(entity)
                .offense("Murder")
                .build();
        repo.update(updateEntity);
        Case newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Armed Robbery",newEntity.getOffense());

        repo.delete(insertedEntity);
        Case deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
