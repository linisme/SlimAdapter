package net.idik.lib.slimadapter.ex.loadmore;

import android.view.View;

/**
 * Created by linshuaibin on 22/05/2017.
 */
public abstract class LoadMoreViewCreator {

    private SlimMoreLoader loader;

    public void attachLoader(SlimMoreLoader loader) {
        this.loader = loader;
    }

    protected void reload() {
        loader.loadMore();
    }

    protected abstract View createLoadingView();

    protected abstract View createNoMoreView();

    protected abstract View createPullToLoadMoreView();

    protected abstract View createErrorView();
}
