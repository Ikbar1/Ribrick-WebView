package com.example.ribrickr4mediaproject;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    //Variable for WebView and WebView WebSettings
    private WebView webView;
    private WebSettings webSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Set back the Main Theme, cause in the Manifest the app Theme is set for Splash screen
        setTheme(R.style.Theme_RibrickR4MediaProject);
        setContentView(R.layout.activity_main);


        //Get WebView ID and enable WebView Settings
        webView = (WebView) findViewById(R.id.WebView);
        webSettings = webView.getSettings();

        //Optional Settings
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);


        //Opening the Web that load in the App
        webView.setWebViewClient(new WebViewClient(){
            //Prevent error when jump to Whatsapp or other web
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url == null || url.startsWith("http://") || url.startsWith("https://")) return false;

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    view.getContext().startActivity(intent);
                    return true;
                } catch (Exception e) {
                    Log.i(TAG, "shouldOverrideUrlLoading Exception:" + e);
                    return true;
                }
            }
        });

        webView.loadUrl("https://ribrick.id/");


    }
    //When back button pressed, go back to previous page
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();
        }
    }
}