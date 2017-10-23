package lee.jaebeom.WikiPortal.setting;

import android.content.SharedPreferences;

import lee.jaebeom.WikiPortal.BasePresenter;
import lee.jaebeom.WikiPortal.BaseView;

/**
 * Created by leejaebeom on 2017. 10. 18..
 */

public interface SettingContract {
    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        void setUsed(SharedPreferences preferences, String key);
        boolean isConfirm();
    }
}
