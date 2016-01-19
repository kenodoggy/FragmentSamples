package com.kenodoggy.navigationdrawer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private JSONArray mPuppiesJSONArray;

    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPuppiesJSONArray = parseJSON();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // load the first one
        if (savedInstanceState == null) {
            updateDetailContainer(0);
        }
    }

    private JSONArray parseJSON() {
        JSONArray puppiesArray = null;
        String json = null;
        InputStream is = null;

        try {
            is = getResources().getAssets().open("puppies.json");
            if (is != null) {
                int size = is.available();
                byte[] buffer = new byte[size];
                if (buffer != null) {
                    is.read(buffer);
                    json = new String(buffer, "UTF-8");

                    if (!TextUtils.isEmpty(json)) {
                        puppiesArray = new JSONArray(json);
                    }
                }
            }
        } catch (IOException e) {
            Log.e("Error", "Failed to read puppies.json");
        } catch (JSONException e) {
            Log.e("JSON Error", "Failed to create JSONArray from puppies.json data");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }

        return puppiesArray;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(mTitle);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    private void updateDetailContainer(int position) {
        // update the detail container
        JSONObject puppy = mPuppiesJSONArray.optJSONObject(position);
        Fragment fragment = DetailFragment.newInstance(puppy, position);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // do replace and commit operation
        transaction.replace(R.id.detail_container, fragment)
                .addToBackStack(null) // optional name for this back stack state, or null
                .commit();

        // update the toolbar title accordingly
        setTitle(mPuppiesJSONArray.optJSONObject(position).optString("title"));
    }


    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync state after callback to onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // make DrawerToggle aware of configuration change
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        int pos = 0;

        switch (id) {
            case R.id.nav_corgi :
                pos = 0;
                break;
            case R.id.nav_dachshund :
                pos = 1;
                break;
            case R.id.nav_golden_retriever :
                pos = 2;
                break;
            case R.id.nav_pomeranian :
                pos = 3; break;
            case R.id.nav_pug : pos = 4;
                break;
            case R.id.nav_yorkie :
                pos = 5;
                break;
            default : break;
        }

        updateDetailContainer(pos);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
