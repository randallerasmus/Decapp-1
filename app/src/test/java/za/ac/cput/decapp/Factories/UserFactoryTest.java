package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import za.ac.cput.decapp.Domain.User;

/**
 * Created by User on 2016/05/05.
 */
public class UserFactoryTest {



        @Test
        public void testCreateUser() throws Exception {

            User User = UserFactory.getUser("The Hacker","randallerasmus1@gmail.com","******","213/06/2016");
            Assert.assertEquals("The Hacker",User);
        }
        @Test
        public void testUpdate () throws Exception
        {
            User User = UserFactory.getUser("The Hacker","randallerasmus1@gmail.com","******","213/06/2016");
            User newUser = new User
                    .Builder()
                    .screenName("Olga")
                    .email("randallerasmus1@gmail.com")
                    .password("******")
                    .obNumber("213/06/2016")
                    .build();

            Assert.assertEquals("The Hacker",newUser.getScreenName());
            Assert.assertEquals("randallerasmus1@gmail.com",newUser.getEmail());
            Assert.assertEquals("******",newUser.getPassword());
            Assert.assertEquals("213/04/2016",newUser.getobNumber());
        }
    }
