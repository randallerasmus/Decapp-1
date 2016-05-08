package za.ac.cput.decapp.Factories;


import za.ac.cput.decapp.Domain.Suspect;

/**
 * Created by User on 2016/04/24.
 */
public class SuspectFactory {

    public static Suspect getSuspect (String name, String surname
                                      )
    {
       Suspect suspct = new Suspect
                .Builder()
                .surname(surname)
                .name(name)
                .build();

        return suspct;
    }
}
