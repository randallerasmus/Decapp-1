package za.ac.cput.decapp.services;

import android.content.Context;

/**
 * Created by User on 2016/05/04.
 */
public interface LawService {
    void addLaw (Context context,LawResource lawResource);
    void resetLaw(Context context);
}
