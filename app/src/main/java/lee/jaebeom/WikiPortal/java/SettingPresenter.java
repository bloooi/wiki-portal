/*
package lee.jaebeom.WikiPortal.setting;

import android.content.SharedPreferences;

import lee.jaebeom.WikiPortal.wiki.WikiData;


public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View view;

    public SettingPresenter(SettingContract.View view) {
        this.view = view;
        view._presenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void setUsed(SharedPreferences sharedPreferences, String key) {
        WikiData.wikiURL.get(key).setUse(sharedPreferences.getBoolean(key, false));
//        if (WikiData.wikiURL.get(key).isUse()){
//            WikiData.wikis++;
//        }else{
//            WikiData.wikis--;
//        }
    }

    @Override
    public boolean isConfirm() {
        if(WikiData.wikis > 0){
            return true;
        }else{
            return false;
        }

    }
}
*/
