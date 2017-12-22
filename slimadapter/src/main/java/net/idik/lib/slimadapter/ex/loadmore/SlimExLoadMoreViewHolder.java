package net.idik.lib.slimadapter.ex.loadmore;

import android.view.View;

import net.idik.lib.slimadapter.SlimViewHolder;
import net.idik.lib.slimadapter.ex.loadmore.SlimMoreLoader;
import net.idik.lib.slimadapter.viewinjector.IViewInjector;

/**
 * Created by linshuaibin on 2017/6/22.
 */
public class SlimExLoadMoreViewHolder extends SlimViewHolder<SlimMoreLoader> {

    public SlimExLoadMoreViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(SlimMoreLoader data, IViewInjector injector) {
    }
}
