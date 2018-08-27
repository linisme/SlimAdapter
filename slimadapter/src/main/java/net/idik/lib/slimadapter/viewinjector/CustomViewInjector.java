package net.idik.lib.slimadapter.viewinjector;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

/**
 * Created by linshuaibin on 22/12/2016.
 */

public abstract class CustomViewInjector<CVI extends IViewInjector> implements IViewInjector<CVI> {

    private IViewInjector wrappedInjector;

    public CustomViewInjector(@NonNull IViewInjector wrappedInjector) {
        this.wrappedInjector = wrappedInjector;
    }

    @Override
    public CVI tag(int id, Object object) {
        wrappedInjector.tag(id, object);
        return (CVI) this;
    }

    @Override
    public CVI text(int id, int res) {
        wrappedInjector.text(id, res);
        return (CVI) this;
    }

    @Override
    public CVI text(int id, CharSequence charSequence) {
        wrappedInjector.text(id, charSequence);
        return (CVI) this;
    }

    @Override
    public CVI typeface(int id, Typeface typeface, int style) {
        wrappedInjector.typeface(id, typeface, style);
        return (CVI) this;
    }

    @Override
    public CVI typeface(int id, Typeface typeface) {
        wrappedInjector.typeface(id, typeface);
        return (CVI) this;
    }

    @Override
    public CVI textColor(int id, int color) {
        wrappedInjector.textColor(id, color);
        return (CVI) this;
    }

    @Override
    public CVI textSize(int id, int sp) {
        wrappedInjector.textSize(id, sp);
        return (CVI) this;
    }

    @Override
    public CVI image(int id, int res) {
        wrappedInjector.image(id, res);
        return (CVI) this;
    }

    @Override
    public CVI image(int id, Drawable drawable) {
        wrappedInjector.image(id, drawable);
        return (CVI) this;
    }

    @Override
    public CVI background(int id, int res) {
        wrappedInjector.background(id, res);
        return (CVI) this;
    }

    @Override
    public CVI background(int id, Drawable drawable) {
        wrappedInjector.background(id, drawable);
        return (CVI) this;
    }

    @Override
    public CVI visible(int id) {
        wrappedInjector.visible(id);
        return (CVI) this;
    }

    @Override
    public CVI invisible(int id) {
        wrappedInjector.invisible(id);
        return (CVI) this;
    }

    @Override
    public CVI alpha(int id, float alpha) {
        wrappedInjector.alpha(id, alpha);
        return (CVI) this;
    }

    @Override
    public CVI visibility(int id, int visibility) {
        wrappedInjector.visibility(id, visibility);
        return (CVI) this;
    }

    @Override
    public CVI gone(int id) {
        wrappedInjector.gone(id);
        return (CVI) this;
    }

    @Override
    public <V extends View> CVI with(int id, Action<V> action) {
        wrappedInjector.with(id, action);
        return (CVI) this;
    }

    @Override
    public CVI clicked(int id, View.OnClickListener listener) {
        wrappedInjector.clicked(id, listener);
        return (CVI) this;
    }

    @Override
    public CVI longClicked(int id, View.OnLongClickListener listener) {
        wrappedInjector.longClicked(id, listener);
        return (CVI) this;
    }

    @Override
    public CVI enable(int id, boolean enable) {
        return null;
    }

    @Override
    public CVI enable(int id) {
        wrappedInjector.enable(id);
        return (CVI) this;
    }

    @Override
    public CVI disable(int id) {
        wrappedInjector.disable(id);
        return (CVI) this;
    }

    @Override
    public CVI adapter(int id, RecyclerView.Adapter adapter) {
        wrappedInjector.adapter(id, adapter);
        return (CVI) this;
    }

    @Override
    public CVI checked(int id, boolean checked) {
        wrappedInjector.checked(id, checked);
        return (CVI) this;
    }

    @Override
    public CVI selected(int id, boolean selected) {
        return null;
    }

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public CVI activated(int id, boolean activated) {
        return null;
    }

    @Override
    public CVI pressed(int id, boolean pressed) {
        return null;
    }

    @Override
    public CVI adapter(int id, Adapter adapter) {
        wrappedInjector.adapter(id, adapter);
        return (CVI) this;
    }

    @Override
    public CVI layoutManager(int id, RecyclerView.LayoutManager layoutManager) {
        wrappedInjector.layoutManager(id, layoutManager);
        return (CVI) this;
    }

    @Override
    public CVI addView(int id, View... views) {
        wrappedInjector.addView(id, views);
        return (CVI) this;
    }

    @Override
    public CVI addView(int id, View view, ViewGroup.LayoutParams params) {
        wrappedInjector.addView(id, view, params);
        return (CVI) this;
    }

    @Override
    public CVI removeAllViews(int id) {
        wrappedInjector.removeAllViews(id);
        return (CVI) this;
    }

    @Override
    public CVI removeView(int id, View view) {
        wrappedInjector.removeView(id, view);
        return (CVI) this;
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return (T) wrappedInjector.findViewById(id);
    }

}
