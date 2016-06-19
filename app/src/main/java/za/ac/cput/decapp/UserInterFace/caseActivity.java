package za.ac.cput.decapp.UserInterFace;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import za.ac.cput.decapp.Repositories.Impl.CaseRepositoryImpl;

public class CaseActivity extends AppCompatActivity  {

    CaseRepositoryImpl myDb;
    EditText editOffenses,editTextLocation;
    Button addCasebutton;
    Context context;
    CaseRepositoryImpl caseRepositoryimpl;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        myDb = new CaseRepositoryImpl(this);


        editOffenses = (EditText) findViewById(R.id.editOffense);
        editTextLocation = (EditText) findViewById(R.id.editOffenseLocation);
        addCasebutton = (Button) findViewById(R.id.btnAddCase);

    }

    public void onAddCaseButtonClick(View v)
    {
        if(v.getId()==R.id.btnAddCase)
        {

            String offenses = editOffenses.getText().toString();
            String offenseLocation = editTextLocation.getText().toString();

           caseRepositoryimpl = new CaseRepositoryImpl(context);
            caseRepositoryimpl.open();




        }
    }

    }

