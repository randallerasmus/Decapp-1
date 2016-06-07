package za.ac.cput.decapp.UserInterFace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by User on 2016/05/16.
 */
public class Decapp extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decapp);

            Button popupButton =(Button)findViewById(R.id.btnSuspects);
            // this here has my suspects popup menu
            popupButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(getApplicationContext(),v);

                    MenuInflater menuInflater = popupMenu.getMenuInflater();

                    popupMenu.inflate(R.menu.menu_decapp);
                    popupMenu.show();

                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener()
                    {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            return false;
                        }
                    });
                }
            });
    }

}
