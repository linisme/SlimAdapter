package net.idik.lib.slimadapter;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import net.idik.lib.slimadapter.diff.DefaultDiffCallback;
import net.idik.lib.slimadapter.diff.SlimDiffUtil;
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

    private static final int WHAT_NOTIFY_DATA_SET_CHANGED = 1;

    protected SlimAdapter() {
    }

    public static SlimAdapterEx create() {
        return new SlimAdapterEx();
    }

    public static <T extends SlimAdapter> T create(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<?> data;

    private Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case WHAT_NOTIFY_DATA_SET_CHANGED:
                    notifyDataSetChanged();
                    break;
            }
            super.dispatchMessage(msg);
        }
    };

    public SlimAdapter updateData(List<?> data) {
        if (diffCallback == null || getItemCount() == 0 || data == null || data.size() == 0) {
            this.data = data;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                notifyDataSetChanged();
            } else {
                uiHandler.removeMessages(WHAT_NOTIFY_DATA_SET_CHANGED);
                uiHandler.sendEmptyMessage(WHAT_NOTIFY_DATA_SET_CHANGED);
            }
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SlimDiffUtil(this.data, data, diffCallback));
            this.data = data;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                diffResult.dispatchUpdatesTo(this);
            } else {
                uiHandler.removeMessages(WHAT_NOTIFY_DATA_SET_CHANGED);
                uiHandler.sendEmptyMessage(WHAT_NOTIFY_DATA_SET_CHANGED);
            }
        }
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

    private SlimDiffUtil.Callback diffCallback = null;

    public SlimAdapter enableDiff() {
        enableDiff(new DefaultDiffCallback());
        return this;
    }

    public SlimAdapter enableDiff(SlimDiffUtil.Callback diffCallback) {
        this.diffCallback = diffCallback;
        return this;
    }

    @Override
    public SlimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Type dataType = dataTypes.get(viewType);
        IViewHolderCreator creator = creators.get(dataType);
        if (creator == null) {
            for (Type t : creators.keySet()) {
                if (isTypeMatch(t, dataType)) {
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

    private boolean isTypeMatch(Type type, Type targetType) {
        if (type instanceof Class && targetType instanceof Class) {
            if (((Class) type).isAssignableFrom((Class) targetType)) {
                return true;
            }
        } else if (type instanceof ParameterizedType && targetType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            ParameterizedType parameterizedTargetType = (ParameterizedType) targetType;
            if (isTypeMatch(parameterizedType.getRawType(), ((ParameterizedType) targetType).getRawType())) {
                Type[] types = parameterizedType.getActualTypeArguments();
                Type[] targetTypes = parameterizedTargetType.getActualTypeArguments();
                if (types == null || targetTypes == null || types.length != targetTypes.length) {
                    return false;
                }
                int len = types.length;
                for (int i = 0; i < len; i++) {
                    if (!isTypeMatch(types[i], targetTypes[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    public SlimAdapter registerDefault(final int layoutRes, final SlimInjector slimInjector) {
        defaultCreator = new IViewHolderCreator() {
            @Override
            public SlimTypeViewHolder create(ViewGroup parent) {
                return new SlimTypeViewHolder(parent, layoutRes) {
                    @Override
                    protected void onBind(Object data, IViewInjector injector) {
                        slimInjector.onInject(data, injector);

                    }
                };
            }
        };
        return this;
    }

    public <T> SlimAdapter register(final int layoutRes, final SlimInjector<T> slimInjector) {
        Type type = getSlimInjectorActualTypeArguments(slimInjector);
        if (type == null) {
            throw new IllegalArgumentException();
        }
        creators.put(type, new IViewHolderCreator<T>() {
            @Override
            public SlimTypeViewHolder<T> create(ViewGroup parent) {
                return new SlimTypeViewHolder<T>(parent, layoutRes) {
                    @Override
                    protected void onBind(T data, IViewInjector injector) {
                        slimInjector.onInject(data, injector);
                    }
                };
            }
        });
        return this;
    }

    private <T> Type getSlimInjectorActualTypeArguments(SlimInjector<T> slimInjector) {
        Type[] interfaces = slimInjector.getClass().getGenericInterfaces();
        for (Type type : interfaces) {
            if (type instanceof ParameterizedType) {
                if (((ParameterizedType) type).getRawType().equals(SlimInjector.class)) {
                    Type actualType = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (actualType instanceof Class) {
                        return actualType;
                    } else {
                        throw new IllegalArgumentException("The generic type argument of SlimInjector is NOT support Generic Parameterized Type now, Please using a WRAPPER class install of it directly.");
                    }
                }
            }
        }
        return null;
    }

    public SlimAdapter attachTo(RecyclerView... recyclerViews) {
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setAdapter(this);
        }
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        Object item = data.get(position);
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
