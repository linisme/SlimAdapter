package net.idik.lib.slimadapter.ex.loadmore;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by linshuaibin on 16/05/2017.
 */

public class SlimLoadMoreView extends FrameLayout {

    private View loadingView;
    private View pullToLoadMoreView;
    private View noMoreView;
    private View errorView;

    public SlimLoadMoreView(@NonNull Context context, LoadMoreViewCreator creator) {
        super(context);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setLoadingView(creator.createLoadingView());
        setNoMoreView(creator.createNoMoreView());
        setPullToLoadMoreView(creator.createPullToLoadMoreView());
        setErrorView(creator.createErrorView());
    }

    public void setErrorView(View errorView) {
        if (this.errorView != null) {
            removeView(this.errorView);
        }
        this.errorView = errorView;
        addView(errorView);
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
                errorView.setVisibility(GONE);
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
                errorView.setVisibility(GONE);
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
                errorView.setVisibility(GONE);
            }
        });
    }

    public void visibleErrorView() {
        post(new Runnable() {
            @Override
            public void run() {
                loadingView.setVisibility(GONE);
                noMoreView.setVisibility(GONE);
                pullToLoadMoreView.setVisibility(GONE);
                errorView.setVisibility(VISIBLE);
            }
        });
    }
}
