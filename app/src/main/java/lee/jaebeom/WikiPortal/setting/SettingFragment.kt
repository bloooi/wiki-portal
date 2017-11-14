package lee.jaebeom.WikiPortal.setting

import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.preference.*
import android.util.Log
import lee.jaebeom.WikiPortal.R
import lee.jaebeom.WikiPortal.util.SharedPreference
import lee.jaebeom.WikiPortal.wiki.WikiData
import lee.jaebeom.WikiPortal.wiki.WikiModel

/**
 * Created by leejaebeom on 2017. 11. 7..
 */
class SettingFragment : PreferenceFragmentCompat(), SettingContract.View, SharedPreferences.OnSharedPreferenceChangeListener {
    lateinit var presenter: SettingContract.Presenter
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pref_setting)
        val category = findPreference("setting_title") as PreferenceCategory
        for (entry: Map.Entry<String, WikiModel> in WikiData.wikiURL.entries){
            val chechBox = CheckBoxPreference(this.activity)
            chechBox.title = entry.key
            chechBox.key = entry.key
            chechBox.isChecked = false
            chechBox.onPreferenceClickListener = object : Preference.OnPreferenceClickListener{
                override fun onPreferenceClick(preference: Preference?): Boolean {
                    val checkBox: CheckBoxPreference = preference as CheckBoxPreference
                    if (chechBox.isChecked){
                        entry.value.sequence = WikiData.useWikis.size + 1
                        SharedPreference.savePreference("${SharedPreference.keySequence}_${entry.key}", entry.value.sequence, context)
                        WikiData.useWikis.add(entry.value)
                    }else{
                        entry.value.sequence = 0
                        SharedPreference.savePreference("${SharedPreference.keySequence}_${entry.key}", entry.value.sequence, context)
                        WikiData.useWikis.remove(entry.value)   //사용하는 위키에서 제거
                        WikiData.orderSequence(context)   //순서 값 재설정
                    }
                    return true
                }
            }
            category.addPreference(chechBox)
        }

        val listPreference  = findPreference("wikipedia_language_setting") as ListPreference
        listPreference.summary = listPreference.entries[
                listPreference.findIndexOfValue(
                        SharedPreference.getStringPreferences(context, "wikipedia_language_setting")
                )].toString()

        listPreference.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
            var listPreference = preference as ListPreference
            var langKey = listPreference.entries[listPreference.findIndexOfValue(newValue.toString())].toString()
            var langValue = newValue.toString()

            listPreference.summary = langKey
            WikiData.wikiURL.get(WikiData.Wikipedia)?.language = langValue
            WikiData.wikiURL.get(WikiData.Wikipedia)?.setChangeURLLanguage(langValue)
            Log.d("listPreference", newValue.toString())

            true
        }

        val wikiSequencePreference = findPreference("wiki_sequence")


    }

    override fun setDivider(divider: Drawable?) {
        super.setDivider(ColorDrawable(Color.TRANSPARENT))
    }

    override fun setDividerHeight(height: Int) {
        super.setDividerHeight(0)
    }
    override fun _presenter(presenter: SettingContract.Presenter) {
        this.presenter = presenter
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        if (!p1.equals("wikipedia_language_setting")){
            WikiData.wikiURL.get(p1)?.isUse = p0!!.getBoolean(p1, false)
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}