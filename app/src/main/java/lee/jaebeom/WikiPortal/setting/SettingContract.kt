package lee.jaebeom.WikiPortal.setting

import android.content.SharedPreferences
import lee.jaebeom.WikiPortal.BasePresenter
import lee.jaebeom.WikiPortal.BaseView
import lee.jaebeom.WikiPortal.util.SharedPreference

/**
 * Created by leejaebeom on 2017. 11. 7..
 */
interface SettingContract {
    interface  View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter {
        fun setUsed(preferences: SharedPreferences, key: String)
        fun isConfirm() : Boolean
    }
}