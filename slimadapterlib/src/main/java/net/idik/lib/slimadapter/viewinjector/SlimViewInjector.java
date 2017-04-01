package net.idik.lib.slimadapter.viewinjector;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by linshuaibin on 29/12/2016.
 */

public class SlimViewInjector implements IViewInjector<SlimViewInjector> {

    private View root;

    private SlimViewInjector(View root) {
        this.root = root;
    }

    public static SlimViewInjector create(View root) {
        return new SlimViewInjector(root);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return (T) root.findViewById(id);
    }

    @Override
    public SlimViewInjector tag(int id, Object object) {
        findViewById(id).setTag(object);
        return this;
    }

    @Override
    public SlimViewInjector text(int id, int res) {
        TextView view = findViewById(id);
        view.setText(res);
        return this;
    }

    @Override
    public SlimViewInjector text(int id, CharSequence charSequence) {
        TextView view = findViewById(id);
        view.setText(charSequence);
        return this;
    }

    @Override
    public SlimViewInjector textColor(int id, int color) {
        TextView view = findViewById(id);
        view.setTextColor(color);
        return this;
    }

    @Override
    public SlimViewInjector textSize(int id, int sp) {
        TextView view = findViewById(id);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    @Override
    public SlimViewInjector image(int id, int res) {
        ImageView view = findViewById(id);
        view.setImageResource(res);
        return this;
    }

    @Override
    public SlimViewInjector image(int id, Drawable drawable) {
        ImageView view = findViewById(id);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public SlimViewInjector background(int id, int res) {
        View view = findViewById(id);
        view.setBackgroundResource(res);
        return this;
    }

    @Override
    public SlimViewInjector background(int id, Drawable drawable) {
        View view = findViewById(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    @Override
    public SlimViewInjector visible(int id) {
        findViewById(id).setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public SlimViewInjector invisible(int id) {
        findViewById(id).setVisibility(View.INVISIBLE);
        return this;
    }

    @Override
    public SlimViewInjector gone(int id) {
        findViewById(id).setVisibility(View.GONE);
        return this;
    }

    @Override
    public <V extends View> SlimViewInjector with(int id, Action<V> action) {
        action.action((V) findViewById(id));
        return this;
    }

    @Override
    public SlimViewInjector clicked(int id, View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
        return this;
    }

    @Override
    public SlimViewInjector longClicked(int id, View.OnLongClickListener listener) {
        findViewById(id).setOnLongClickListener(listener);
        return this;
    }

    @Override
    public SlimViewInjector enable(int id) {
        findViewById(id).setEnabled(true);
        return this;
    }

    @Override
    public SlimViewInjector disable(int id) {
        findViewById(id).setEnabled(false);
        return this;
    }

    @Override
    public SlimViewInjector checked(int id, boolean checked) {
        Checkable checkable = findViewById(id);
        checkable.setChecked(checked);
        return this;
    }

    @Override
    public SlimViewInjector adapter(int id, RecyclerView.Adapter adapter) {
        RecyclerView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SlimViewInjector adapter(int id, Adapter adapter) {
        AdapterView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SlimViewInjector layoutManager(int id, RecyclerView.LayoutManager layoutManager) {
        RecyclerView view = findViewById(id);
        view.setLayoutManager(layoutManager);
        return this;
    }
}
