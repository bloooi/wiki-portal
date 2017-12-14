package lee.jaebeom.WikiPortal.main

import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.miguelcatalan.materialsearchview.MaterialSearchView
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.databinding.ActivityMainBinding
import lee.jaebeom.WikiPortal.setting.SettingActivity
import lee.jaebeom.WikiPortal.util.Dialog
import lee.jaebeom.WikiPortal.util.NetworkCheck
import lee.jaebeom.WikiPortal.util.SharedPreference
import lee.jaebeom.WikiPortal.util.Values.Companion.SETTING_REQUEST_CODE

/**
 * Created by leejaebeom on 2017. 10. 30..
 */

class MainActivity : AppCompatActivity() {
    lateinit var presenter: MainPresenter
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        if (!NetworkCheck.isWifiConnect(this)){
//            Dialog.notice(this, R.string.title_unconnected_network, R.string.message_unconnected_network, DialogInterface.OnClickListener { p0, p1 -> finish() })
//        }
        //toolbar 생략
        //MaterialSearchView 생략
        //ViewPager 생략
        //TabLayout 생략
        val adapter = MainPagerAdapter(supportFragmentManager, this)
        presenter = MainPresenter(adapter)

        var keyword: String = SharedPreference.getStringPreferences(this, SharedPreference.keyKeyword)

        if(!keyword.equals("")){
            binding.toolbar.setTitle(keyword);
        }else{
            Handler().postDelayed({ kotlin.run { binding.searchView.showSearch() } }, 100)
        }

        setSupportActionBar(binding.toolbar)

        binding.toolbar.setOnClickListener {
            binding.searchView.showSearch()
            binding.searchView.setText(binding.toolbar.title)
        }
        binding.viewPager.adapter = adapter
        binding.viewPager.offscreenPageLimit = 2

        binding.tabLayout.setupWithViewPager(binding.viewPager, true)

        binding.searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.search(query!!, this@MainActivity)
                binding.toolbar.title = query
                onBackPressed()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean { return false }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val item = menu?.findItem(R.id.action_search)
        binding.searchView.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_setting){
            val intent = Intent(this, SettingActivity::class.java)
            intent.putExtra("isMain", true)
            startActivityForResult(intent, SETTING_REQUEST_CODE)
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK){
            if (requestCode == SETTING_REQUEST_CODE){
                presenter.setSetting()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        if (binding.searchView.isSearchOpen){
            binding.searchView.closeSearch()
        }else{
            super.onBackPressed()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
    }
}