package lee.jaebeom.allwiki.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import lee.jaebeom.allwiki.R;
import lee.jaebeom.allwiki.setting.SettingActivity;
import lee.jaebeom.allwiki.util.Dialog;
import lee.jaebeom.allwiki.util.SharedPreference;

public class MainActivity extends AppCompatActivity{
    MaterialSearchView searchView;
    Toolbar toolbar;
    ViewPager mainViewPager;
    TabLayout mainTabLayout;
    MainPresenter presenter;

    MainPagerAdapter adapter;

    private final int SETTING_REQUEST_CODE = 1243;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        mainTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mainViewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        presenter = new MainPresenter(adapter);

        String keyword = SharedPreference.getStringPreferences(this,SharedPreference.keyKeyword);
        if (!keyword.equals("")){
            toolbar.setTitle(keyword);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchView.showSearch();
                }
            }, 100);
        }

        setSupportActionBar(toolbar);


        mainViewPager.setAdapter(adapter);
        mainViewPager.setOffscreenPageLimit(2);
        mainTabLayout.setupWithViewPager(mainViewPager);

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.search(query, MainActivity.this);
                toolbar.setTitle(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting){
            startActivityForResult(new Intent(this, SettingActivity.class), SETTING_REQUEST_CODE);
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK){
            if (requestCode == SETTING_REQUEST_CODE){
                presenter.setSetting();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()){
            searchView.closeSearch();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
