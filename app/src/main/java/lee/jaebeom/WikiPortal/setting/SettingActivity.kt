package lee.jaebeom.WikiPortal.setting

import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.tool_bar.view.*
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.databinding.ActivitySettingBinding
import lee.jaebeom.WikiPortal.main.MainActivity
import lee.jaebeom.WikiPortal.util.Dialog
import lee.jaebeom.WikiPortal.wiki.WikiData
import lee.jaebeom.WikiPortal.wiki.WikiModel

/**
 * Created by leejaebeom on 2017. 11. 7..
 */
class SettingActivity : AppCompatActivity() {
    lateinit var binding : ActivitySettingBinding
    var isMain : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        var intent = getIntent()
        isMain = intent.getBooleanExtra("isMain", false)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting)
        var fragment = SettingFragment()
        var presenter = SettingPresenter(fragment)

        binding.include?.toolbar?.setTitle(R.string.setting)
        setSupportActionBar(binding.include?.toolbar)
        supportFragmentManager.beginTransaction().replace(R.id.content, fragment, null).commit()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.confirm, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val wikis = WikiData.wikiURL.entries.count { it.value.isUse }

        if (item?.itemId == R.id.confirm){
            if (wikis > 0){
                setResult(Activity.RESULT_OK)
                finish()
            }else{
                Dialog.notice(this, R.string.title_none_wikis, R.string.message_none_wikis, null)
            }
        }
        return true
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        finish()
    }

    override fun finish() {
        if (!isMain){
            startActivity(Intent(this, MainActivity::class.java))
        }
        super.finish()
    }
}