package net.idik.lib.slimadapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.idik.lib.slimadapter.diff.SlimDiffUtil;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linshuaibin on 16/05/2017.
 */
public class SlimAdapterEx extends SlimAdapter {

    private final static int TYPE_EX = -100;
    private final static int TYPE_EMPTY = -90;

    private List<SlimExViewHolder> headerItems;
    private List<SlimExViewHolder> footerItems;


    private View emptyView;


    protected SlimAdapterEx() {
        super();
        headerItems = new ArrayList<>();
        footerItems = new ArrayList<>();
    }

    public SlimAdapterEx addHeader(View view) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        headerItems.add(new SlimExViewHolder(view));
        notifyDataSetChanged();
        return this;
    }

    public SlimAdapterEx addFooter(View view) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        footerItems.add(new SlimExViewHolder(view));
        notifyDataSetChanged();
        return this;
    }

    public SlimAdapterEx addHeader(Context context, int layoutRes) {
        return addHeader(LayoutInflater.from(context).inflate(layoutRes, null));
    }

    public SlimAdapterEx addFooter(Context context, int layoutRes) {
        return addFooter(LayoutInflater.from(context).inflate(layoutRes, null));
    }

    public SlimAdapterEx enableEmptyView(Context context, int layoutRes) {
        return enableEmptyView(LayoutInflater.from(context).inflate(layoutRes, null));
    }

    public SlimAdapterEx enableEmptyView(View emptyView) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        emptyView.setLayoutParams(params);
        this.emptyView = emptyView;
        return this;
    }


    private Object getExItem(int position) {
        if (position < headerItems.size()) {
            return headerItems.get(position);
        } else {
            position -= headerItems.size();
            if (position < footerItems.size()) {
                return footerItems.get(position);
//            } else {
//                return moreLoader;
            }
        }
        return null;
    }


    @Override
    public int getItemViewType(int position) {
        if (emptyView != null && (super.getData() == null || super.getData().size() == 0)) {
            return TYPE_EMPTY;
        }
        if (position < headerItems.size()) {
            return TYPE_EX - position;
        } else {
            position -= headerItems.size();
            if (position < super.getItemCount()) {
                return super.getItemViewType(position);
            } else {
                position -= super.getItemCount();
                if (position < footerItems.size()) {
                    return TYPE_EX - position - headerItems.size();
                } else {
                    return TYPE_EX - headerItems.size() - footerItems.size();
                }
            }
        }
    }

    @Override
    public SlimViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlimViewHolder viewHolder;
        if (viewType == TYPE_EMPTY) {
            viewHolder = new SlimExViewHolder(emptyView);
        } else if (viewType <= TYPE_EX) {
            Object item = getExItem(TYPE_EX - viewType);
            viewHolder = (SlimViewHolder) item;
        } else {
            viewHolder = super.onCreateViewHolder(parent, viewType);
        }
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        if (emptyView != null && (getData() == null || getData().size() == 0)) {
            return 1;
        }
        return footerItems.size() + headerItems.size() + super.getItemCount();
    }

    @Override
    public Object getItem(int position) {
        if (emptyView != null && (getData() == null || getData().size() == 0) && position == 0) {
            return emptyView;
        }
        if (position < headerItems.size()) {
            return headerItems.get(position);
        } else {
            position -= headerItems.size();
            if (position < super.getItemCount()) {
                return super.getItem(position);
            } else {
                position -= super.getItemCount();
                if (position < footerItems.size()) {
                    return footerItems.get(position);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public SlimAdapterEx enableDiff() {
        return (SlimAdapterEx) super.enableDiff();
    }

    @Override
    public SlimAdapterEx enableDiff(SlimDiffUtil.Callback diffCallback) {
        return (SlimAdapterEx) super.enableDiff(diffCallback);
    }

    @Override
    public SlimAdapterEx registerDefault(int layoutRes, SlimInjector slimInjector) {
        return (SlimAdapterEx) super.registerDefault(layoutRes, slimInjector);
    }

    @Override
    public <T> SlimAdapterEx register(int layoutRes, SlimInjector<T> slimInjector) {
        return (SlimAdapterEx) super.register(layoutRes, slimInjector);
    }

    @Override
    public SlimAdapterEx attachTo(RecyclerView... recyclerViews) {
        return (SlimAdapterEx) super.attachTo(recyclerViews);
    }

    private class SlimExViewHolder extends SlimViewHolder<Object> {


        public SlimExViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Object data, IViewInjector injector) {

        }
    }

}
