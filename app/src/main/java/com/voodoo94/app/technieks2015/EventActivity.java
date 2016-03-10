package com.voodoo94.app.technieks2015;

import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class EventActivity extends ActionBarActivity {

    private NetworkUtility mNet;
    private android.support.v7.app.ActionBar mActionbar;
    public static Typeface cb;
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        cb = Typeface.createFromAsset(getAssets(), "fonts/corbert.ttf");

        mActionbar = getSupportActionBar();
        mActionbar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_LIST);
        mActionbar.setDisplayShowTitleEnabled(false);
        mActionbar.show();

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_dropdown_item);

        android.support.v7.app.ActionBar.OnNavigationListener mOnNavigationListener = new android.support.v7.app.ActionBar.OnNavigationListener() {
            String[] strings = getResources().getStringArray(R.array.action_list);

            @Override
            public boolean onNavigationItemSelected(int position, long itemId) {
                switch (position) {
                    case 0: {
                        android.support.v4.app.Fragment mFragmentSigEvents = new SigEventFragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frag_container, mFragmentSigEvents, strings[position]);
                        ft.commit();

                        break;
                    }
                    case 1: {
                        android.support.v4.app.Fragment mFragmentDay1 = new Day1Fragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frag_container, mFragmentDay1, strings[position]);
                        ft.commit();

                        break;
                    }
                    case 2: {
                        android.support.v4.app.Fragment mFragmentDay2 = new Day2Fragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frag_container, mFragmentDay2, strings[position]);
                        ft.commit();
                        break;
                    }
                    case 3: {
                        android.support.v4.app.Fragment mFragmentDay3 = new Day3Fragment();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.frag_container, mFragmentDay3, strings[position]);
                        ft.commit();

                        break;
                    }
                }
                return true;
            }
        };

        mActionbar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
