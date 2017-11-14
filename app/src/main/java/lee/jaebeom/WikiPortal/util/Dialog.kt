package lee.jaebeom.WikiPortal.util

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import lee.jaebeom.WikiPortal.R

/**
 * Created by leejaebeom on 2017. 11. 9..
 */
class Dialog {
    companion object {
        fun notice(context: Context, title: String, message: String, listener: DialogInterface.OnClickListener?){
            AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(message)
                    .setPositiveButton(R.string.confirm, listener)
                    .create()
                    .show();
        }

        fun notice(context: Context, titleID: Int, messageID: Int, listener: DialogInterface.OnClickListener?){
            AlertDialog.Builder(context)
                    .setTitle(titleID)
                    .setMessage(messageID)
                    .setPositiveButton(R.string.confirm, listener)
                    .create()
                    .show();
        }
    }
}