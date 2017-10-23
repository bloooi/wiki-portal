package lee.jaebeom.WikiPortal.util;

import lee.jaebeom.WikiPortal.wiki.WikiData;

/**
 * Created by leejaebeom on 2017. 10. 16..
 */

public class DOMAccess {
    public static String getJavascript(String wiki) {
        String javascript;
        switch (wiki){
            case WikiData.Namuwiki:
                javascript = "javascript:(function(){" +
//                        "var tab = document.getElementsByClassName(\"navbar-wrapper\")[0]; tab.parentNode.removeChild(tab);" +
//                        "var ad = document.getElementsByClassName(\"live-topbar-area\")[0]; ad.parentNode.removeChild(ad);" +
//                        "var menu = document.getElementsByClassName(\"wiki-article-menu\")[0]; menu.parentNode.removeChild(menu);" +
//                        "var title = document.getElementsByClassName(\"title\")[0]; title.parentNode.removeChild(title);" +
//                        "var footer = document.getElementsByClassName(\"footer-wrapper\")[0]; footer.parentNode.removeChild(footer);" +
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
            case WikiData.Rigvedawiki:
                javascript = "javascript:(function(){" +
                        "var tab = document.getElementsByClassName(\"header\")[0]; tab.parentNode.removeChild(tab);" +
                        "var tab = document.getElementsByTagName(\"header\")[0]; tab.parentNode.removeChild(tab);" +
                        "var tabPadding = document.getElementsByTagName(\"div\")[0]; tabPadding.parentNode.removeChild(tabPadding);" +
                        "var wikiIcon = document.getElementById(\"wikiIcon\"); wikiIcon.parentNode.removeChild(wikiIcon);" +
                        "var btnGroup = document.getElementsByClassName(\"btn-group\")[0]; btnGroup.parentNode.removeChild(btnGroup);" +
                        "var title = document.getElementsByClassName(\"wikiTitle entry-title\")[0]; title.parentNode.removeChild(title);" +
                        "var aside = document.getElementsByTagName(\"aside\")[0]; aside.parentNode.removeChild(aside);" +
                        "var footer = document.getElementsByTagName(\"footer\")[0]; footer.parentNode.removeChild(footer);" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
            case WikiData.Librewiki:
                javascript = "javascript:(function(){" +
                        "var tab = document.getElementsByTagName(\"header\")[0]; tab.parentNode.removeChild(tab);" +
                        "var top = document.getElementsByClassName(\"liberty-content-header\")[0]; top.parentNode.removeChild(top);" +
                        "var social = document.getElementsByClassName(\"social-buttons\")[0]; social.parentNode.removeChild(social);" +
                        "var ad = document.getElementsByClassName(\"bottom-ads\")[0]; if(ad != null)ad.parentNode.removeChild(ad);" +
                        "var margin = document.getElementsByClassName(\"content-wrapper\")[0]; margin.style.marginTop = 0;" +
//                        "var tab = document.getElementsByTagName(\"header\")[0]; tab.parentNode.removeChild(tab);" +
//                        "var wikiIcon = document.getElementById(\"wikiIcon\"); wikiIcon.parentNode.removeChild(wikiIcon);" +
//                        "var btnGroup = document.getElementsByClassName(\"btn-group\")[0]; btnGroup.parentNode.removeChild(btnGroup);" +
//                        "var title = document.getElementsByClassName(\"wikiTitle entry-title\")[0]; title.parentNode.removeChild(title);" +
//                        "var aside = document.getElementsByTagName(\"aside\")[0]; aside.parentNode.removeChild(aside);" +
//                        "var footer = document.getElementsByTagName(\"footer\")[0]; footer.parentNode.removeChild(footer);" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
            default:
                javascript = "javascript:(function(){" +
                        "window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>')" +
                        "})()";
                break;
        }
        return javascript;
    }
}
