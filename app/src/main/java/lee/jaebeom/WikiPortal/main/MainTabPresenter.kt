package lee.jaebeom.WikiPortal.main

import lee.jaebeom.WikiPortal.wiki.WikiData
import java.io.Serializable

/**
 * Created by leejaebeom on 2017. 11. 6..
 */
class MainTabPresenter(tabView: MainContract.TabView) : MainContract.TabPresenter, Serializable{
    lateinit var HTMLCode : String
    private var tabView: MainContract.TabView = tabView

    init {
        this.tabView._presenter(this)
    }
    override fun start() {

    }

    override fun saveHTML(Html: String) {
        HTMLCode = Html
    }

    override fun getHTML(): String {
        return HTMLCode
    }

    override fun CallBack() {
        tabView.CallBack()
    }

    override fun getWikiURL(wiki: String): String {
        return WikiData.wikiURL.get(wiki)!!.URL
    }

    override fun getWikiFrontURL(wiki: String): String {
        return WikiData.wikiURL.get(wiki)!!.frontURL
    }

}