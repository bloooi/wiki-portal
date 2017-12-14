package lee.jaebeom.WikiPortal

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceManager
import lee.jaebeom.WikiPortal.main.MainActivity
import lee.jaebeom.WikiPortal.setting.SettingActivity
import lee.jaebeom.WikiPortal.util.SharedPreference
import lee.jaebeom.WikiPortal.wiki.WikiData
import lee.jaebeom.WikiPortal.wiki.WikiModel

/**
 * Created by leejaebeom on 2017. 11. 9..
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var wikis = 0
        var sharedPref : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        WikiData.clearUseWikis()
        for (entry : Map.Entry<String, WikiModel> in WikiData.wikiURL.entries){
            entry.value.isUse = sharedPref.getBoolean(entry.key, false) //사용 하는 위키
            var lang : String  = sharedPref.getString(entry.key+ "_language_setting", WikiData.korean)  //언어 설정
            entry.value.language = lang
            if (entry.value.equals(WikiData.Wikipedia)){
                entry.value.setChangeURLLanguage(lang)  //언어에 따른 위키피디아 URL 변경
            }

            if (entry.value.isUse){
                WikiData.useWikis.add(entry.value)
                wikis++
            }

            entry.value.sequence = sharedPref.getInt("${SharedPreference.keySequence}_${entry.key}", 0)
        }

//        lateinit var activityIntent : Intent //1.2부터 가능
        var activityIntent : Intent?
        if (wikis == 0){
            activityIntent = Intent(this, SettingActivity::class.java)
        }else{
            activityIntent = Intent(this, MainActivity::class.java)
        }

        startActivity(activityIntent)
        finish()


        overridePendingTransition(0, 0)
    }
}