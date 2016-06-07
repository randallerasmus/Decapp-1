package za.ac.cput.decapp.RepositoryTests;

import android.test.AndroidTestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;
import za.ac.cput.decapp.Repositories.Interfaces.UserRepository;

public class UserRepositoryTest extends AndroidTestCase {
    private static final String TAG="USER TEST";
    private Long id;

    @Test
    public void testUserCRUD() throws Exception{
        UserRepository repo = new UserRepositoryImpl(this.getContext());

        User scheduleType = new User.Builder()
                .id(id)
                .username("Jason")
                .password("Hansel and Gretel")
                .build();
        User insertedEntity = repo.save(scheduleType);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);


        Set<User> users = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",users.size()>0);

        User entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);


        User updateEntity = new User.Builder()
                .copy(entity)
                .username("John")
                .password("*******")
                .build();
        repo.update(updateEntity);
        User newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","John",newEntity.getUsername());
        Assert.assertEquals(TAG+ " UPDATE ENTITY","*****",newEntity.getPassword());


        repo.delete(insertedEntity);
        User deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
