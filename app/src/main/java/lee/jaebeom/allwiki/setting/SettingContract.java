package lee.jaebeom.allwiki.setting;

import android.content.SharedPreferences;

import lee.jaebeom.allwiki.BasePresenter;
import lee.jaebeom.allwiki.BaseView;

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
