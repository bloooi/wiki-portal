/*
package lee.jaebeom.WikiPortal.wiki;

public class WikiModel {
    private int category;

    private String name;
    public String URL;
    private String language;
    private boolean isUse;
    private int order; //순서
    private String frontURL;

    public WikiModel(String name, int category, String URL, String frontURL, int order) {
        this.name = name;
        this.category = category;
        this.URL = URL;
        this.frontURL = frontURL;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getCategory() {
        return category;
    }

    public void setChangeURLLanguage(String lang) {
        StringBuffer buffer = new StringBuffer(this.URL);
        buffer.replace(8, 10, lang);
        this.URL = buffer.toString();
        this.frontURL = buffer.toString();
    }

    public String getURL() {
        return URL;
    }

    public String getFrontURL() {
        return frontURL;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public int getOrder() {
        return order;
    }
}

*/
