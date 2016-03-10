package com.voodoo94.app.technieks2015;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class PreTechActivity extends ActionBarActivity {
    private NetworkUtility mNet;

    private String mJsonString;

    final String URL = "http://technieks.com/pre";

    public WebView mWebView;
    public ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_tech);

        mNet = new NetworkUtility();

        mWebView = (WebView) findViewById(R.id.webview_pretech);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_pretech);

        // Enable JavaScript.
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setUseWideViewPort(true);

        mWebView.setInitialScale(1);

        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.setScrollbarFadingEnabled(true);

        mWebView.setWebViewClient(new myWebClient());

        if (!mNet.is_connected(getApplication())) {
            Toast.makeText(getApplication(), "No Internet access...", Toast.LENGTH_LONG).show();
        } else {
            mWebView.loadUrl(URL);
        }
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            mProgressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            mProgressBar.setVisibility(View.GONE);
        }
    }

    private class JSONParse extends AsyncTask<String, Void, Void> {

        @Override
        public void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        public Void doInBackground(String... args) {
            HttpURLConnection mHttpConnection;

            BufferedReader reader;

            String mJsonString;

            String ACTION_PARAM = "action";
            String VALUE_PARAM = "get_json";

            try {
                Uri buildUri = Uri.parse(URL).buildUpon()
                        .appendQueryParameter(ACTION_PARAM, VALUE_PARAM)
                        .build();

                java.net.URL url = new URL(buildUri.toString());

                mHttpConnection = (HttpURLConnection) url.openConnection();
                mHttpConnection.setRequestMethod("POST");
                mHttpConnection.connect();

                InputStream inputStream = mHttpConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }

                mJsonString = buffer.toString();

                Log.d("LOG_TAG", mJsonString);

            } catch (Exception e) {

            }
            return null;
        }

        @Override
        public void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pre_tech, menu);
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
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
