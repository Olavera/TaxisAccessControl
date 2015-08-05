package com.iecisa.taxisaccesscontrol.views.activities;

import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

import com.iecisa.taxisaccesscontrol.R;
import com.iecisa.taxisaccesscontrol.views.VehicleDetailsFragment;
import com.iecisa.taxisaccesscontrol.views.fragments.BlacklistFragment;
import com.iecisa.taxisaccesscontrol.views.fragments.FragmentCallbacks;
import com.iecisa.taxisaccesscontrol.views.utils.Constants;
import com.iecisa.taxisaccesscontrol.views.utils.Sections;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
        implements FragmentCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.inject(this);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer, mDrawerLayout);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSectionAttached(String title) {
        mTitle = title;
    }

    @Override
    public void switchFragment(Sections section, Bundle bundle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = null;
        switch(section){
            case BLACKLIST_FRAGMENT:
                fragment = BlacklistFragment.newInstance();
                break;
            case VEHICLE_DETAILS_FRAGMENT:
                long vehicleId = bundle.getLong(Constants.VEHICLE_ID, -1);
                if(vehicleId!=-1) {
                    //TODO: Completar llamada a detalles de vehiculo
                    //fragment = VehicleDetailsFragment.newInstance(vehicleId);
                }
                break;
            default:
                break;
        }
        if(fragment!=null){
            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
        }
    }

}
