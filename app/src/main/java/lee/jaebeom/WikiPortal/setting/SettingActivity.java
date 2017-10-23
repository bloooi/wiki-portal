package lee.jaebeom.WikiPortal.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Map;

import lee.jaebeom.WikiPortal.R;
import lee.jaebeom.WikiPortal.main.MainActivity;
import lee.jaebeom.WikiPortal.util.Dialog;
import lee.jaebeom.WikiPortal.util.Values;
import lee.jaebeom.WikiPortal.wiki.WikiData;
import lee.jaebeom.WikiPortal.wiki.WikiModel;

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
                setResult(RESULT_OK);
                finish();
            }else{
                Dialog.notice(this, R.string.title_none_wikis, R.string.message_none_wikis);
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        finish();
    }
}
