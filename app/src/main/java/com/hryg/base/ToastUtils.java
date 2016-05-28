package com.hryg.base;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperToast;

public class ToastUtils {

	public static void showSuperToastComment(Context context, CharSequence text) {
		SuperToast superToast = new SuperToast(context);
		superToast.setText(text);
		superToast.setGravity(Gravity.BOTTOM, 0, 80);
		superToast.setAnimations(SuperToast.Animations.FLYIN);
		superToast.setDuration(SuperToast.Duration.VERY_SHORT);
		superToast.setBackground(SuperToast.Background.BLUE);
		superToast.setTextColor(Color.WHITE);
		superToast.show();
	}

	public static void showSuperToastAlert(Context context, CharSequence text) {
		SuperToast superToast = new SuperToast(context);
		superToast.setText(text);
		superToast.setGravity(Gravity.BOTTOM, 0, 200);
		superToast.setAnimations(SuperToast.Animations.SCALE);
		superToast.setDuration(SuperToast.Duration.VERY_SHORT);
		superToast.setBackground(SuperToast.Background.RED);
		superToast.setTextColor(Color.WHITE);
		superToast.setTextSize(SuperToast.TextSize.SMALL);
		superToast.show();
	}
	
	public static void showSuperToastAlertGreen(Context context, CharSequence text) {
		SuperToast superToast = new SuperToast(context);
		superToast.setText(text);
		superToast.setGravity(Gravity.CENTER, 0, 200);
		superToast.setAnimations(SuperToast.Animations.FLYIN);
		superToast.setDuration(SuperToast.Duration.VERY_SHORT);
		superToast.setBackground(SuperToast.Background.GREEN);
		superToast.setTextColor(Color.WHITE);
		superToast.setTextSize(SuperToast.TextSize.SMALL);
		superToast.show();
	}

	public static void toast(Context context, CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}

	public static void toast(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

	public static void toastLong(Context context, CharSequence text) {
		Toast.makeText(context, text, Toast.LENGTH_LONG).show();
	}

	public static void toastLong(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
	}

	public static Toast toast(Context context, CharSequence text, Toast toast) {
		if (toast != null) {
			toast.setText(text);
		} else {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		}
		toast.show();
		return toast;
	}

	public static Toast toast(Context context, int resId, Toast toast) {
		return toast(context, context.getString(resId), toast);
	}
}
