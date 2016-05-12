package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import za.ac.cput.decapp.R;

public class MainActivity extends AppCompatActivity
{
//    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this conects java class and the layout file
        setContentView(R.layout.activity_main);
    }

    public void loginMethod(View view) {
        String button_text;
        button_text = ((Button) view).getText().toString();
        if (button_text.equals("Sign up"))
        {
            Intent intent = new Intent(this,SignUp.class);
            startActivity(intent);
        }
            else if (button_text.equals("Login"))
        {
            Intent intent = new Intent(this,caseActivity.class);
            startActivity(intent);
        }
    }
}