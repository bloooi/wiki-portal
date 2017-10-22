package lee.jaebeom.allwiki.setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.Map;

import lee.jaebeom.allwiki.R;
import lee.jaebeom.allwiki.main.MainActivity;
import lee.jaebeom.allwiki.util.Dialog;
import lee.jaebeom.allwiki.wiki.WikiData;
import lee.jaebeom.allwiki.wiki.WikiModel;

public class SettingActivity extends AppCompatActivity{

    SettingPresent presenter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        SettingFragment fragment = new SettingFragment();
        presenter = new SettingPresent(fragment);

        toolbar.setTitle(R.string.setting);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment, null).commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int wikis = 0;
        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            if (entry.getValue().isUse()){
                wikis++;
            }
        }
        if (item.getItemId() == R.id.confirm){
            if (wikis > 0){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }else{
                Dialog.notice(this, R.string.title_none_wikis, R.string.message_none_wikis);
            }
        }
        return true;
    }

}
