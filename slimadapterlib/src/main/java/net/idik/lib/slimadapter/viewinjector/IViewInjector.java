package net.idik.lib.slimadapter.viewinjector;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;

/**
 * Created by linshuaibin on 22/12/2016.
 */
public interface IViewInjector<VI extends IViewInjector> {

    interface Action<V extends View> {
        void action(V view);
    }

    <T extends View> T findViewById(int id);

    VI tag(int id, Object object);

    VI text(int id, int res);

    VI text(int id, CharSequence charSequence);

    VI textColor(int id, int color);

    VI textSize(int id, int sp);

    VI image(int id, int res);

    VI image(int id, Drawable drawable);

    VI background(int id, int res);

    VI background(int id, Drawable drawable);

    VI visible(int id);

    VI invisible(int id);

    VI gone(int id);

    <V extends View> VI with(int id, Action<V> action);

    VI clicked(int id, View.OnClickListener listener);

    VI longClicked(int id, View.OnLongClickListener listener);

    VI enable(int id);

    VI disable(int id);

    VI checked(int id, boolean checked);

    VI adapter(int id, RecyclerView.Adapter adapter);

    VI adapter(int id, Adapter adapter);

    VI layoutManager(int id, RecyclerView.LayoutManager layoutManager);
}
