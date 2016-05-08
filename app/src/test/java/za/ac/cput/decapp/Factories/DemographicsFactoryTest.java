package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.sql.Date;

import za.ac.cput.decapp.Domain.Demographics;

/**
 * Created by User on 2016/05/05.
 */
public class DemographicsFactoryTest  {

          @Test
        public void testCreateDemographics() throws Exception {

            Demographics Demographics = DemographicsFactory.getDemographics("Black","Male",new Date(2015-12-11));
            Assert.assertEquals("Black","Male",new Date(11-11-11));
        }
        @Test
        public void testUpdate () throws Exception
        {
            Demographics Demographics = DemographicsFactory.getDemographics("Black","Male",new Date(12-12-12));
            Demographics newDemographics = new Demographics
                    .Builder()
                   .race("Black")
                    .gender("Female")
                   .dob(new Date(12-12-2016))
                    .build();

            Assert.assertEquals("Black",newDemographics.getRace());
            Assert.assertEquals("Male",newDemographics.getGender());
           Assert.assertEquals(2015-12-10,newDemographics.getDob());
        }
    }
