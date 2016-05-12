package za.ac.cput.decapp.UserInterFace;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import za.ac.cput.decapp.R;

/**
 * Created by User on 2016/05/12.
 */
public class SignUp extends MainActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_signup);
    }
}
