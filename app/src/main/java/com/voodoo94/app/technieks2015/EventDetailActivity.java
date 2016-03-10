package com.voodoo94.app.technieks2015;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class EventDetailActivity extends ActionBarActivity {
    public static final String REG = "com.voodoo94.app.technieks2015.REG";

    private String name;
    private String desc;
    private String regUrl;
    private String imgUrl;

    private TextView mName;
    private TextView mDesc;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.test);
        super.onCreate(savedInstanceState);

        Intent mRecievedIntent = getIntent();
        //Bundle mBundle = mRecievedIntent.getExtras();

        /*name = mBundle.getString("name");
        desc = mBundle.getString("desc");
        imgUrl = mBundle.getString("imgUrl");
        regUrl = mBundle.getString("imgUrl");

        mName = (TextView) findViewById(R.id.eventHeadText);
        mDesc = (TextView) findViewById(R.id.eventDescText);
        mImage = (ImageView) findViewById(R.id.EImageView);

        mName.setText(name);
        mDesc.setText(desc);*/
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

        mRegisterIntent.putExtra(REG, regUrl);

        startActivity(mRegisterIntent);
    }
}
