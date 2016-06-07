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
       Address address = AddressFactory.getAddress("pinetree","Khayelitsha","7100");
       Assert.assertEquals("pinetree","7100",address);
   }

    @Test
    public void testUpdate()throws Exception
    {
        Address address = AddressFactory.getAddress("pinetree","Khayelitsha","7100");
        Address newAddress = new Address
                .Builder()
                .copy(address)
                .address("pinetree")
                .suburb("Khayelitsha")
                .postalCode("7100")
                .build();
        Assert.assertEquals("pinetree",newAddress.getAddress());
        Assert.assertEquals("Khayelitsha",newAddress.getSuburb());
        Assert.assertEquals("7100",newAddress.getPostalCode());
    }
}