package com.example.suberic.earthquake;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EarthquakeMainActivity extends AppCompatActivity
        implements EarthquakeListFragment.OnListFragmentInteractionListener {


    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";
    private static final int MENU_PREFERENCES = Menu.FIRST + 1;
    private static final int SHOW_PREFERENCES = 1;

    EarthquakeListFragment mEarthquakeListFragment;
    EarthquakeViewModel earthquakeViewModel;

    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case MENU_PREFERENCES:
                Intent intent = new Intent(this, PreferencesActivity.class);
                startActivityForResult(intent, SHOW_PREFERENCES);
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_PREFERENCES, Menu.NONE, R.string.menu_settings);

        return true;
    }

    @Override
    public void onListFragmentRefreshRequested() {
        updateEarthquakes();
    }

    private void updateEarthquakes() {
        // Request the View Model update the earthquakes from the USGS feed.
        earthquakeViewModel.loadEarthquakes();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_main);

        FragmentManager fm = getSupportFragmentManager();

        // Android will automatically re-add any Fragments that
        // have previously been added after a configuration change,
        // so only add it if this isn't an automatic restart.
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction();

            mEarthquakeListFragment = new EarthquakeListFragment();
            ft.add(R.id.main_activity_frame,
                    mEarthquakeListFragment, TAG_LIST_FRAGMENT);

            ft.commitNow();
        } else {
            mEarthquakeListFragment =
                    (EarthquakeListFragment) fm.findFragmentByTag(TAG_LIST_FRAGMENT);
        }

        // Retrieve the Earthquake View Model for this Activity.
        earthquakeViewModel = ViewModelProviders.of(this)
                .get(EarthquakeViewModel.class);
    }
}
