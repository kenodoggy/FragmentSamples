package com.kenodoggy.puppiesviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private String[] mPuppyTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPuppyTitles = getResources().getStringArray(R.array.puppies_array);

        initViewPager();
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        /* add the Fragments to the ViewPagerAdapter */
        int index = 0;
        for(String title : mPuppyTitles) {
            adapter.addFrag(PuppyFragment.newInstance(index++), title);
        }

        viewPager.setAdapter(adapter);
    }

}
