package lee.jaebeom.WikiPortal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class temp extends AppCompatActivity {

    WebView temp;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        temp = findViewById(R.id.temp_webview);
        temp.getSettings().setJavaScriptEnabled(true);
        temp.loadUrl("https://ko.m.wikipedia.org/wiki/아이폰");
    }

}
