package com.voodoo94.app.technieks2015;

/**
 * Created by Shreesha on 3/9/2015.
 */
    import android.webkit.WebView;
    import android.webkit.WebViewClient;

public class WebClient extends WebViewClient {

        @Override
   public boolean shouldOverrideUrlLoading(WebView v, String url) {
            v.loadUrl(url);
            return true;
        }

}
