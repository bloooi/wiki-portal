package lee.jaebeom.allwiki.wiki;


import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by leejaebeom on 2017. 10. 16..
 */

public class WikiData {

    public static final Map<String, WikiModel> wikiURL;
    public static int wikis;

    public final static String Wikipedia = "wikipedia";
    public final static String Namuwiki = "나무위키";
    public final static String Rigvedawiki = "리그베다위키";

    private final static String korean = "ko";

    private final static int encyclopedia = 1;

    static{
        wikiURL = new HashMap<>();
        wikiURL.put(Wikipedia, new WikiModel(Wikipedia, encyclopedia, "https://ko.m.wikipedia.org/wiki/", "https://ko.m.wikipedia.org/wiki/위키백과:대문", korean));
        wikiURL.put(Namuwiki, new WikiModel(Namuwiki, encyclopedia, "https://namu.wiki/w/","https://namu.wiki/w/나무위키:대문", korean));
        wikiURL.put(Rigvedawiki, new WikiModel(Rigvedawiki, encyclopedia, "http://Rigvedawiki.net/w/", "http://rigvedawiki.net", korean));

    }
}
