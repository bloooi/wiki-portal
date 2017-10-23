package lee.jaebeom.WikiPortal.util;

import android.os.AsyncTask;
import android.webkit.WebView;

/**
 * Created by leejaebeom on 2017. 10. 18..
 */

public class DomAccessTask extends AsyncTask<WebView, Object, Integer> {
    @Override
    protected Integer doInBackground(WebView... webViews) {
        for (WebView webView : webViews){

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
}
