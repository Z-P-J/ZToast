package com.zpj.toast;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.zpj.toast.style.DefaultToastStyle;
import com.zpj.utils.ContextUtils;

public class ZToast {

    public enum ToastType {
        NORMAL, SUCCESS, ERROR, WARNING
    }

    private final Context mContext;
    private Toast toast;

    private String text;

    private static AbstractToastStyle mDefaultToastStyle;

    private AbstractToastStyle mToastStyle;
    private int gravity = Gravity.BOTTOM;
    private int duration = Toast.LENGTH_SHORT;
    private ToastType toastType = ToastType.NORMAL;

    private ZToast(Context context) {
        this.mContext = context;
    }

    public static void init(AbstractToastStyle style) {
        mDefaultToastStyle = style;
    }

    public static ZToast with(Context context) {
        return new ZToast(context);
    }

    public static void normal(String text) {
        normal(ContextUtils.getApplicationContext(), text).show();
    }

    public static void success(String text) {
        success(ContextUtils.getApplicationContext(), text).show();
    }

    public static void error(String text) {
        error(ContextUtils.getApplicationContext(), text).show();
    }

    public static void warning(String text) {
        warning(ContextUtils.getApplicationContext(), text).show();
    }

    public static void normal(int msgId) {
        normal(ContextUtils.getApplicationContext().getString(msgId));
    }

    public static void success(int msgId) {
        success(ContextUtils.getApplicationContext().getString(msgId));
    }

    public static void error(int msgId) {
        error(ContextUtils.getApplicationContext().getString(msgId));
    }

    public static void warning(int msgId) {
        warning(ContextUtils.getApplicationContext().getString(msgId));
    }

    public static ZToast normal(Context context, String text) {
        return with(context).setText(text).setToastType(ToastType.NORMAL);
    }

    public static ZToast success(Context context, String text) {
        return with(context).setText(text).setToastType(ToastType.SUCCESS);
    }

    public static ZToast error(Context context, String text) {
        return with(context).setText(text).setToastType(ToastType.ERROR);
    }

    public static ZToast warning(Context context, String text) {
        return with(context).setText(text).setToastType(ToastType.WARNING);
    }

    public ZToast setText(String text) {
        this.text = text;
        return this;
    }

    public ZToast setToastType(ToastType toastType) {
        this.toastType = toastType;
        return this;
    }

    public ZToast setToastStyle(AbstractToastStyle mToastStyle) {
        this.mToastStyle = mToastStyle;
        return this;
    }

    public ZToast setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public ZToast setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public ZToast show() {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        if (mToastStyle == null) {
            mToastStyle = mDefaultToastStyle;
        }
        if (mToastStyle == null) {
            mToastStyle = new DefaultToastStyle();
        }
        toast = new Toast(mContext);
        toast.setGravity(gravity, 0, gravity == Gravity.CENTER ? 0 : toast.getYOffset());
        toast.setDuration(duration == Toast.LENGTH_LONG ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.setView(mToastStyle.onCreate(mContext, text, toastType));
        toast.show();
        return this;
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
