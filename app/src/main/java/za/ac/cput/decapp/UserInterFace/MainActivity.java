package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
//    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this conects java class and the layout file
        setContentView(R.layout.activity_main);

        //type casting to buttons for Actionlisteners
        Button btnSignUp =(Button)findViewById(R.id.btnSignUp);
       Button btnCancel = (Button)findViewById(R.id.btnCancel);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);

        //this also works like a charm
        btnSignUp.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnSignUp:
                Intent signUpIntent = new Intent(MainActivity.this,SignUp.class);
                startActivity(signUpIntent);

            case R.id.btnCancel:
                System.exit(0);

            case R.id.btnLogin:
                Intent decappIntent = new Intent(MainActivity.this,Decapp.class);
                startActivity(decappIntent);
        }

    }
}