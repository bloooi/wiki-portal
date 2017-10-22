package lee.jaebeom.allwiki.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.Serializable;

import lee.jaebeom.allwiki.R;
import lee.jaebeom.allwiki.util.DOMAccess;
import lee.jaebeom.allwiki.util.NestedWebView;
import lee.jaebeom.allwiki.util.SharedPreference;

/**
 * Created by leejaebeom on 2017. 10. 14..
 */

public class MainContentFragment extends Fragment implements MainContract.TabView, View.OnKeyListener, Serializable {
    String URL;
    String wiki;
    MainContract.TabPresenter presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            wiki = getArguments().getString("wiki");
        }
    }

    public static MainContentFragment newInstance(String wiki) {
        Bundle args = new Bundle();
        args.putString("wiki", wiki);
        MainContentFragment fragment = new MainContentFragment();
        fragment.setArguments(args);
        return fragment;
    }
    NestedWebView webView;
    WebView DOMAccessWedView;
    ProgressBar webLoadingBar;
    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface"})
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null){
            if (!SharedPreference.getStringPreferences(getContext(),SharedPreference.keyKeyword).equals("")){
                URL = presenter.getWikiURL(getArguments().getString("wiki")) + SharedPreference.getStringPreferences(getContext(),SharedPreference.keyKeyword);
            }else{
                URL = presenter.getWikiFrontURL(getArguments().getString("wiki"));
            }
        }else{
            URL = savedInstanceState.getString("URL");
            wiki = savedInstanceState.getString("wiki");
        }

        View view = inflater.inflate(R.layout.fragment_main, container, false);;
        webView = view.findViewById(R.id.fragment_web_view);
        DOMAccessWedView = (WebView)view.findViewById(R.id.fragment_DOM_Access);
        webLoadingBar = view.findViewById(R.id.fragment_progress_bar);

        DOMAccessWedView.getSettings().setJavaScriptEnabled(true);
        DOMAccessWedView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.canScrollHor(10);

        DOMAccessWedView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                webLoadingBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(DOMAccess.getJavascript(wiki));

            }
        });

        DOMAccessWedView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, final int newProgress) {
                webLoadingBar.setProgress(newProgress);
            }
        });
        webView.setWebViewClient(new WebViewClient(){

            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                DOMAccessWedView.loadUrl(request.getUrl().toString());
                return true;
            }

            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                DOMAccessWedView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webLoadingBar.setVisibility(View.GONE);
            }
        });

        webView.setOnKeyListener(this);

        DOMAccessWedView.loadUrl(URL);
        return view;
    }

    @Override
    public void setPresenter(MainContract.TabPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void CallBack() {
        webView.post(new Runnable() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void run() {
                webView.loadDataWithBaseURL(URL, presenter.getHTML(), "text/html", "UTF-8", null);

            }
        });

    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == MotionEvent.ACTION_UP && webView.canGoBack()){
            webView.goBack();
            webLoadingBar.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("URL", URL);
        outState.putString("wiki", wiki);
        super.onSaveInstanceState(outState);
    }

    class MyJavaScriptInterface {

        @JavascriptInterface
        public void processHTML(final String html) {
            presenter.saveHTML(html);
            presenter.CallBack();
        }
    }
}
