package lee.jaebeom.WikiPortal.setting

import android.content.SharedPreferences
import lee.jaebeom.WikiPortal.wiki.WikiData

/**
 * Created by leejaebeom on 2017. 11. 9..
 */
class SettingPresenter(view : SettingContract.View) : SettingContract.Presenter{
    init {
        view._presenter(this)
    }
    override fun start() {

    }

    override fun setUsed(preferences: SharedPreferences, key: String) {
        WikiData.wikiURL.get(key)?.isUse = preferences.getBoolean(key, false)
    }

    override fun isConfirm(): Boolean {
        return WikiData.wikis!! > 0
    }
}