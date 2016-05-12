package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
//    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this conects java class and the layout file
        setContentView(R.layout.activity_main);
        Button btnSignUp =(Button)findViewById(R.id.btnSignUp);
        Button btnCancel = (Button)findViewById(R.id.btnCancel);

        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            public void onClick (View v)
            {
                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                startActivity(intent);
            }


        });
        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                System.exit(0);
            }
        });
    }
}