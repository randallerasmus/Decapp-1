package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.WantedSuspects;

/**
 * Created by User on 2016/05/05.
 */
public class WantedActivitySuspectFactoryTest {

        @Test
        public void testCreateWantedSuspect() throws Exception {

            WantedSuspects WantedSuspects = WantedSuspectFactory.getWantedSuspects("Johnny","Bravo");
            Assert.assertEquals("Huge nose","Bravo",WantedSuspects);
        }
        @Test
        public void testUpdate () throws Exception
        {
            WantedSuspects wantedSuspects = WantedSuspectFactory.getWantedSuspects("Huge nose","Bravo");
            WantedSuspects newWantedSuspects = new WantedSuspects
                    .Builder()
                    .copy(wantedSuspects)
                    .name("Huge Nose")
                    .surname("Bravo")
                    .build();

            Assert.assertEquals("Johnny",newWantedSuspects.getName());
            Assert.assertEquals("Depp",newWantedSuspects.getSurname());
        }
    }
