package za.ac.cput.decapp.Factories;



import java.util.Date;

import za.ac.cput.decapp.Domain.Demographics;

/**
 * Created by User on 2016/04/24.
 */
public class DemographicsFactory {
    public static Demographics getDemographics(String race, String gender,
                                               Date dob)
    {
        Demographics demogr = new Demographics
                .Builder(race)
                .gender(gender)
                .dob(dob)
                .build();

        return demogr;
    }
}
