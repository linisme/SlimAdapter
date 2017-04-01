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

import net.idik.lib.slimadapter.SlimViewHolder;


/**
 * Created by linshuaibin on 22/12/2016.
 */
public class SlimAdapterDefaultViewInjector implements IViewInjector<SlimAdapterDefaultViewInjector> {

    private SlimViewHolder viewHolder;

    public SlimAdapterDefaultViewInjector(SlimViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    @Override
    public final <T extends View> T findViewById(int id) {
        return (T) viewHolder.id(id);
    }

    @Override
    public SlimAdapterDefaultViewInjector tag(int id, Object object) {
        findViewById(id).setTag(object);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector text(int id, int res) {
        TextView view = findViewById(id);
        view.setText(res);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector text(int id, CharSequence charSequence) {
        TextView view = findViewById(id);
        view.setText(charSequence);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector textColor(int id, int color) {
        TextView view = findViewById(id);
        view.setTextColor(color);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector textSize(int id, int sp) {
        TextView view = findViewById(id);
        view.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector image(int id, int res) {
        ImageView view = findViewById(id);
        view.setImageResource(res);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector image(int id, Drawable drawable) {
        ImageView view = findViewById(id);
        view.setImageDrawable(drawable);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector background(int id, int res) {
        View view = findViewById(id);
        view.setBackgroundResource(res);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector background(int id, Drawable drawable) {
        View view = findViewById(id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector visible(int id) {
        findViewById(id).setVisibility(View.VISIBLE);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector invisible(int id) {
        findViewById(id).setVisibility(View.INVISIBLE);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector gone(int id) {
        findViewById(id).setVisibility(View.GONE);
        return this;
    }

    @Override
    public <V extends View> SlimAdapterDefaultViewInjector with(int id, Action<V> action) {
        action.action((V) findViewById(id));
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector clicked(int id, View.OnClickListener listener) {
        findViewById(id).setOnClickListener(listener);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector longClicked(int id, View.OnLongClickListener listener) {
        findViewById(id).setOnLongClickListener(listener);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector enable(int id) {
        findViewById(id).setEnabled(true);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector disable(int id) {
        findViewById(id).setEnabled(false);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector checked(int id, boolean checked) {
        Checkable view = findViewById(id);
        view.setChecked(checked);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector adapter(int id, RecyclerView.Adapter adapter) {
        RecyclerView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector adapter(int id, Adapter adapter) {
        AdapterView view = findViewById(id);
        view.setAdapter(adapter);
        return this;
    }

    @Override
    public SlimAdapterDefaultViewInjector layoutManager(int id, RecyclerView.LayoutManager layoutManager) {
        RecyclerView view = findViewById(id);
        view.setLayoutManager(layoutManager);
        return this;
    }


}
