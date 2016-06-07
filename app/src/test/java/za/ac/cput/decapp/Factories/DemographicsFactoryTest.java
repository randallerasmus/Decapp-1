package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.Demographics;

/**
 * Created by User on 2016/05/05.
 */
public class DemographicsFactoryTest  {

          @Test
        public void testCreateDemographics() throws Exception {

            Demographics DemoGraphics = DemographicsFactory.getDemographics("Black","Male","43");
            Assert.assertEquals("Black","Male",DemoGraphics);
        }
        @Test
        public void testUpdate () throws Exception
        {
            Demographics demographics = DemographicsFactory.getDemographics("Black","Male","30");
            Demographics newDemographics = new Demographics
                    .Builder()
                    .copy(demographics)
                    .race("Black")
                    .gender("Female")
                    .age("25")
                    .build();

            Assert.assertEquals("Black",newDemographics.getRace());
            Assert.assertEquals("Male",newDemographics.getGender());
           Assert.assertEquals("30",newDemographics.getAge());
        }
    }
