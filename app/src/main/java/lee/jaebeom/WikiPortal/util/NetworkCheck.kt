package lee.jaebeom.WikiPortal.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by leejaebeom on 2017. 11. 9..
 */
class NetworkCheck {
    companion object {
        private lateinit var connManager : ConnectivityManager
        fun isWifiConnect(context: Context) : Boolean{
            connManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val wifi : NetworkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            return wifi.isConnected
        }
    }
}