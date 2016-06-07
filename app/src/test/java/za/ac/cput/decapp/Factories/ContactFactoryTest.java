package za.ac.cput.decapp.Factories;

import org.junit.Assert;
import org.junit.Test;

import za.ac.cput.decapp.Domain.Contact;

/**
 * Created by User on 2016/05/05.
 */
public class ContactFactoryTest  {

        @Test
        public void testCreateContact() throws Exception {

            Contact Contact = ContactFactory.getContact("0813355742","0219041958");
            Assert.assertEquals("0813355278","0219021452",Contact);
        }
        @Test
        public void testUpdate () throws Exception
        {
            Contact contact = ContactFactory.getContact("111111111","111111111");
            Contact newContact = new Contact
                    .Builder()
                    .copy(contact)
                    .cellphone("02134567")
                    .phoneNumber("021345698")
                    .build();

            Assert.assertEquals("012394567",newContact.getCellphone());
            Assert.assertEquals("111111111",newContact.getPhoneNumber());
        }
    }
