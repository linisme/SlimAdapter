package net.idik.lib.slimadapter;

import net.idik.lib.slimadapter.viewinjector.IViewInjector;

/**
 * Created by linshuaibin on 01/04/2017.
 */

public abstract class SlimInjector<T> {

    final void inject(T data, IViewInjector injector) {
        onInject(data, injector);
    }

    protected abstract void onInject(T data, IViewInjector injector);
}
