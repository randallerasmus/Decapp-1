package za.ac.cput.decapp.Factories;

import za.ac.cput.decapp.Domain.Case;

import java.util.Date;

/**
 * Created by User on 2016/04/24.
 */
public class CaseFactory {
    public static Case getCase(String offense, Date date) {
        Case cas = new Case.Builder()
//                .id(id)
                .offense(offense)
                .date(new Date())
                .build();

        return cas;
    }
}
