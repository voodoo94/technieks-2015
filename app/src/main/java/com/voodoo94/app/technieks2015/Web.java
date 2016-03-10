package com.voodoo94.app.technieks2015;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class Web extends Activity {

    WebView tweetViewer;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Bundle b = getIntent().getExtras();
        final Activity activity = this;
        String url = b.getString("url");

        tweetViewer = (WebView) findViewById(R.id.tweetyView);

        tweetViewer.getSettings().setJavaScriptEnabled(true);
        tweetViewer.getSettings().setLoadWithOverviewMode(true);
        tweetViewer.getSettings().setUseWideViewPort(true);
        tweetViewer.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        tweetViewer.getSettings().setLoadsImagesAutomatically(true);

        tweetViewer.setWebChromeClient(new WebChromeClient(){

            public void onProgressChanged(WebView view, int progress) {
                activity.setTitle("Loading...");
                activity.setProgress(progress * 100);
                if(progress == 100)
                    activity.setTitle("techNIEks 2015");
            }
        });
        tweetViewer.setWebViewClient(new WebClient());

        tweetViewer.setOnKeyListener(new OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });

        tweetViewer.loadUrl(url);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
