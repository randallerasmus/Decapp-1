package za.ac.cput.decapp.Factories;

import org.junit.Assert;
import org.junit.Test;

import za.ac.cput.decapp.Domain.Suspect;

/**
 * Created by User on 2016/05/05.
 */
public class SuspectFactoryTest {


        @Test
        public void testCreateSuspect() throws Exception {

            Suspect Suspect = SuspectFactory.getSuspect("Randall","Erasmus");
            Assert.assertEquals("Randall","Erasmus",Suspect);
        }
        @Test
        public void testUpdate () throws Exception
        {
            Suspect Suspect = SuspectFactory.getSuspect("Randall","Erasmus");
            Suspect newSuspect = new Suspect
                    .Builder()
                    .copy(Suspect)
                    .name("Randall")
                    .surname("Erasmus")
                    .build();

            Assert.assertEquals("Randall",newSuspect.getName());
            Assert.assertEquals("Erasmus",newSuspect.getSurname());
        }
    }
