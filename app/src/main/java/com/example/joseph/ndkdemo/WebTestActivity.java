package com.example.joseph.ndkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by joseph on 2017/11/13.
 */

public class WebTestActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
//        setContentView(R.layout.activity_web_test);
        setContentView(webView);

        initView();

    }

    private void initView() {
//        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webView.addJavascriptInterface(new AndroidToast(), "AndroidToast");
        webView.addJavascriptInterface(new AndroidMessage(), "AndroidMessage");

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.loadUrl("javascript:(function(){" +
                        "var objs = document.getElementsByTagName(\"img\"); " +
                        "for(var i=0;i<objs.length;i++)  " +
                        "{"
                        + "    objs[i].onclick=function()  " +
                        "    {  "
                        + "        window.AndroidToast.show(this.src);  " +
                        "    }  " +
                        "}" +
                        "})()");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;


            }
        });



//       webView.loadUrl("http://www.baidu.com");

        webView.loadUrl("file:///android_asset/index.html");
    }


    public class AndroidToast {

        @JavascriptInterface
        public void show(String str) {
            Toast.makeText(WebTestActivity.this, str, Toast.LENGTH_SHORT).show();
        }

    }

    public class AndroidMessage {
        @JavascriptInterface
        public String getMsg() {
            return "form java";
        }
    }

    public void javaCallJS() {
        webView.loadUrl("javascript:callFromJava('call from java')");
    }

    @Override
    public void onBackPressed() {

        javaCallJS();

    }
}
