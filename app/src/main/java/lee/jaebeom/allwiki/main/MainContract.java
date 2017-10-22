package lee.jaebeom.allwiki.main;


import android.content.Context;

import lee.jaebeom.allwiki.BasePresenter;
import lee.jaebeom.allwiki.BaseView;

/**
 * Created by leejaebeom on 2017. 10. 13..
 */

public interface MainContract {
    interface View extends BaseView<Presenter>{
        void dataChange();
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
