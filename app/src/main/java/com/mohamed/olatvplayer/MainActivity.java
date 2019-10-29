package com.mohamed.olatvplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.mohamed.olatvplayer.WebServices.WebServiceConsumer;
import com.mohamed.olatvplayer.WebServices.onRawDataRecived;
import com.mohamed.olatvplayer.toolkit.M3UParser;
import com.mohamed.olatvplayer.ui.Player.ExoPlayerActivity;
import com.mohamed.olatvplayer.ui.home.HomeFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    MenuItem Home;
    MenuItem Settings;
    MenuItem About;
    DrawerLayout drawerLayout;
    Menu optionsMenu;
    Menu navigationMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
         drawerLayout = findViewById(R.id.drawer_layout);
         navigationMenu = ((NavigationView)findViewById(R.id.nav_view)).getMenu();
         navigationMenu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
             @Override
             public boolean onMenuItemClick(MenuItem item) {
                 System.out.println("Home Clicked");
                 return false;
             }
         });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLink();
            }
        });


        launchPlayerIfNeeded();
        setMainFragment();
      }



    private void setMainFragment(){
        // Create new fragment and transaction
        HomeFragment newFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
        transaction.replace(R.id.nav_host_fragment, newFragment);
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
        System.out.println("fragment swapped");
    }

    private void setLink() {
        System.out.println("setting link");
    }

    private void initActions() {
        this.Home.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                System.out.println("Home Clicked");
                return true;
            }
        });
    }

    private void initViews() {
        this.Home = optionsMenu.findItem(R.id.nav_home);
        this.Settings = optionsMenu.findItem(R.id.nav_settings);
        this.About = optionsMenu.findItem(R.id.nav_about);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        //  store the menu to var when creating options menu
        optionsMenu = menu;
        initViews();
        initActions();
        return true;
    }

    private void launchPlayerIfNeeded() {
        Intent mIntent = getIntent();
        final String videoURL = mIntent.getStringExtra("uri");
        System.out.println("videoURL:" + videoURL);
        Toast.makeText(this.getApplicationContext(), "videoURL:" + videoURL, Toast.LENGTH_LONG).show();
        if (videoURL != null) {
            if (!videoURL.isEmpty()) {
                ExoPlayerActivity.KEY_VIDEO_URI = videoURL;
                startActivity(new Intent(MainActivity.this, ExoPlayerActivity.class));
            }
        }

    }

    private void test(){
        WebServiceConsumer.getInstance().callWebService("http://forever.moppie.info:7444/get.php?username=anahsjstsgdfsscc9262_ii&password=kronivca98642aswwe&type=m3u_plus&output=ts", new onRawDataRecived() {
            @Override
            public void doWork(String rawData) {
                M3UParser parser = new M3UParser();
                parser.parseFile(rawData);
            }
        });
    }





}
