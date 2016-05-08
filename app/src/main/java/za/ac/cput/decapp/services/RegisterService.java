package za.ac.cput.decapp.services;

import android.content.Context;

/**
 * Created by User on 2016/05/04.
 */
public interface RegisterService {
    void addUser (Context context, RegisterResource registerResource);
    void resetUser(Context context);
}
