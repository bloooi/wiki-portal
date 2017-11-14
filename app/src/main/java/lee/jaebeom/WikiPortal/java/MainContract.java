/*
package lee.jaebeom.WikiPortal.main;


import android.content.Context;

import lee.jaebeom.WikiPortal.BasePresenter;
import lee.jaebeom.WikiPortal.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void dataChange();
        void settingChange();
    }
    interface TabView extends BaseView<TabPresenter> {
        void CallBack();
    }
    interface Presenter extends BasePresenter {
        void search(String keyword, Context context);
        void setSetting();
    }

    interface TabPresenter extends BasePresenter{
        void saveHTML(String Html);
        String getHTML();

        void CallBack();

        String getWikiURL(String wiki);
        String getWikiFrontURL(String wiki);
    }
}
*/