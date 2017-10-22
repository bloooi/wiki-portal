package lee.jaebeom.allwiki.setting;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

import lee.jaebeom.allwiki.R;
import lee.jaebeom.allwiki.wiki.WikiData;
import lee.jaebeom.allwiki.wiki.WikiModel;

/**
 * Created by leejaebeom on 2017. 10. 17..
 */

public class SettingFragment extends PreferenceFragmentCompat implements SettingContract.View, SharedPreferences.OnSharedPreferenceChangeListener{

    SettingContract.Presenter presenter;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_setting);

        PreferenceCategory category = (PreferenceCategory)findPreference("setting_title");

        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference(this.getActivity());
            checkBoxPreference.setTitle(entry.getKey());
            checkBoxPreference.setKey(entry.getKey());
            checkBoxPreference.setChecked(false);
            checkBoxPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    Log.d("key", preference.getKey());
                    return true;
                }
            });
            category.addPreference(checkBoxPreference);
        }
    }

    @Override
    public void setDivider(Drawable divider) {
        super.setDivider(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public void setDividerHeight(int height) {
        super.setDividerHeight(0);
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        presenter.setUsed(sharedPreferences, key);

        WikiData.wikiURL.get(key).setUse(sharedPreferences.getBoolean(key, false));
//        if (WikiData.wikiURL.get(key).isUse()){
//            WikiData.wikis++;
//        }else{
//            WikiData.wikis--;
//        }

    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);

    }
}
