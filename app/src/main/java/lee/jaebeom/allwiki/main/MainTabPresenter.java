package lee.jaebeom.allwiki.main;

import java.io.Serializable;

import lee.jaebeom.allwiki.wiki.WikiData;

/**
 * Created by leejaebeom on 2017. 10. 15..
 */

public class MainTabPresenter implements MainContract.TabPresenter, Serializable {
    String HTML;
    private final MainContract.TabView tabView;

    public MainTabPresenter(MainContract.TabView tabView) {
        this.tabView = tabView;
        this.tabView.setPresenter(this);
    }

    @Override
    public void CallBack() {
        tabView.CallBack();
    }

    @Override
    public void start() {

    }

    @Override
    public void saveHTML(String Html) {
        HTML = Html;
    }

    @Override
    public String getHTML() {
        return HTML;
    }

    @Override
    public String getWikiURL(String wiki){
        return WikiData.wikiURL.get(wiki).getURL();
    }

    @Override
    public String getWikiFrontURL(String wiki) {
        return WikiData.wikiURL.get(wiki).getFrontURL();
    }
}
