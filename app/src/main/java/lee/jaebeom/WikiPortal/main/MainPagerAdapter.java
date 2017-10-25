package lee.jaebeom.WikiPortal.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import lee.jaebeom.WikiPortal.util.SharedPreference;
import lee.jaebeom.WikiPortal.wiki.WikiData;
import lee.jaebeom.WikiPortal.wiki.WikiModel;

/**
 * Created by leejaebeom on 2017. 10. 14..
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter implements MainContract.View {
    int wikis = 0;
    String keyword;
    Context context;
    private MainContentFragment []fragment;
    private MainTabPresenter [] presenters;
    private ArrayList <WikiModel>useWikis;
    private MainContract.Presenter presenter;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword);

        init();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return useWikis.get(position).getName();
    }

    @Override
    public Fragment getItem(int position) {
        fragment[position] = MainContentFragment.newInstance(useWikis.get(position).getName());
        presenters[position] = new MainTabPresenter(fragment[position]);
        return fragment[position];
    }

    @Override
    public int getItemPosition(Object object) {
        if (useWikis.contains(object)) {
            return useWikis.indexOf(object);
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public int getCount() {
        return wikis;
    }

    private void init(){
        wikis = 0;
        useWikis = new ArrayList<>();
        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            if (entry.getValue().isUse()){
                useWikis.add(entry.getValue());
                wikis++;
            }
        }
        AscendingObj ascending = new AscendingObj();
        Collections.sort(useWikis, ascending);

        presenters = new MainTabPresenter[wikis];
        fragment = new MainContentFragment[wikis];
    }
    @Override
    public void dataChange() {
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword);
        notifyDataSetChanged();
    }

    @Override
    public void settingChange() {
        init();
        notifyDataSetChanged();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    class AscendingObj implements Comparator<WikiModel> {

        @Override
        public int compare(WikiModel wikiModel, WikiModel t1) {
            return Integer.valueOf(wikiModel.getOrder()).compareTo(t1.getOrder());
        }
    }
}
