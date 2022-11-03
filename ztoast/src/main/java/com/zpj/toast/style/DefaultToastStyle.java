package com.zpj.toast.style;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.zpj.toast.AbstractToastStyle;
import com.zpj.toast.R;
import com.zpj.toast.ZToast;
import com.zpj.utils.ScreenUtils;

public class DefaultToastStyle extends AbstractToastStyle {

    private int iconStart;
    private int textColor = Color.BLACK;

    private float textSize = 12;

    @Override
    public int getLayoutId() {
        return R.layout._ztoast_layout;
    }

    @Override
    public void onCreateView(Context context, View rootView, String text, @ZToast.Type int type) {
        TextView tvText = findViewById(rootView, R.id.tv_text);
//        ImageView tvIcon = findViewById(rootView, R.id.tv_icon);
        switch (type) {
            case ZToast.SUCCESS:
                iconStart = R.drawable.ic_check_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_success);
                break;
            case ZToast.ERROR:
                iconStart = R.drawable.ic_clear_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_error);
                break;
            case ZToast.WARNING:
                iconStart = R.drawable.ic_warning_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_warning);
                break;
            case ZToast.NORMAL:
            default:
                iconStart = R.drawable.ic_info_white;
                textColor = Color.BLACK;
                rootView.setBackgroundResource(R.drawable.bg_toast_normal);
                break;
        }
        tvText.setText(text);
        tvText.setTextColor(textColor);
        tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        Drawable drawable = context.getResources().getDrawable(iconStart);
        int size = ScreenUtils.dp2pxInt(context, 24);
        drawable.setBounds(0, 0, size, size);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable, ColorStateList.valueOf(textColor));
        tvText.setCompoundDrawablesRelative(wrappedDrawable, null, null, null);
    }

    public DefaultToastStyle setIconStart(int iconStart) {
        this.iconStart = iconStart;
        return this;
    }

    public DefaultToastStyle setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public DefaultToastStyle setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

}
