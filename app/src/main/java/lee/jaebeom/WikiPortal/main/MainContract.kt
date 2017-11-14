package lee.jaebeom.WikiPortal.main

import android.content.Context
import lee.jaebeom.WikiPortal.BasePresenter
import lee.jaebeom.WikiPortal.BaseView

/**
 * Created by leejaebeom on 2017. 10. 31..
 */
interface MainContract {
    interface View : BaseView<Presenter>{
        fun dataChange();
        fun settingChange();
    }

    interface TabView : BaseView<TabPresenter>{
        fun CallBack();
    }

    interface Presenter: BasePresenter{
        fun search(keyword: String, context: Context)
        fun setSetting();
    }

    interface TabPresenter: BasePresenter{
        fun saveHTML(Html : String)
        fun getHTML() : String

        fun CallBack()

        fun getWikiURL(wiki : String) : String
        fun getWikiFrontURL(wiki: String) : String
    }
}