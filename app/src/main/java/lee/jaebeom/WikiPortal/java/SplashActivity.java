/*
package lee.jaebeom.WikiPortal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

import java.util.Map;

import lee.jaebeom.WikiPortal.main.MainActivity;
import lee.jaebeom.WikiPortal.setting.SettingActivity;
import lee.jaebeom.WikiPortal.wiki.WikiData;
import lee.jaebeom.WikiPortal.wiki.WikiModel;


public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int wikis = 0;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            entry.getValue().setUse(sharedPref.getBoolean(entry.getKey(), false));
            String lang = sharedPref.getString(entry.getKey() + "_language_setting", WikiData.korean);
            entry.getValue().setLanguage(lang);
            if (entry.getKey().equals(WikiData.Wikipedia)){
                entry.getValue().setChangeURLLanguage(lang);
            }
            if (entry.getValue().isUse()){
                wikis++;
            }
        }
        Intent activityIntent;
        if (wikis == 0){
            activityIntent = new Intent(this, SettingActivity.class);
        }else{
            activityIntent = new Intent(this, MainActivity.class);
        }
        startActivity(activityIntent);
        finish();

        overridePendingTransition(0, 0);
    }
}
*/