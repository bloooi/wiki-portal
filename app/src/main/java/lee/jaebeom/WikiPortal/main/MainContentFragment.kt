package lee.jaebeom.WikiPortal.main

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.support.v4.app.Fragment

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.*
import android.webkit.*
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.databinding.FragmentMainBinding
import lee.jaebeom.WikiPortal.main.MainContract.TabPresenter
import lee.jaebeom.WikiPortal.util.DOMAccess
import lee.jaebeom.WikiPortal.util.SharedPreference
import java.io.Serializable

/**
 * Created by leejaebeom on 2017. 10. 31..
 */
class MainContentFragment : Fragment(), MainContract.TabView, View.OnKeyListener, Serializable {
    lateinit var presenter: TabPresenter
    lateinit var wiki: String
    lateinit var URL: String
    lateinit var binding :FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wiki = arguments.getString("wiki")
    }

    companion object {
        @JvmStatic fun newInstance(wiki: String) : MainContentFragment{
            val args = Bundle()
            args.putString("wiki", wiki)
            val fragment = MainContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("SetJavaScriptEnabled", "SetJavaScriptEnabled")
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        if (presenter != null){
            if (!SharedPreference.getStringPreferences(activity.applicationContext, SharedPreference.keyKeyword).equals("")){
                URL = presenter.getWikiURL(arguments.getString("wiki")) + SharedPreference.getStringPreferences(activity.applicationContext, SharedPreference.keyKeyword)
            }else{
                URL = presenter.getWikiFrontURL(arguments.getString("wiki"))
            }
        }else{
            URL = savedInstanceState?.getString("URL")!!
            wiki = savedInstanceState.getString("wiki")!!
        }
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val view = binding.root

        binding.fragmentDOMAccess.settings.javaScriptEnabled = true
        binding.fragmentDOMAccess.addJavascriptInterface(MyJavaScriptInterface(), "HTMLOUT")

        binding.fragmentWebView.settings.javaScriptEnabled = true
//        binding.fragmentWebView.canScrollHor(10)

        binding.fragmentDOMAccess.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding.fragmentProgressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                view?.loadUrl(DOMAccess.selectJavascript(wiki))
            }
        }

        binding.fragmentDOMAccess.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.fragmentProgressBar.progress = newProgress
            }
        }

        binding.fragmentWebView.webViewClient = object : WebViewClient(){
            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                binding.fragmentDOMAccess.loadUrl(request?.url.toString())
                return true
            }

            @Suppress("DEPRECATION")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                binding.fragmentDOMAccess.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.fragmentProgressBar.visibility = View.GONE
            }
        }

        binding.fragmentWebView.setOnKeyListener(this)

        binding.fragmentDOMAccess.loadUrl(URL)
        return view
    }

    override fun _presenter(presenter: TabPresenter) {
        this.presenter = presenter
    }

    override fun CallBack() {
        binding.fragmentWebView.post(Runnable {
            binding.fragmentWebView.loadDataWithBaseURL(URL, presenter.getHTML(), "text/html", "UTF-8", null)
        })
    }

    override fun onKey(p0: View?, i: Int, keyEvent: KeyEvent): Boolean {
        if (i == KeyEvent.KEYCODE_BACK && keyEvent.action == MotionEvent.ACTION_UP && binding.fragmentWebView.canGoBack()){
            binding.fragmentWebView.goBack()
            binding.fragmentProgressBar.visibility = View.VISIBLE
            return true
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString("URL", URL)
        outState?.putString("wiki", wiki)
        super.onSaveInstanceState(outState)
    }

    inner class MyJavaScriptInterface{
        @JavascriptInterface
        fun processHTML(html: String) {
            presenter.saveHTML(html)
            presenter.CallBack()
        }
    }

}