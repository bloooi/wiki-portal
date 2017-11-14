/*
package lee.jaebeom.WikiPortal.main;

import android.content.Context;

import lee.jaebeom.WikiPortal.util.SharedPreference;

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mainView;
    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainView._presenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void search(String keyword, Context context) {
        SharedPreference.savePreference(SharedPreference.keyKeyword, keyword, context);
        mainView.dataChange();
    }

    @Override
    public void setSetting() {
        mainView.settingChange();
    }


}
*/