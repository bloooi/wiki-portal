package lee.jaebeom.allwiki.util;

import lee.jaebeom.allwiki.wiki.WikiData;

/**
 * Created by leejaebeom on 2017. 10. 16..
 */

public class DOMAccess {
    public static String getJavascript(String wiki) {
        String javascript;
        switch (wiki){
            case WikiData.Namuwiki:
                javascript = "javascript:(function(){" +
                        "var tab = document.getElementsByClassName(\"navbar-wrapper\")[0]; tab.parentNode.removeChild(tab);" +
                        "var ad = document.getElementsByClassName(\"live-topbar-area\")[0]; ad.parentNode.removeChild(ad);" +
                        "var menu = document.getElementsByClassName(\"wiki-article-menu\")[0]; menu.parentNode.removeChild(menu);" +
                        "var title = document.getElementsByClassName(\"title\")[0]; title.parentNode.removeChild(title);" +
                        "var footer = document.getElementsByClassName(\"footer-wrapper\")[0]; footer.parentNode.removeChild(footer);" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
            case WikiData.Wikipedia:
                javascript = "javascript:(function(){" +
                        "var tab = document.getElementsByClassName(\"header-container header-chrome\")[0]; tab.parentNode.removeChild(tab);" +
                        "var ad = document.getElementsByClassName(\"banner-container\")[0]; if(ad != null)ad.parentNode.removeChild(ad);" +
                        "var title = document.getElementById(\"section_0\"); title.parentNode.removeChild(title);" +
//                        "var menu = document.getElementsByClassName(\"wiki-article-menu\")[0]; menu.parentNode.removeChild(menu);" +
//                        "var footer = document.getElementsByClassName(\"footer-wrapper\")[0]; footer.parentNode.removeChild(footer);" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
            default:
                javascript = "javascript:(function(){" +
//                        "var tab = document.getElementsByClassName(\"header-container header-chrome\")[0]; tab.parentNode.removeChild(tab);" +
//                        "var ad = document.getElementsByClassName(\"banner-container\")[0]; if(ad != null)ad.parentNode.removeChild(ad);" +
//                        "var title = document.getElementById(\"section_0\"); title.parentNode.removeChild(title);" +
//                        "var menu = document.getElementsByClassName(\"wiki-article-menu\")[0]; menu.parentNode.removeChild(menu);" +
//                        "var footer = document.getElementsByClassName(\"footer-wrapper\")[0]; footer.parentNode.removeChild(footer);" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
        }
        return javascript;
    }
}
