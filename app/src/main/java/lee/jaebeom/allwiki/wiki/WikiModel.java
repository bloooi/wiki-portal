package lee.jaebeom.allwiki.wiki;

/**
 * Created by leejaebeom on 2017. 10. 18..
 */

public class WikiModel {
    private int category;

    private String name;
    private String URL;
    private String language;
    private boolean isUse;
    private int order; //순서
    private String frontURL;

    public WikiModel(String name, int category, String URL, String frontURL, String language) {
        this.name = name;
        this.category = category;
        this.URL = URL;
        this.frontURL = frontURL;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public String getURL() {
        return URL;
    }

    public String getFrontURL() {
        return frontURL;
    }

    public String getLanguage() {
        return language;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }
}
