package com.voodoo94.app.technieks2015;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


public class EventDetail extends ActionBarActivity {
    public static final String REG = "com.voodoo94.app.technieks2015.REG";

    private TextView mName;
    private TextView mDesc;
    private ImageView mImage;

    private String name;
    private String desc;
    private String regUrl;
    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Intent mRecievedIntent = getIntent();
        Bundle mBundle = mRecievedIntent.getExtras();

        name = mBundle.getString("name");
        desc = mBundle.getString("desc");
        imgUrl = mBundle.getString("imgUrl");
        regUrl = mBundle.getString("url");

        Typeface or = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        Typeface cb = Typeface.createFromAsset(getAssets(), "fonts/corbert.ttf");

        mName = (TextView) findViewById(R.id.eventHeadText);
        mDesc = (TextView) findViewById(R.id.eventDescText);
        mImage = (ImageView) findViewById(R.id.EImageView);

        mName.setText(name);
        mDesc.setText(desc);

        mName.setTypeface(or);
        mDesc.setTypeface(cb);

        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(new URL(imgUrl).openConnection().getInputStream());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mImage.setImageBitmap(bmp);

        Button mB = (Button) findViewById(R.id.eventRegBut);
        mB.setTypeface(cb);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_detail, menu);
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

    public void registerButtonClicked(View view) {
        Intent mRegisterIntent = new Intent(this, RegisterActivity.class);

        Bundle mB = new Bundle();

        mB.putString("reg", regUrl);
        mRegisterIntent.putExtras(mB);

        startActivity(mRegisterIntent);
    }

    @Override
    public void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
