/*
package lee.jaebeom.WikiPortal.setting;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;

import java.util.Map;

import lee.jaebeom.WikiPortal.R;
import lee.jaebeom.WikiPortal.util.SharedPreference;
import lee.jaebeom.WikiPortal.wiki.WikiData;
import lee.jaebeom.WikiPortal.wiki.WikiModel;

public class SettingFragment extends PreferenceFragmentCompat implements SettingContract.View, SharedPreferences.OnSharedPreferenceChangeListener{

    SettingContract.Presenter presenter;
    ListPreference listPreference;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_setting);

        PreferenceCategory category = (PreferenceCategory)findPreference("setting_title");
        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference(this.getActivity());
            checkBoxPreference.setTitle(entry.getKey());
            checkBoxPreference.setKey(entry.getKey());
            checkBoxPreference.setChecked(false);
            category.addPreference(checkBoxPreference);
        }

        listPreference = (ListPreference)findPreference("wikipedia_language_setting");
        listPreference.setSummary(
                listPreference.getEntries()[
                    listPreference.findIndexOfValue(
                            SharedPreference.getStringPreferences(getContext(), "wikipedia_language_setting")
                    )
                ].toString());
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                ListPreference listPreference = (ListPreference)preference;
                String langKey = listPreference.getEntries()[listPreference.findIndexOfValue(newValue.toString())].toString();  //ex) English, 한국어
                String langValue = newValue.toString();    //ex) en, ko

                listPreference.setSummary(langKey);
                WikiData.wikiURL.get(WikiData.Wikipedia).setLanguage(langValue);
                WikiData.wikiURL.get(WikiData.Wikipedia).setChangeURLLanguage(langValue);
                Log.d("listPreference", newValue.toString());
                return true;
            }
        });
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
    public void _presenter(SettingContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        presenter.setUsed(sharedPreferences, key);
        Log.d("pref key", key);
        if (!key.equals("wikipedia_language_setting")){
            WikiData.wikiURL.get(key).setUse(sharedPreferences.getBoolean(key, false));
        }else{

        }
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
*/
