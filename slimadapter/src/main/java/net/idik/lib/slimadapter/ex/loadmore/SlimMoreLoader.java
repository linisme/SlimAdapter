package net.idik.lib.slimadapter.ex.loadmore;

import android.content.Context;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.idik.lib.slimadapter.SlimAdapterEx;

import java.util.List;

/**
 * Created by linshuaibin on 16/05/2017.
 */

public abstract class SlimMoreLoader extends RecyclerView.OnScrollListener {
    private static final int WHAT_LOAD_MORE = 1;

    private SlimLoadMoreView loadMoreView;
    private boolean loading;
    private LoadMoreViewCreator loadMoreViewCreator;
    private Context context;

    private SlimAdapterEx slimAdapterEx;
    private android.os.Handler eventHandler;

    private Handler handler;


    public SlimMoreLoader(Context context, LoadMoreViewCreator creator) {
        this.context = context;
        this.loadMoreViewCreator = creator;
        this.loadMoreViewCreator.attachLoader(this);
        initHandler();
    }

    public SlimMoreLoader(Context context) {
        this(context, new SimpleLoadMoreViewCreator(context));
    }

    public void setSlimAdapterEx(SlimAdapterEx slimAdapterEx) {
        this.slimAdapterEx = slimAdapterEx;
    }

    private void initHandler() {
        handler = new Handler();
        HandlerThread eventHandlerThread = new HandlerThread(SlimMoreLoader.class.getSimpleName() + ".Thread");
        eventHandlerThread.start();
        eventHandler = new android.os.Handler(eventHandlerThread.getLooper(), new android.os.Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_LOAD_MORE:
                        onLoadMore(handler);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public SlimLoadMoreView getLoadMoreView() {
        if (loadMoreView == null) {
            loadMoreView = new SlimLoadMoreView(context, loadMoreViewCreator);
        }
        return loadMoreView;
    }

    protected abstract void onLoadMore(Handler handler);

    protected abstract boolean hasMore();

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        switch (newState) {
            case RecyclerView.SCROLL_STATE_IDLE:
                int last = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int total = recyclerView.getLayoutManager().getItemCount();
                if (last + 1 == total && !loading) {
                    loadMore();
                }
                break;
            default:
                break;
        }
    }

    void loadMore() {
        if (hasMore()) {
            loading = true;
            getLoadMoreView().visibleLoadingView();
            eventHandler.removeMessages(WHAT_LOAD_MORE);
            eventHandler.sendEmptyMessage(WHAT_LOAD_MORE);
        } else {
            reset();
        }
    }

    public void reset() {
        loading = false;
        if (hasMore()) {
            getLoadMoreView().visiblePullToLoadMoreView();
        } else {
            getLoadMoreView().visibleNoMoreView();
        }
    }

    protected final class Handler {

        Handler() {
        }

        public void loadCompleted(List<?> data) {
            if (data == null) {
                reset();
                return;
            }
            List currentData = slimAdapterEx.getData();
            if (currentData == null) {
                currentData = data;
            } else {
                currentData.addAll(data);
            }
            slimAdapterEx.updateData(currentData);
        }

        public void error() {
            loading = false;
            getLoadMoreView().visibleErrorView();
        }
    }

}
