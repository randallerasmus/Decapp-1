package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.Case;

/**
 * Created by User on 2016/05/05.
 */
public class CaseFactoryTest  {

    @Test
    public void testCreate ()throws Exception
    {
        Case aCase = CaseFactory.getCase(null,"ArmedRobbery","Harare");
        Assert.assertEquals("ArmedRobbery","Harare",aCase);
    }

    @Test
    public void testUpdate()throws Exception
    {
        Case Case = CaseFactory.getCase(null,"ArmedRobbery","Cape Town");
        Case newCase = new Case
                .Builder()
                .copy(Case)
                .offense("Robbery")
                .offenseLocation("Lesotho")
                .build();
        Assert.assertEquals("Robbery",newCase.getOffense());
        Assert.assertEquals("Lesotho",newCase.getOffenseLocation());
    }
}