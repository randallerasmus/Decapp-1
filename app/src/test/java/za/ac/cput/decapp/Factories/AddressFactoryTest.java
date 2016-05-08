package za.ac.cput.decapp.Factories;

import junit.framework.Assert;

import org.junit.Test;

import za.ac.cput.decapp.Domain.Address;

/**
 * Created by User on 2016/05/05.
 */
public class AddressFactoryTest
{
   @Test
    public void testCreate ()throws Exception
   {
       Address address = AddressFactory.getAddress("pinetree","7100");
       Assert.assertEquals("pinetree","7100");
   }

    @Test
    public void testUpdate()throws Exception
    {
        Address Address = AddressFactory.getAddress("pinetree","7100");
        Address newAddress = new Address
                .Builder()
                .copy(Address)
                .address("pinetree")
                .build();
        Assert.assertEquals("pinetree",newAddress.getAddress());
        Assert.assertEquals("7100",newAddress.getPostalCode());
    }
}