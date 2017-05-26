package net.idik.lib.slimadapter.ex.loadmore;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by linshuaibin on 22/05/2017.
 */
public class SimpleLoadMoreViewCreator extends LoadMoreViewCreator {

    private Context context;

    private CharSequence loadingHint = "Loading...";
    private CharSequence noMoreHint = "No MORE.";
    private CharSequence pullToLoadMoreHint = "Pull to load more...";
    private CharSequence errorHint = "Error# Click to retry...";
    private int padding = 24;

    private ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    public SimpleLoadMoreViewCreator(Context context) {
        this.context = context;
    }

    public SimpleLoadMoreViewCreator setLoadingHint(CharSequence loadingHint) {
        this.loadingHint = loadingHint;
        return this;
    }

    public SimpleLoadMoreViewCreator setNoMoreHint(CharSequence noMoreHint) {
        this.noMoreHint = noMoreHint;
        return this;
    }

    public SimpleLoadMoreViewCreator setPullToLoadMoreHint(CharSequence pullToLoadMoreHint) {
        this.pullToLoadMoreHint = pullToLoadMoreHint;
        return this;
    }

    public SimpleLoadMoreViewCreator setErrorHint(CharSequence errorHint) {
        this.errorHint = errorHint;
        return this;
    }

    public SimpleLoadMoreViewCreator setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    @Override
    public View createLoadingView() {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(loadingHint);
        textView.setLayoutParams(params);
        return textView;
    }

    @Override
    public View createNoMoreView() {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(noMoreHint);
        textView.setLayoutParams(params);
        return textView;
    }

    @Override
    public View createPullToLoadMoreView() {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(pullToLoadMoreHint);
        textView.setLayoutParams(params);
        return textView;
    }

    @Override
    public View createErrorView() {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(padding, padding, padding, padding);
        textView.setText(errorHint);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reload();
            }
        });
        textView.setLayoutParams(params);
        return textView;
    }
}
