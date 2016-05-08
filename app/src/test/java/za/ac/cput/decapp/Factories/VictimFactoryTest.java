package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import za.ac.cput.decapp.Domain.Victim;

/**
 * Created by User on 2016/05/05.
 */
public class VictimFactoryTest {



        @Test
        public void testCreateVictim() throws Exception {

            Victim Victim = VictimFactory.getVictim("Johnny","Bravo");
            Assert.assertEquals("Huge nose","Bravo");
        }
        @Test
        public void testUpdate () throws Exception
        {
            Victim Victim = VictimFactory.getVictim("Huge nose","Bravo");
            Victim newVictim = new Victim
                    .Builder()
                    .name("Huge Nose")
                    .surname("Bravo")
                    .build();

            Assert.assertEquals("Johnny",newVictim.getName());
            Assert.assertEquals("Bravo",newVictim.getSurname());
        }
    }
