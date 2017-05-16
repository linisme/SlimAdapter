package net.idik.lib.slimadapter.ex;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by linshuaibin on 16/05/2017.
 */

public class SlimLoadMoreView extends FrameLayout {

    private View loadingView;
    private View pullToLoadMoreView;
    private View noMoreView;

    public SlimLoadMoreView(@NonNull Context context, SlimMoreLoader.ILoadMoreViewCreator creator) {
        super(context);
        setLoadingView(creator.createLoadingView());
        setNoMoreView(creator.createNoMoreView());
        setPullToLoadMoreView(creator.createPullToLoadMoreView());
    }


    public void setLoadingView(View loadingView) {
        if (this.loadingView != null) {
            removeView(this.loadingView);
        }
        this.loadingView = loadingView;
        addView(loadingView);
    }

    public void setPullToLoadMoreView(View pullToLoadMoreView) {
        if (this.pullToLoadMoreView != null) {
            removeView(this.pullToLoadMoreView);
        }
        this.pullToLoadMoreView = pullToLoadMoreView;
        addView(pullToLoadMoreView);
    }

    public void setNoMoreView(View noMoreView) {
        if (this.noMoreView != null) {
            removeView(this.noMoreView);
        }
        this.noMoreView = noMoreView;
        addView(noMoreView);
    }

    public void visibleLoadingView() {
        post(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(VISIBLE);
                noMoreView.setVisibility(GONE);
                pullToLoadMoreView.setVisibility(GONE);
            }
        });
    }

    public void visiblePullToLoadMoreView() {
        post(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(GONE);
                noMoreView.setVisibility(GONE);
                pullToLoadMoreView.setVisibility(VISIBLE);
            }
        });
    }

    public void visibleNoMoreView() {
        post(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(GONE);
                noMoreView.setVisibility(VISIBLE);
                pullToLoadMoreView.setVisibility(GONE);
            }
        });
    }
}
