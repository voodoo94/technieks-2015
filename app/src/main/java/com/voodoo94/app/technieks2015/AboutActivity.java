package com.voodoo94.app.technieks2015;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;


public class AboutActivity extends ActionBarActivity implements android.view.View.OnClickListener {
    private ActionBar mActionBar;

    private TextView mHeaderTextView;
    private TextView mAboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        if (getActionBar() != null)
            getActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Typeface or = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        Typeface cb = Typeface.createFromAsset(getAssets(), "fonts/corbert.ttf");

        mHeaderTextView = (TextView) findViewById(R.id.about_header);
        mAboutTextView = (TextView) findViewById(R.id.about);

        mHeaderTextView.setTypeface(or);
        mAboutTextView.setTypeface(cb);

        Button fb = (Button) findViewById(R.id.fb);
        Button insta = (Button) findViewById(R.id.insta);
        Button sc = (Button) findViewById(R.id.sc);
        Button yt = (Button) findViewById(R.id.yt);

        fb.setOnClickListener(this);
        insta.setOnClickListener(this);
        sc.setOnClickListener(this);
        yt.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fb: {
                Intent mIntent = new Intent(this, Web.class);
                Bundle mB = new Bundle();

                mB.putString("url", "https://www.facebook.com/technieks");

                mIntent.putExtras(mB);

                startActivity(mIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.insta: {
                Intent mIntent = new Intent(this, Web.class);
                Bundle mB = new Bundle();

                mB.putString("url", "https://www.instagram.com/technieks15");

                mIntent.putExtras(mB);

                startActivity(mIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.sc: {
                Intent mIntent = new Intent(this, Web.class);
                Bundle mB = new Bundle();

                mB.putString("url", "https://www.soundcloud.com/technieks2015");

                mIntent.putExtras(mB);

                startActivity(mIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
            case R.id.yt: {
                Intent mIntent = new Intent(this, Web.class);
                Bundle mB = new Bundle();

                mB.putString("url", "https://www.youtube.com/channel/UC0Ky30GAIfdtGccczVNUIqA");

                mIntent.putExtras(mB);

                startActivity(mIntent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            }
        }
    }
}
