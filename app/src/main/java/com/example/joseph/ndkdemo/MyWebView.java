package com.example.joseph.ndkdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by joseph on 2017/11/11.
 */

public class MyWebView extends WebView{
    public MyWebView(Context context) {
        this(context,null);;
    }

    public MyWebView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }



    private void initData() {
        getSettings().setDefaultTextEncodingName("UTF-8");
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new WebViewClient());
        setWebChromeClient(new WebChromeClient());

    }

}
