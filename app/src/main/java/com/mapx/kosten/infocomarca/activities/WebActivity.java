package com.mapx.kosten.infocomarca.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.mapx.kosten.infocomarca.R;

public class WebActivity extends AppCompatActivity {
    private final String TAG = "ItemsActivity";

    // declaration of variables
    private Toolbar mToolbar;
    private String mItemsArray;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private int mItemPosition;
    private String[] mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // instantiation of variables
        mToolbar = (Toolbar) findViewById(R.id.toolbarLayout_main);
        mWebView = (WebView) findViewById(R.id.webview_items);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_items);

        // toolbar
        setSupportActionBar(mToolbar);

        // obtenemos datos pasado por Activity previo
        Bundle bundle = getIntent().getExtras();
        mItemsArray = bundle.getString("ItemsTitle");
        mItemPosition = bundle.getInt("ItemPosition");

        getSupportActionBar().setTitle(mItemsArray);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        WebSettings webSettings = mWebView.getSettings();
        //mWebView.setInitialScale(1);
        webSettings.setJavaScriptEnabled(true);
        //mWebView.getSettings().setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X;
        // en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        //mWebView.getSettings().setUserAgentString("Android");
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.getSettings().setUserAgentString("Android");

        mWebView.setWebViewClient(new MyWebViewClient());

        switch (mItemsArray) {
            case "Diarios":
                mUrl = getResources().getStringArray(R.array.news_url);
                break;
            case "Radios":
                mUrl = getResources().getStringArray(R.array.news_url);
                break;
            default:
                //mUrl = getResources().getStringArray(R.array.url_local_array);
                break;
        }

        mWebView.loadUrl(mUrl[mItemPosition]);
    }

    @Override
    // listener de los iconos del menu de la toolbar
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            mProgressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);

            mProgressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }
}
