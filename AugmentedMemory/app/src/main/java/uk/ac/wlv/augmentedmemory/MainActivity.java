package uk.ac.wlv.augmentedmemory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.view.View;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logoImage = (ImageView) findViewById(R.id.logoImage);
        ImageView settingsImage = (ImageView) findViewById(R.id.settingsImage);

        int logoResource = getResources().getIdentifier("@drawable/logo", null, this.getPackageName());
        logoImage.setImageResource(logoResource);

        int settingsResource = getResources().getIdentifier("@drawable/ic_settings", null, this.getPackageName());
        settingsImage.setImageResource(settingsResource);

        settingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
            }
        });

        mBottomNavigationView=findViewById(R.id.bottomNavigation);

        mBottomNavigationView.setOnNavigationItemSelectedListener(bottomMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomMethod=new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    Fragment fragment=null;

                    switch (item.getItemId()){
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.reminderList:
                            fragment = new ReminderListFragment();
                            break;

                        case R.id.map:
                            fragment = new MapFragment();
                            break;

                        case R.id.contacts:
                            fragment = new ContactsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();

                    return true;
                }
            };
}