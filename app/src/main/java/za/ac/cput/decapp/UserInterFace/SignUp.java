package za.ac.cput.decapp.UserInterFace;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by User on 2016/05/12.
 */
public class SignUp extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_signup);
    }
    public void onSignUpClick(View v)
    {
        if(v.getId()==R.id.btnSignUp)
        {
            EditText uname = (EditText)findViewById(R.id.editTx_name);
            EditText pass1 = (EditText)findViewById(R.id.editTxt_pass1);
            EditText pass2 = (EditText)findViewById(R.id.editTxt_pass2);
            EditText email = (EditText)findViewById(R.id.editTxt_email);

            //this convert the value to string values
        String unamestr = uname.getText().toString(); //this is how you fetch the text from editText
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        String namestr = email.getText().toString();

            // this is to test that the passwords match
            if (!pass1str.equals(pass2str))
            {
                // this test to
                Toast pass = Toast.makeText(SignUp.this,"Passwords Dont Match",Toast.LENGTH_SHORT);
                pass.show();
            }
        }
    }
}
