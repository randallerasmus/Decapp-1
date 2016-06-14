package za.ac.cput.decapp.UserInterFace;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by User on 2016/05/16.
 */
public class DecappActivity extends AppCompatActivity {

    ImageView result;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private static final int IMAGE_CAPTURE = 101;
    private Uri fileUri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_decapp);

            Button popupButton = (Button) findViewById(R.id.btnSuspects);
            Button snapButton = (Button)findViewById(R.id.btnSnapFace);

            //check if the phone has a camera
            if(!hasCamera())
                snapButton.setEnabled(false);
        }
    private boolean hasCamera() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            return true;
        } else {
            return false;
        }
    }
    // THIS IS THE METHOD THAT LAUNCH THE IMAGE CAPTURE INTENT
    //this method must correspond to the onlick method in your xml file
    // with your button or something
    public void Snap(View view) {
        File mediaFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "myPic.jpeg");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = Uri.fromFile(mediaFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        // this is also show the image up afterwards in a
        if (intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        //this is to show the image up afterwards
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap)extras.get("data");
            result.setImageBitmap(imageBitmap);
        }
        if(requestCode==IMAGE_CAPTURE)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this,"Picture has been saved to:\n"+fileUri, Toast.LENGTH_LONG).show();
            }
        }else if(resultCode==RESULT_CANCELED)
        {
            Toast.makeText(this,"Image capture Cancelled",Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(this,"Image saved to Gallery",Toast.LENGTH_LONG).show();
    }
    public void onSuspectButtonClick(View v)
            {
                if(v.getId()==R.id.btnSuspects)
                {
                    PopupMenu popupMenu = new PopupMenu(DecappActivity.this,v);

                    MenuInflater menuInflater = popupMenu.getMenuInflater();

                    popupMenu.inflate(R.menu.menu_decapp);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {


                            switch (item.getItemId())
                            {
                                case R.id.scanSuspects:
                                    Intent scanIntent = new Intent(DecappActivity.this,WantedActivity.class);
                                    startActivity(scanIntent);
                                    break;

                                case R.id.searchSuspects:
                                    Intent searchIntent = new Intent(DecappActivity.this,WantedActivity.class);
                                    startActivity(searchIntent);
                                    break;

                                case R.id.removeSuspects:
                                    Intent removeIntent = new Intent(DecappActivity.this,RemoveActivity.class);
                                    startActivity(removeIntent);
                                    break;

                                case R.id.editSuspects:
                                    Intent editIntent = new Intent(DecappActivity.this,UpdateActivity.class);
                                    startActivity(editIntent);
                                    break;


                            }
                            return false;
                        }


                    });
            }
            }
    public void onLawClick(View law)
    {
        if(law.getId()==R.id.btnLawUpdate)
        {
            Intent intent = new Intent(this,LawActivity.class);
            startActivity(intent);
        }
    }
}


