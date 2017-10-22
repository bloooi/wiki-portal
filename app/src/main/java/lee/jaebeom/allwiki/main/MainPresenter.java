package lee.jaebeom.allwiki.main;

import android.content.Context;
import android.util.Log;

import lee.jaebeom.allwiki.util.SharedPreference;

/**
 * Created by leejaebeom on 2017. 10. 13..
 */

public class MainPresenter implements MainContract.Presenter {
    MainContract.View mainView;
    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainView.setPresenter(this);
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
        mainView.dataChange();
    }


}
