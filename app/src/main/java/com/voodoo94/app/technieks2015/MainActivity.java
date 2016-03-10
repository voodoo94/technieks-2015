package com.voodoo94.app.technieks2015;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private WebView mWebView;
    private String mTwitterData = "http://technieks.com/data/twitter.html";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        if (getActionBar() != null)
            getActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkUtility mNet = new NetworkUtility();

        if (mNet.is_connected(getApplication())) {

            Toast.makeText(getApplication(), "Touch the TechNIEks 2015 logo to enter", Toast.LENGTH_LONG).show();

            mWebView = (WebView) findViewById(R.id.webview_main);
            mWebView.getSettings().setJavaScriptEnabled(true);

            mWebView.loadUrl(mTwitterData);
        } else {
            Toast.makeText(getApplication(), "No Internet Connection...", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void eventButtonClicked(View view) {
        Intent mEventActivtyIntent = new Intent(this, EventActivity.class);

        startActivity(mEventActivtyIntent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void sponsersButtonClicked(View view) {
        Intent mSponsersIntent = new Intent(this, SponsersActivity.class);

        startActivity(mSponsersIntent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void aboutButtonClicked(View view) {
        Intent mAboutIntent = new Intent(this, AboutActivity.class);

        startActivity(mAboutIntent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void galleryButtonClicked(View view) {
        Intent mGalleryIntent = new Intent(this, GalleryActivity.class);

        startActivity(mGalleryIntent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void preTechnieksButtonClicked(View view) {
        Intent mPreIntent = new Intent(this, PreTechActivity.class);

        startActivity(mPreIntent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
