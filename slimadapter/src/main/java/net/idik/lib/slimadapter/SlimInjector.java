package net.idik.lib.slimadapter;

import net.idik.lib.slimadapter.viewinjector.IViewInjector;

/**
 * Created by linshuaibin on 01/04/2017.
 */

public interface SlimInjector<T> {
    void onInject(T data, IViewInjector injector);
}
