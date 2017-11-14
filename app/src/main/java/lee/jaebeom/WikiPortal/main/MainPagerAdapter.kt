package lee.jaebeom.WikiPortal.main

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import lee.jaebeom.WikiPortal.util.SharedPreference
import lee.jaebeom.WikiPortal.wiki.WikiData
import lee.jaebeom.WikiPortal.wiki.WikiData.Companion.useWikis


/**
 * Created by leejaebeom on 2017. 11. 1..
 */
class MainPagerAdapter(fm: FragmentManager, context: Context) : FragmentStatePagerAdapter(fm), MainContract.View{
    private var context : Context
    private var keyword : String
    private var fragments : Array<MainContentFragment?>? = null
    private var presenters: Array<MainTabPresenter?>? = null
    private lateinit var presenter : MainContract.Presenter
    init {
        this.context = context
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword)
        init()

    }

    override fun getPageTitle(position: Int): CharSequence {
        return useWikis.get(position).name
    }

    override fun getItem(position: Int): Fragment? {
        fragments!![position] = MainContentFragment.newInstance(useWikis.get(position).name)
        presenters!![position] = MainTabPresenter(fragments!![position]!!)
        return fragments!![position]
    }

    override fun getItemPosition(`object`: Any?): Int {
        if (useWikis.contains(`object`)){
            return useWikis.indexOf(`object`)
        }else{
            return PagerAdapter.POSITION_NONE
        }
    }
    override fun getCount(): Int {
        return useWikis.size
    }

    fun init(){

        WikiData.sortSequence()

        fragments = arrayOfNulls(useWikis.size)
        presenters = arrayOfNulls(useWikis.size)
    }

    override fun dataChange() {
        keyword = SharedPreference.getStringPreferences(context, SharedPreference.keyKeyword)
        notifyDataSetChanged()
    }

    override fun settingChange() {
        init()
        notifyDataSetChanged()
    }

    override fun _presenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

}