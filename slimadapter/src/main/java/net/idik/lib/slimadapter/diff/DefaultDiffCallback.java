package net.idik.lib.slimadapter.diff;

/**
 * Created by linshuaibin on 16/05/2017.
 */

public class DefaultDiffCallback implements SlimDiffUtil.Callback {
    @Override
    public boolean areItemsTheSame(Object oldItem, Object newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(Object oldItem, Object newItem) {
        return true;
    }
}
