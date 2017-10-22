package lee.jaebeom.allwiki;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

import java.util.Map;

import lee.jaebeom.allwiki.main.MainActivity;
import lee.jaebeom.allwiki.setting.SettingActivity;
import lee.jaebeom.allwiki.wiki.WikiData;
import lee.jaebeom.allwiki.wiki.WikiModel;

/**
 * Created by leejaebeom on 2017. 10. 18..
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int wikis = 0;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            entry.getValue().setUse(sharedPref.getBoolean(entry.getKey(), false));
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
