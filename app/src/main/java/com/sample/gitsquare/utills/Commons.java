package com.sample.gitsquare.utills;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.sample.gitsquare.R;

// TODO: Auto-generated Javadoc
/**
 * The Class Commons is a utility class which provide the basic functions 
 * used in project.
 */
public class Commons
{
	private static ProgressDialog dialog;
	public static void showProgress(Context context){
		String pleaseWait = context.getString(R.string.txt_plz_wait);
		dialog = ProgressDialog.show(context, "", pleaseWait);
	}
	public static void progressDismiss() {
		if (dialog != null) {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
		}
	}
	public static void gotoURL(Context c,String url){
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		c.startActivity(intent);
	}
}
