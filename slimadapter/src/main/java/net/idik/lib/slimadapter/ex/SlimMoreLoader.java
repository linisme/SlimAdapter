package net.idik.lib.slimadapter.ex;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by linshuaibin on 16/05/2017.
 */

public abstract class SlimMoreLoader extends RecyclerView.OnScrollListener {

    private SlimLoadMoreView loadMoreView;
    private boolean loading;
    private ILoadMoreViewCreator loadMoreViewCreator;
    private Context context;

    private Handler eventHandler;

    private static final int WHAT_LOAD_MORE = 1;

    public SlimMoreLoader(Context context, ILoadMoreViewCreator creator) {
        this.context = context;
        this.loadMoreViewCreator = creator;
        initHandler();
    }

    public SlimMoreLoader(Context context) {
        this(context, new DefaultLoadMoreViewCreator(context));
    }

    private void initHandler() {
        HandlerThread eventHandlerThread = new HandlerThread(SlimMoreLoader.class.getSimpleName() + ".Thread");
        eventHandlerThread.start();
        eventHandler = new Handler(eventHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case WHAT_LOAD_MORE:
                        onLoadMore();
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

    protected abstract void onLoadMore();

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

    private void loadMore() {
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

    public interface ILoadMoreViewCreator {
        View createLoadingView();

        View createNoMoreView();

        View createPullToLoadMoreView();
    }

    public static class DefaultLoadMoreViewCreator implements ILoadMoreViewCreator {

        private final static int PADDING = 24;

        private Context context;

        public DefaultLoadMoreViewCreator(Context context) {
            this.context = context;
        }

        @Override
        public View createLoadingView() {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(PADDING, PADDING, PADDING, PADDING);
            textView.setText("Loading...");
            return textView;
        }

        @Override
        public View createNoMoreView() {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(PADDING, PADDING, PADDING, PADDING);
            textView.setText("No MORE");
            return textView;
        }

        @Override
        public View createPullToLoadMoreView() {
            TextView textView = new TextView(context);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(PADDING, PADDING, PADDING, PADDING);
            textView.setText("Pull to load more...");
            return textView;
        }
    }
}
