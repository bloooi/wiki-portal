package lee.jaebeom.WikiPortal.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Map;

import lee.jaebeom.WikiPortal.util.SharedPreference;
import lee.jaebeom.WikiPortal.wiki.WikiData;
import lee.jaebeom.WikiPortal.wiki.WikiModel;

/**
 * Created by leejaebeom on 2017. 10. 14..
 */

public class MainPagerAdapter extends FragmentPagerAdapter implements MainContract.View {
    String keyword;
    int wikis = 0;
    Context context;
    private MainContentFragment []fragment;
    private MainTabPresenter [] presenters;
    private ArrayList <WikiModel>useWikis = new ArrayList();
    private MainContract.Presenter presenter;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword);

        for (Map.Entry<String, WikiModel> entry: WikiData.wikiURL.entrySet()){
            if (entry.getValue().isUse()){
                useWikis.add(entry.getValue());
                wikis++;
            }
        }
        presenters = new MainTabPresenter[wikis];
        fragment = new MainContentFragment[wikis];
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
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return wikis;
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void dataChange() {
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword);
        notifyDataSetChanged();
    }
}
