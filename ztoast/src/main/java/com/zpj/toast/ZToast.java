package com.zpj.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.zpj.toast.style.DefaultToastStyle;
import com.zpj.utils.ContextUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.LENGTH_SHORT;

public class ZToast {

    public static final int NORMAL = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int WARNING = 3;

    @IntDef(value = {
            NORMAL,
            SUCCESS,
            ERROR,
            WARNING
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {}

    @IntDef(value = {
            LENGTH_SHORT,
            LENGTH_LONG
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {}

    private final Context mContext;
    private Toast toast;

    private String text;

    private static AbstractToastStyle mDefaultToastStyle;

    private AbstractToastStyle mToastStyle;
    private int gravity = Gravity.BOTTOM;
    private int duration = LENGTH_SHORT;
    private int toastType = NORMAL;
    private int xOffset = 0;
    private int yOffset = -1;

    private ZToast(Context context) {
        this.mContext = context;
    }

    public static void init(AbstractToastStyle style) {
        mDefaultToastStyle = style;
    }

    public static ZToast with(Context context) {
        return new ZToast(context);
    }

    public static ZToast with(String text) {
        return with(ContextUtils.getApplicationContext()).setText(text);
    }

    public static ZToast with(int textId) {
        return with(ContextUtils.getApplicationContext()).setText(textId);
    }

    public static void normal(String text) {
        with(text).setToastType(NORMAL).show();
    }

    public static void success(String text) {
        with(text).setToastType(SUCCESS).show();
    }

    public static void error(String text) {
        with(text).setToastType(ERROR).show();
    }

    public static void warning(String text) {
        with(text).setToastType(WARNING).show();
    }

    public static void normal(int msgId) {
        with(msgId).setToastType(NORMAL).show();
    }

    public static void success(int msgId) {
        with(msgId).setToastType(SUCCESS).show();
    }

    public static void error(int msgId) {
        with(msgId).setToastType(ERROR).show();
    }

    public static void warning(int msgId) {
        with(msgId).setToastType(WARNING).show();
    }

    public static ZToast normal(Context context, String text) {
        return with(context).setText(text).setToastType(NORMAL);
    }

    public static ZToast success(Context context, String text) {
        return with(context).setText(text).setToastType(SUCCESS);
    }

    public static ZToast error(Context context, String text) {
        return with(context).setText(text).setToastType(ERROR);
    }

    public static ZToast warning(Context context, String text) {
        return with(context).setText(text).setToastType(WARNING);
    }

    public ZToast setText(String text) {
        this.text = text;
        return this;
    }

    public ZToast setText(int textId) {
        this.text = mContext.getString(textId);
        return this;
    }

    public ZToast setToastType(@Type int toastType) {
        this.toastType = toastType;
        return this;
    }

    public ZToast normal() {
        return setToastType(NORMAL);
    }

    public ZToast success() {
        return setToastType(SUCCESS);
    }

    public ZToast error() {
        return setToastType(ERROR);
    }

    public ZToast warning() {
        return setToastType(WARNING);
    }

    public ZToast setToastStyle(AbstractToastStyle mToastStyle) {
        this.mToastStyle = mToastStyle;
        return this;
    }

    public ZToast setGravity(int gravity) {
        this.gravity = gravity;
        return this;
    }

    public ZToast setGravity(int gravity, int xOffset, int yOffset) {
        this.gravity = gravity;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        return this;
    }

    public ZToast setXOffset(int xOffset) {
        this.xOffset = xOffset;
        return this;
    }

    public ZToast setYOffset(int yOffset) {
        this.yOffset = yOffset;
        return this;
    }

    public ZToast setDuration(@Duration int duration) {
        this.duration = duration;
        return this;
    }

    public ZToast show() {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            showInMain();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    showInMain();
                }
            });
        }
        return this;
    }

    private void showInMain() {
        if (mToastStyle == null) {
            mToastStyle = mDefaultToastStyle;
        }
        if (mToastStyle == null) {
            mToastStyle = new DefaultToastStyle();
        }
        toast = new Toast(mContext);
        if (yOffset < 0) {
            if (gravity == Gravity.CENTER) {
                yOffset = 0;
            } else {
                yOffset = toast.getYOffset();
            }
        }
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setDuration(duration); //  == LENGTH_LONG ? LENGTH_LONG : LENGTH_SHORT
        toast.setView(mToastStyle.onCreate(mContext, text, toastType));
        toast.show();
    }

    public void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }

}
