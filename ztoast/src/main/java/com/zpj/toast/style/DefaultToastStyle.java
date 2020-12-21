package com.zpj.toast.style;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zpj.toast.AbstractToastStyle;
import com.zpj.toast.R;
import com.zpj.toast.ZToast;

public class DefaultToastStyle extends AbstractToastStyle {

    private int iconStart;
    private int textColor = Color.BLACK;
    private int font;

    private float textSize = 12;
    private boolean textBold;

    @Override
    public int getLayoutId() {
        return R.layout._ztoast_layout;
    }

    @Override
    public void onCreateView(Context context, View rootView, String text, ZToast.ToastType type) {
        TextView tvText = findViewById(rootView, R.id.tv_text);
        ImageView tvIcon = findViewById(rootView, R.id.tv_icon);
        switch (type) {
            case SUCCESS:
                iconStart = R.drawable.ic_check_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_success);
                break;
            case ERROR:
                iconStart = R.drawable.ic_clear_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_error);
                break;
            case WARNING:
                iconStart = R.drawable.ic_warning_white;
                textColor = Color.WHITE;
                rootView.setBackgroundResource(R.drawable.bg_toast_warning);
                break;
            default:
                iconStart = R.drawable.ic_info_white;
                textColor = Color.BLACK;
                rootView.setBackgroundResource(R.drawable.bg_toast_normal);
                break;
        }
        tvText.setText(text);
        tvText.setTextColor(textColor);
        tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        tvIcon.setImageResource(iconStart);
        tvIcon.setColorFilter(textColor);
    }

    public DefaultToastStyle setIconStart(int iconStart) {
        this.iconStart = iconStart;
        return this;
    }

    public DefaultToastStyle setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public DefaultToastStyle setFont(int font) {
        this.font = font;
        return this;
    }

    public DefaultToastStyle setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public DefaultToastStyle setTextBold(boolean textBold) {
        this.textBold = textBold;
        return this;
    }
}
