package com.voodoo94.app.technieks2015;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;


public class SplashActivity extends ActionBarActivity {

    private android.support.v7.app.ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        //getActionBar().hide();
        mActionBar = getSupportActionBar();
        if (mActionBar != null)
            mActionBar.hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        MediaPlayer Intro = MediaPlayer.create(this, R.raw.bg);
        Intro.start();

        Thread timer = new Thread(){
            public void run() {
                try {
                    sleep(1800);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
    public void onPause() {
        super.onPause();

        finish();
    }
}
