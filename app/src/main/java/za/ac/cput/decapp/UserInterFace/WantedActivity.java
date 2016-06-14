package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class WantedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wanted);
    }

    public void onBtnBackClick(View v)
    {
        if(v.getId()==R.id.btnBack)
        {
            Intent intent = new Intent(this,DecappActivity.class);
            startActivity(intent);}

        }

}
