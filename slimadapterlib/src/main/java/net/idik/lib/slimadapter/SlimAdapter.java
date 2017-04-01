package net.idik.lib.slimadapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linshuaibin on 10/02/2017.
 */

public class SlimAdapter extends AbstractSlimAdapter {

    private SlimAdapter() {
    }

    private List<?> data;

    public SlimAdapter setData(List<?> data) {
        this.data = data;
        return this;
    }

    public List<?> getData() {
        return data;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    private List<Type> dataTypes = new ArrayList<>();

    private Map<Type, IViewHolderCreator> creators = new HashMap<>();

    private IViewHolderCreator defaultCreator = null;

    @Override
    public final synchronized SlimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Type dataType = dataTypes.get(viewType);
        IViewHolderCreator creator = creators.get(dataType);
        if (creator == null) {
            for (Type t : creators.keySet()) {
                if (((Class) t).isAssignableFrom((Class) dataType)) {
                    creator = creators.get(t);
                    break;
                }
            }
        }
        if (creator == null) {
            if (defaultCreator == null) {
                throw new IllegalArgumentException("Neither the TYPE not The DEFAULT injector found...");
            }
            creator = defaultCreator;
        }
        return creator.create(parent);
    }

    public static SlimAdapter create() {
        return new SlimAdapter();
    }

    public <T> SlimAdapter withDefault(final int layoutRes, final SlimInjector slimInjector) {
        defaultCreator = new IViewHolderCreator<T>() {
            @Override
            public SlimTypeViewHolder<T> create(ViewGroup parent) {
                return new SlimTypeViewHolder<T>(parent, layoutRes) {
                    @Override
                    protected void onBind(T data, IViewInjector injector) {
                        slimInjector.inject(data, injector);

                    }
                };
            }
        };
        return this;
    }

    public <T> SlimAdapter with(final int layoutRes, final SlimInjector<T> slimInjector) {
        Type type = ((ParameterizedType) slimInjector.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        creators.put(type, new IViewHolderCreator<T>() {
            @Override
            public SlimTypeViewHolder<T> create(ViewGroup parent) {
                return new SlimTypeViewHolder<T>(parent, layoutRes) {
                    @Override
                    protected void onBind(T data, IViewInjector injector) {
                        slimInjector.inject(data, injector);
                    }
                };
            }
        });
        return this;
    }

    public SlimAdapter attachTo(RecyclerView recyclerView) {
        recyclerView.setAdapter(this);
        return this;
    }

    @Override
    public final synchronized int getItemViewType(int position) {
        Object item = getItem(position);
        int index = dataTypes.indexOf(item.getClass());
        if (index == -1) {
            dataTypes.add(item.getClass());
        }
        index = dataTypes.indexOf(item.getClass());
        return index;
    }

    private interface IViewHolderCreator<T> {
        SlimAdapter.SlimTypeViewHolder<T> create(ViewGroup parent);
    }

    private static abstract class SlimTypeViewHolder<T> extends SlimViewHolder<T> {

        SlimTypeViewHolder(ViewGroup parent, int itemLayoutRes) {
            super(parent, itemLayoutRes);
        }

    }

}
