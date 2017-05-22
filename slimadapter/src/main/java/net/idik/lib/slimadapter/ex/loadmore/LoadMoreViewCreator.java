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

    abstract View createLoadingView();

    abstract View createNoMoreView();

    abstract View createPullToLoadMoreView();

    abstract View createErrorView();
}
