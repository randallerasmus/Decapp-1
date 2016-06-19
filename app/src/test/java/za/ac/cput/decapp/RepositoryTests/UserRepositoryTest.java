package za.ac.cput.decapp.RepositoryTests;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import za.ac.cput.decapp.Conf.util.App;
import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;

public class UserRepositoryTest {
    private static final String TAG="USER TEST";
    private Long id;

    @Test
    public void testUserCRUD() throws Exception{
        UserRepositoryImpl repo = new UserRepositoryImpl(App.getAppContext());

        User scheduleType = new User.Builder()
                .id(id)
                .username("Jason")
                .password("Hansel and Gretel")
                .passwordConfirmation("Hansel and Gretal")
                .authorizationNumber("213/06/2016")
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
                .password("******")
                .passwordConfirmation("******")
                .authorizationNumber("213/06/2016")
                .build();
        repo.update(updateEntity);
        User newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE Username","John",newEntity.getUsername());
        Assert.assertEquals(TAG+ " UPDATE Password","*****",newEntity.getPassword());
        Assert.assertEquals(TAG+ " UPDATE PasswordConfirmation","*****",newEntity.getPasswordConfirmation());
        Assert.assertEquals(TAG+ " UPDATE AuthorizationNumber","213/06/2016",newEntity.getAuthorizationNumber());


        repo.delete(insertedEntity);
        User deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
