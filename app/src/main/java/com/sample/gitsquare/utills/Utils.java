package com.sample.gitsquare.utills;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;

import com.sample.gitsquare.R;


// TODO: Auto-generated Javadoc
/**
 * The Class Utils is also a utility class provide 
 * the basic operations in the app.
 */
public class Utils
{

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param btn1 the btn1
	 * @param btn2 the btn2
	 * @param listener1 the listener1
	 * @param listener2 the listener2
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg, String btn1,
			String btn2, DialogInterface.OnClickListener listener1,
			DialogInterface.OnClickListener listener2)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		// builder.setTitle(R.string.app_name);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton(btn1, listener1);
		if (btn2 != null && listener2 != null)
			builder.setNegativeButton(btn2, listener2);

		AlertDialog alert = builder.create();
		alert.show();
		return alert;

	}


	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param btn1 the btn1
	 * @param btn2 the btn2
	 * @param listener1 the listener1
	 * @param listener2 the listener2
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg, int btn1,
			int btn2, DialogInterface.OnClickListener listener1,
			DialogInterface.OnClickListener listener2)
	{

		return showDialog(ctx, ctx.getString(msg), ctx.getString(btn1),
				ctx.getString(btn2), listener1, listener2);

	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param btn1 the btn1
	 * @param btn2 the btn2
	 * @param listener the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg, String btn1,
			String btn2, DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, msg, btn1, btn2, listener,
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id)
					{

						dialog.dismiss();
					}
				});

	}
	
	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param btn1 the btn1
	 * @param btn2 the btn2
	 * @param listener the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg, int btn1,
			int btn2, DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, ctx.getString(msg), ctx.getString(btn1),
				ctx.getString(btn2), listener);

	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param listener the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg,
			DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, msg, ctx.getString(android.R.string.ok), null,
				listener, null);
	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @param listener the listener
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg,
			DialogInterface.OnClickListener listener)
	{

		return showDialog(ctx, ctx.getString(msg),
				ctx.getString(android.R.string.ok), null, listener, null);
	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, String msg)/////hello
	{

		return showDialog(ctx, msg, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int id)
			{

				dialog.dismiss();
			}
		});

	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param msg the msg
	 * @return the alert dialog
	 */
	public static AlertDialog showDialog(Context ctx, int msg)
	{

		return showDialog(ctx, ctx.getString(msg));

	}

	/**
	 * Show dialog.
	 *
	 * @param ctx the ctx
	 * @param title the title
	 * @param msg the msg
	 * @param listener the listener
	 */
	public static void showDialog(Context ctx, int title, int msg,
			DialogInterface.OnClickListener listener)
	{

		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setMessage(msg).setCancelable(false)
				.setPositiveButton(android.R.string.ok, listener);
		builder.setTitle(title);
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public static void noInternetDialog(Context ctx)
	{
		showDialog(ctx, ctx.getString(R.string.txt_no_connection));
	}


	/**
	 * Checks if is online.
	 *
	 * @param ctx the ctx
	 * @return true, if is online
	 */
	public static final boolean isOnline(Context ctx)
	{
		ConnectivityManager conMgr = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conMgr.getActiveNetworkInfo() != null
		&& conMgr.getActiveNetworkInfo().isAvailable()
		&& conMgr.getActiveNetworkInfo().isConnected())
			return true;
		return false;
	}
}
