package net.idik.lib.slimadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.idik.lib.slimadapter.viewinjector.IViewInjector;
import net.idik.lib.slimadapter.viewinjector.SlimAdapterDefaultViewInjector;


/**
 * Created by linshuaibin on 21/12/2016.
 */

public abstract class SlimViewHolder<D> extends RecyclerView.ViewHolder {

    private SparseArray<View> viewMap;

    private IViewInjector injector;

    public SlimViewHolder(ViewGroup parent, int itemLayoutRes) {
        this(LayoutInflater.from(parent.getContext()).inflate(itemLayoutRes, parent, false));
    }

    public SlimViewHolder(View itemView) {
        super(itemView);
        viewMap = new SparseArray<>();
    }

    final void bind(D data) {
        if (injector == null) {
            injector = new SlimAdapterDefaultViewInjector(this);
        }
        onBind(data, injector);
    }

    protected abstract void onBind(D data, IViewInjector injector);

    public final <T extends View> T id(int id) {
        View view = viewMap.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            viewMap.put(id, view);
        }
        return (T) view;
    }

}
