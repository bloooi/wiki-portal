package lee.jaebeom.WikiPortal.wiki

data class WikiModel(val name: String, val category: Int, var URL: String, var frontURL: String) {
    var language: String? = null
    var isUse: Boolean = false
    var sequence: Int = 0
    fun setChangeURLLanguage(lang: String) {
        val buffer = StringBuffer(this.URL)
        buffer.replace(8, 10, lang)
        this.URL = buffer.toString()
        this.frontURL = buffer.toString()
    }
}