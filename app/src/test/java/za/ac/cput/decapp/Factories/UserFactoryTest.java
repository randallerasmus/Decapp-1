package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.User;

/**
 * Created by User on 2016/05/05.
 */
public class UserFactoryTest {



        @Test
        public void testCreateUser() throws Exception {

            User user = UserFactory.getUser("The Hacker","******","******","213/06/2016");
            Assert.assertEquals("The Hacker",user.getUsername());
            Assert.assertEquals("******",user.getPassword());
            Assert.assertEquals("******",user.getPasswordConfirmation());
            Assert.assertEquals("213/06/2016",user.getAuthorizationNumber());
        }
        @Test
        public void testUpdate () throws Exception
        {
            User user = UserFactory.getUser("The Hacker","******","******","213/06/2016");
            User newUser = new User
                    .Builder()
                    .copy(user)
                    .username("Olga")
                     .password("******")
                    .passwordConfirmation("******")
                    .authorizationNumber("213/06/2016")
                    .build();

            Assert.assertEquals("Olga",newUser.getUsername());
            Assert.assertEquals("******",newUser.getPassword());
            Assert.assertEquals("******",newUser.getPasswordConfirmation());
            Assert.assertEquals("213/06/2016",newUser.getAuthorizationNumber());
        }
    }
