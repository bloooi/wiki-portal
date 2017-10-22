package lee.jaebeom.allwiki.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import lee.jaebeom.allwiki.R;

/**
 * Created by leejaebeom on 2017. 10. 18..
 */

public class Dialog {
    public static void notice(Context context, String title, String message){
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.confirm, null)
                .create()
                .show();
    }

    public static void notice(Context context, int titleID, int messageID){
        new AlertDialog.Builder(context)
                .setTitle(titleID)
                .setMessage(messageID)
                .setPositiveButton(R.string.confirm, null)
                .create()
                .show();
    }

}
