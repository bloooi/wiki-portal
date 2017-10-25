package lee.jaebeom.WikiPortal.wiki;


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
    public final static String Librewiki = "리브레위키";

    public final static String korean = "ko";

    private final static int encyclopedia = 1;

    static{
        wikiURL = new HashMap<>();
        wikiURL.put(Wikipedia, new WikiModel(Wikipedia, encyclopedia, "https://ko.m.wikipedia.org/wiki/", "https://ko.m.wikipedia.org/wiki/", 1));
        wikiURL.put(Namuwiki, new WikiModel(Namuwiki, encyclopedia, "https://namu.wiki/go/", "https://namu.wiki/w/나무위키:대문", 2));
        wikiURL.put(Librewiki, new WikiModel(Librewiki, encyclopedia, "https://librewiki.net/wiki/", "https://librewiki.net/", 3));
//        wikiURL.put(Rigvedawiki, new WikiModel(Rigvedawiki, encyclopedia, "http://Rigvedawiki.net/w/", "http://rigvedawiki.net"));

    }
}
