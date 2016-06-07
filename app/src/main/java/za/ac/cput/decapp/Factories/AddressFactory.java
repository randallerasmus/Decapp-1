package za.ac.cput.decapp.Factories;


import za.ac.cput.decapp.Domain.Address;

/**
 * Created by User on 2016/04/24.
 */
public class AddressFactory {
    public static Address getAddress(String address,String postalCode,String suburb) {
        Address addrs = new Address.Builder()
                .address(address)
                .postalCode(postalCode)
                .suburb(suburb)
                .build();

        return addrs;
    }

    
}