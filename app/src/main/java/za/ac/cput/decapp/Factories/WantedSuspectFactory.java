package za.ac.cput.decapp.Factories;

import za.ac.cput.decapp.Domain.WantedSuspects;

/**
 * Created by User on 2016/06/06.
 */
public class WantedSuspectFactory {

    public static WantedSuspects getWantedSuspects (String name, String surname)
    {
        WantedSuspects wanSus = new WantedSuspects
                .Builder()
                .name(name)
                .surname(surname)
                .build();
        return wanSus;
    }

}
