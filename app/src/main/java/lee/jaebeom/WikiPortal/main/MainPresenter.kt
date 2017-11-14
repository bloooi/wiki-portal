package lee.jaebeom.WikiPortal.main

import android.content.Context
import lee.jaebeom.WikiPortal.util.SharedPreference

/**
 * Created by leejaebeom on 2017. 11. 6..
 */
class MainPresenter(val mainView : MainContract.View) : MainContract.Presenter{
    init {
        mainView._presenter(this)
    }
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(keyword: String, context: Context) {
        SharedPreference.savePreference(SharedPreference.keyKeyword, keyword, context)
        mainView.dataChange()
    }

    override fun setSetting() {
        mainView.settingChange()
    }
}