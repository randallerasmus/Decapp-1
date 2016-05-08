package za.ac.cput.decapp.Factories;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.junit.Test;

import java.util.Date;


import za.ac.cput.decapp.Domain.Case;

/**
 * Created by User on 2016/05/05.
 */
public class CaseFactoryTest  {

    @Test
    public void testCreate ()throws Exception
    {
        Case Case = CaseFactory.getCase("ArmedRobbery",new Date());
        Assert.assertEquals("ArmedRobbery",new Date());
    }

    @Test
    public void testUpdate()throws Exception
    {
        Case Case = CaseFactory.getCase("ArmedRobbery",new Date());
        Case newCase = new Case
                .Builder()
                .offense("Robbery")
                .date(new Date())
                .build();
        Assert.assertEquals("Robbery",newCase.getOffense());
        Assert.assertEquals("Sun May 08 02:33",newCase.getDate());
    }
}