package lee.jaebeom.WikiPortal.wiki

import android.content.Context
import lee.jaebeom.WikiPortal.util.SharedPreference
import java.util.*

/**
 * Created by leejaebeom on 2017. 11. 6..
 */
class WikiData {
    companion object {
        @JvmField val wikiURL : MutableMap<String, WikiModel> = HashMap<String, WikiModel>()
        @JvmField val useWikis : ArrayList<WikiModel> = ArrayList()
        var wikis : Int? = null;
        val Wikipedia = "wikipedia"
        val Namuwiki = "나무위키"
        val Rigvedawiki = "리그베다위키"
        val Librewiki = "리브레위키"
        val korean = "ko"
        val encyclopedia = 1
        init {
            wikiURL.put(Wikipedia, WikiModel(Wikipedia, encyclopedia,"https://ko.m.wikipedia.org/wiki/", "https://ko.m.wikipedia.org/wiki/"))
            wikiURL.put(Namuwiki, WikiModel(Namuwiki, encyclopedia, "https://namu.wiki/go/", "https://namu.wiki/w/나무위키:대문"));
            wikiURL.put(Librewiki, WikiModel(Librewiki, encyclopedia, "https://librewiki.net/wiki/", "https://librewiki.net/"));
//          wikiURL.put(Rigvedawiki, WikiModel(Rigvedawiki, encyclopedia, "http://Rigvedawiki.net/w/", "http://rigvedawiki.net"));
        }

        fun sortSequence(){
            Collections.sort(useWikis, AscendingObj())
        }

        fun orderSequence(context: Context){   //순서값 재설정
            for (i in 0 until useWikis.size){
                useWikis.get(i).sequence = i+1
                SharedPreference.savePreference("${SharedPreference.keySequence}_${WikiData.useWikis.get(i).name}", WikiData.useWikis.get(i).sequence, context)
            }
        }
        class AscendingObj : Comparator<WikiModel> {
            override fun compare(wikiModel: WikiModel, t1: WikiModel): Int {
                return wikiModel.sequence.compareTo(t1.sequence)
            }
        }
    }

}