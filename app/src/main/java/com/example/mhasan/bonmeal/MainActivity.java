package com.example.mhasan.bonmeal;


import android.app.SearchManager;

import android.content.res.Configuration;

import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.color.black;


public class MainActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        String mActivityTitle = getTitle().toString();
        getSupportActionBar().setTitle(mActivityTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        addDrawerItems();
        setupDrawer();
        setViewPager();


    }

    public void addDrawerItems() {
        String[] osArray = {"Android", "iOS", "Windows", "OS X", "Linux"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Call when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //  getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // getSupportActionBar().setTitle(mActivityTitle);
                //  ActionBar actionBar = getSupportActionBar();

                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.getDrawerArrowDrawable().setColor(getColor(black));
        mDrawerLayout.addDrawerListener(mDrawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    private void setViewPager() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final CategoryAdapter categoryAdapter = new CategoryAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(categoryAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cookchallenge);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_cookbook);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_fridge);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_recipes);
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        //   int tabIconColor = ContextCompat.getColor(this, R.color.tabSelectedIconColor);
                        int position = tab.getPosition();
                        switch (position) {
                            case 0:
                                TextView tabOne = (TextView) LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_tab, null);
                                tabOne.setText(R.string.kitchen_challenge);
                                tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_cookchallenge, 0, 0);
                                tabLayout.getTabAt(0).setCustomView(tabOne);

                                break;
                            case 1:
                                                               Log.d("onTabSelected: ", Integer.toString(position));
                                tab.setText(R.string.cook_book);
                                break;
                            case 2:
                                Log.d("onTabSelected: ", Integer.toString(position));
                                tab.setText(R.string.fridge);
                                break;
                            case 3:
                                Log.d("onTabSelected: ", Integer.toString(position));
                                tab.setText(R.string.my_recipes);
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
//                        int tabIconColor = ContextCompat.getColor(this, R.color.tabUnselectedIconColor);
//                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

}
