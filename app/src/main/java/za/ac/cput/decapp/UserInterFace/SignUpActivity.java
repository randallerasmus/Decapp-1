package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import za.ac.cput.decapp.Domain.User;
import za.ac.cput.decapp.Repositories.Impl.UserRepositoryImpl;

/**
 * Created by User on 2016/05/12.
 */
public class SignUpActivity extends AppCompatActivity
{
    UserRepositoryImpl userRepositoryImpl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userRepositoryImpl = new UserRepositoryImpl(this);
//       userRepositoryImpl = userRepositoryImpl.open();
    }
    public void onSignUpClick(View v)
    {
        if(v.getId()==R.id.btnSignUp)
        {
            EditText uname = (EditText)findViewById(R.id.editTx_name);
            EditText pass1 = (EditText)findViewById(R.id.editTxt_pass1);
            EditText pass2 = (EditText)findViewById(R.id.editTxt_pass2);
            EditText email = (EditText)findViewById(R.id.editTxt_authorizationNumber);

            //this convert the value to string values
        String unamestr = uname.getText().toString(); //this is how you fetch the text from editText
        String pass1str = pass1.getText().toString();
        String pass2str = pass2.getText().toString();
        String emailstr = email.getText().toString();

            // this is to test that the passwords match
            if (!pass1str.equals(pass2str))
            {
                // this test to
                Toast pass = Toast.makeText(SignUpActivity.this,"Passwords Dont Match",Toast.LENGTH_SHORT);
                pass.show();
            } else if(pass1str.equals(pass2str))
            {
                userRepositoryImpl.save(new User());
                Toast pass = Toast.makeText(SignUpActivity.this,"SignUpActivity Successfull",Toast.LENGTH_SHORT);
                pass.show();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
