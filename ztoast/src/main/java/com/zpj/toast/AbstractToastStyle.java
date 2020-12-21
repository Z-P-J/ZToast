package com.zpj.toast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public abstract class AbstractToastStyle {



    View onCreate(Context context, String text, ZToast.ToastType type) {
        View rootView = LayoutInflater.from(context).inflate(getLayoutId(), null, false);
        onCreateView(context, rootView, text, type);
        return rootView;
    }

    protected final <T extends View> T findViewById(View view, int id) {
        return view.findViewById(id);
    }

    protected abstract int getLayoutId();

    protected abstract void onCreateView(Context context, View rootView, String text, ZToast.ToastType type);

}
